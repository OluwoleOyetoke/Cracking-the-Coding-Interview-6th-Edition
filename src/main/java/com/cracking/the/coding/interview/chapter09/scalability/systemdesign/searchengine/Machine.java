/*
 * Copyright (C) 2018 Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cracking.the.coding.interview.chapter09.scalability.systemdesign.searchengine;

import java.util.HashMap;

enum RequestSource {
    ME,
    OTHERS;
}

enum RequestType {
    READ,
    WRITE;
}

/**
 * Search backend machine
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class Machine implements IMachine {

    public int RESPONSE_SENT = 0;
    private static int RESPONDERS = 0;
    private int MACHINE_ID = 1;
    private HashMap<Integer, String> cache = new HashMap<>();
    private HashMap<Integer, Integer> directory = new HashMap<>();

    public Machine(int machineId) {
        Bus.getBusInstance().register(this); //register machine to the bus
        MACHINE_ID = machineId;
    }

    public Machine() {

    }

    @Override
    public void init() {
        RESPONSE_SENT = 0;
        RESPONDERS = 0;
    }

    @Override
    public IMachine getMe() {
        return this;
    }

    @Override
    public void cearCache() {

    }

    @Override
    public void search(int queryHash, String query, int count, IMachine.IResponse callback) {
        final int responseSent = 0;
        //Check current machine cache first before placing request on bus
        IMachine.IResponse subCallback = new IMachine.IResponse() {
            @Override
            public void response(String result, int present, int machineId) {
                if (present == 0) { //fire request on bus if not present in my own cache
                    System.out.println("Since not found in machine " + MACHINE_ID + "...Read request fired on bus");
                    IMachine.IResponse subSubCallback = new IMachine.IResponse() {
                        @Override
                        public void response(String result, int present, int machineId) {
                            if (present == 0) {//check if i should add to my own cache + forward search to cluster
                                //System.out.println("Machine " + MACHINE_ID + " did not find its request in machine " + machineId);
                                if ((RESPONDERS == MachineFactory.NO_OF_MACHINES - 2) && RESPONSE_SENT == 0) {
                                    System.out.println("Expensive blocking call to cluster ongoing for: " + query);
                                    String expensiveResult = processSearch(query);
                                    if (count >= QueryBase.CACHE_HIT_FREQUENCY_FLAG) {
                                        System.out.println("Query '" + query + "' HIT " + QueryBase.CACHE_HIT_FREQUENCY_FLAG + " time(s).... now added to the cache of machine " + MACHINE_ID);
                                        addToCache(queryHash, expensiveResult);
                                    }
                                    callback.response(expensiveResult, 1, 0); //send result back to search engine
                                }

                            } else {//present in the cache of some other machine
                                if (RESPONSE_SENT == 0) {
                                    //System.out.println("Result for '" + query + "' gotten from the cache of machine " + machineId);
                                    RESPONSE_SENT = 1;
                                    callback.response(result, present, machineId);
                                }
                            }
                            RESPONDERS++;
                        }
                    };
                    //Send request on bus
                    Bus.getBusInstance().request(queryHash, subSubCallback, RequestType.READ, RequestSource.OTHERS, getMe());
                } else {//send result back to search engine
                    System.out.println("Result for '" + query + "' gotten from current machine cache");
                    callback.response(result, present, machineId);
                }
            }
        };
        checkYourCache(queryHash, subCallback, RequestType.READ, RequestSource.ME, getMe()); //Check my own device cache
    }

    /**
     * Expensive search process, so let us assume: 1. It is not asynchronous i.e
     * it is a blocking call 2. The cluster object is not singleton, so we
     * always have to instantiate a new one
     *
     * @param query query to search for;
     * @return result search result
     */
    @Override
    public String processSearch(String query) {
        Cluster cluster = new Cluster();
        return cluster.answerClientNonAsync(query);
    }

    /**
     * Check cache of current machine to know if query result already exist
     * there
     *
     * @param queryHash hash of query
     * @param callback call back
     * @param requestType 0=read, 1=write
     * @param from from me=0, from other machines=1;
     */
    @Override
    public void checkYourCache(int queryHash, IResponse callback, RequestType requestType, RequestSource from, IMachine requester) {
        if (requester.getResponseSent() != 1) {
            System.out.println("Machine " + MACHINE_ID + " checking its cache");
            if (directory.containsKey(queryHash)) {
                //check what state data is in the cache
                int stateTable = directory.get(queryHash);
                int currentState = checkDataState(stateTable);
                System.out.println("Found in machine " + MACHINE_ID + " cache");

                //EXCLUSIVE STATE
                if (currentState == 1 && requestType == RequestType.READ) { //currentState 1 = exclusive
                    if (from == RequestSource.OTHERS) {
                        directory.put(queryHash, 2); //move to shared
                        requester.getDirectory().put(queryHash, 2); //move requester into shared state also
                    }
                    callback.response(cache.get(queryHash), 1, MACHINE_ID);
                } else if (currentState == 1 && requestType == RequestType.WRITE) {
                    if (from == RequestSource.OTHERS) {
                        directory.put(queryHash, 4); //invalidate my copy 
                        requester.getDirectory().put(queryHash, 3); //...requesters copy becomes modified
                    }
                    callback.response(cache.get(queryHash), 1, MACHINE_ID);
                } //SHARED STATE
                else if (currentState == 2 && requestType == RequestType.READ) { //currentState 2 = shared
                    callback.response(cache.get(queryHash), 1, MACHINE_ID); //remains shared
                } else if (currentState == 2 && requestType == RequestType.WRITE) {
                    //directory.put(queryHash, 4); //invalidate my copy 
                    Bus.getBusInstance().invalidateAll(queryHash);//invalidate other shared copy
                    requester.getDirectory().put(queryHash, 3); //move requesters version to modified state
                    callback.response(cache.get(queryHash), 1, MACHINE_ID);
                } //MODIFIED STATE
                else if (currentState == 3 && requestType == RequestType.READ) { //currentState 3 = modified
                    directory.put(queryHash, 4); //invalidate my copy 
                    requester.getDirectory().put(queryHash, 1);//...requesters copy becomes exclusive
                    callback.response(cache.get(queryHash), 1, MACHINE_ID);
                } else if (currentState == 3 && requestType == RequestType.WRITE) {
                    callback.response(cache.get(queryHash), 1, MACHINE_ID); //remains modified
                } //INVALIDATED STATE
                else if (currentState == 4 && requestType == RequestType.READ) { //currentState 4 = invalidated
                    callback.response("", 0, MACHINE_ID); //call back with not present
                } else if (currentState == 4 && requestType == RequestType.WRITE) {
                    callback.response("", 0, MACHINE_ID); //call back with not present
                }
                //currentState 0 = not present at all
            } else {
                //respond to bus with 'not present'
                System.out.println("Not Found in machine " + MACHINE_ID + " cache");
                callback.response("", 0, MACHINE_ID); //call back with not present
            }
        }
    }

    /**
     * Find set bit among the 5 possible state for MESI protocol
     *
     * @param data data
     * @return state return state of data in current cache
     */
    @Override
    public int checkDataState(int data) {
        int[] forBits = {0, 1, 2, 4, 8};
        int state;
        for (int i = 0; i < 4; i++) {
            state = data & forBits[i];
            if (state > 0) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void addToCache(int queryHash, String result) {
        cache.put(queryHash, result);
        directory.put(queryHash, 1); //add as exclusive
    }

    @Override
    public HashMap<Integer, Integer> getDirectory() {
        return directory;
    }

    @Override
    public int getMachineId() {
        return MACHINE_ID;
    }

    @Override
    public int getResponseSent() {
        return RESPONSE_SENT;
    }

    @Override
    public HashMap<Integer, String> getCache() {
        return cache;
    }
}
