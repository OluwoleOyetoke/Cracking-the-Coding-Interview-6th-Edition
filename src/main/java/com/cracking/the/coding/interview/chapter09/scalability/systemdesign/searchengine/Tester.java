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

/**
 * <b>Cache:</b> Imagine a web server for a simplified search engine. This
 * system has 100 machines to respond to search queries, which may then call out
 * using processSearch(string query) to another cluster of machines to actually
 * get the result. The machine which responds to a given query is chosen at
 * random, so you cannot guarantee that the same machine will always respond to
 * the same request. The method processSearch is very expensive. Design a
 * caching mechanism to cache the results of the most recent queries. Be sure to
 * explain how you would update the cache when data changes.
 *
 * Design is made in compliance with the MESI protocol Gentle clearing of cache
 * as it gets full is not implemented... In real computer architecture, many
 * things are used to determine what block of the cache to clear out. Factors
 * such as least frequently accessed, oldest accessed etc. However, here, we
 * could use a linked list which falls off its tail once content is gt 100
 *
 * @author Oluwole Oyetoke (oluwoleoyetoke@gmail.com)
 */
public class Tester {

    /**
     * Main method for the search Engine
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args){
        String[] query = {
            "Who won the 2018 world cup",
            "What is the capital of the USA",
            "What country is the most populous country in Africa",
            "When is the next Olympics",
            "How many days are there in a leap year",
            "Who won the 2018 world cup",
            "When is the next Olympics",
            "Who won the 2018 world cup",
            "Who is the president of the USA",
            "When is the next Olympics",
            "How many days are there in a leap year",
            "When is the next Olympics",
            "Who is the president of the USA",
            "What country is the most populous country in Africa",
            "How many days are there in a leap year",
            "What is the capital of the USA",
            "What country is the most populous country in Africa",
        };
        
        
     
        SearchEngine engine = new SearchEngine();
        long start=0;
        long stop=0;
        long duration;
        for(int i=0; i<query.length; i++){
            start=System.currentTimeMillis();
            engine.search(query[i]);
            stop=System.currentTimeMillis();
            duration = stop-start;
            System.out.println("Duration: "+duration+" milli second(s)\n");
        }
        
         System.out.println("PRINT ALL MACHINE CACHES");
         Iterator<IMachine> it = engine.machineFactory.machines.iterator();
         IMachine machine;
         String values;
         while(it.hasNext()){
             machine = it.next();
             values = Arrays.toString(machine.getCache().values().toArray());
             System.out.println("Machine "+machine.getMachineId()+": "+values);
         }
    }
     */
}
