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
package Scalability_And_System_Design.Cache;

import java.util.HashMap;
 enum RequestSource{
        ME,
        OTHERS;
    }

enum RequestType{
        READ,
        WRITE;
    }
/**
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 */
public class Machine1 implements IMachine {
    private static Machine1 machine;
    final int MACHINE_ID = 1;
    
    private Machine1(){
        
    }
    HashMap<Integer, String> cache = new HashMap<>();
    HashMap<Integer, Integer> directory = new HashMap<>();
    public static IMachine getMachineInstance(){
        if(machine==null){
            machine = new Machine1();
            Bus.getBusInstance().register(machine); //register machine to the bus
            return machine;
        }else{
            return machine;
        }
    }

    @Override
    public void cearCache() {
     
    }

    @Override
    public String search(int queryHash, String query, int count) {
        Bus.getBusInstance().request(queryHash, count);
        
        return "";
    }

    @Override
    public String processSearch(String query) {
     return "";
    }
    
    /**
     * Check cache of current machine to know if query result already exist there
     * @param queryHash hash of query
     * @param callback call back
     * @param requestTyype 0=read, 1=write
     * @param from from me=0, from other machines=1;
     */
    @Override
    public void checkYourCache(int queryHash, IResponse callback, RequestType requestType, RequestSource from) {
        if(directory.containsKey(queryHash)){
            //check what state data is in the cache
            int stateTable = directory.get(queryHash);
            int currentState = checkDataState(stateTable);
            int newState=0;
            if(currentState==0 && requestType==RequestType.READ){ //currentState 0 = not present before (will never be this, cos cace.get is present)
                
            }else if(currentState==0 && requestType==RequestType.WRITE){
                
            }
            
            //EXCLUSIVE STATE
            else if(currentState==1 && requestType==RequestType.READ){ //currentState 1 = exclusive
                if(from == RequestSource.ME){
                   // directory.put(queryHash, 1);//leave state as exclusive
                }else{
                   directory.put(queryHash, 2); //move to shared
                }
                callback.response(cache.get(queryHash), 1, MACHINE_ID);
            }else if(currentState==1 && requestType==RequestType.WRITE){
                  if(from == RequestSource.ME){
                    directory.put(queryHash, 1);//leave state as exclusive
                }else{
                   directory.put(queryHash, 4); //invalidate my copy ...requesters copy becomes modified
                }
                callback.response(cache.get(queryHash), 1, MACHINE_ID);
            } 
            
            
            //SHARED STATE
            else if(currentState==2 && requestType==RequestType.READ){ //currentState 2 = shared
                 callback.response(cache.get(queryHash), 1, MACHINE_ID); //remains shared
            }else if(currentState==2 && requestType==RequestType.WRITE){
                  directory.put(queryHash, 4); //invalidate my copy 
                  callback.response(cache.get(queryHash), 1, MACHINE_ID);
            } 
            
            //MODIFIED STATE
            else if(currentState==3 && requestType==RequestType.READ){ //currentState 3 = modified
                directory.put(queryHash, 4); //invalidate my copy ...requesters copy becomes exclusive
                callback.response(cache.get(queryHash), 1, MACHINE_ID);
            }else if(currentState==3 && requestType==RequestType.WRITE){
               callback.response(cache.get(queryHash), 1, MACHINE_ID); //remains modified
            } 
            
            
            //INVALIDATED STATE
            else if(currentState==4 && requestType==RequestType.READ){ //currentState 4 = invalidated
                callback.response("", 0, MACHINE_ID); //call back with not present
            }else if(currentState==4 && requestType==RequestType.WRITE){
                callback.response("", 0, MACHINE_ID); //call back with not present
            }
            //currentState 0 = not present at all
        }else{
            //respond to bus with 'not present'
        }
    }
    
    /**
     * Find set bit among the 5 possible state for MESI protocol
     * @param data data
     * @return state return state of data in current cache
     */
    @Override
    public int checkDataState(int data) {
        int[] forBits = {0, 1, 2, 4, 8};
        int state;
        for(int i=0; i<4; i++){
            state = data & forBits[i];
            if(state>0){
                return i;
            }
        }
        return 0;
    }
}
