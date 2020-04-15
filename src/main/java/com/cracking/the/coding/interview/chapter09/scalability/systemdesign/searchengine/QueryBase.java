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

/**
 * In real life, logging and keeping a count of the query is another complex
 * process on it own, as this will need another set of distributed databases
 * etc.
 *
 * Here, we will make use of just a single hash map which can be used to keep
 * track of the queries received
 *
 * @author Oluwole Oyetoke (oluwoleoyetoke@gmail.com)
 */
public class QueryBase {

    HashMap<Integer, Integer> log = new HashMap<>();
    public static int CACHE_HIT_FREQUENCY_FLAG = 2;

    /**
     * Check if query has occured before and also increment whatever its current
     * count is
     *
     * @param hash query hash
     * @return current count/number of appearance of query so far
     */
    int logQuery(int queryHash) {
        log.putIfAbsent(queryHash, 0);
        int currentCount = log.get(queryHash);
        log.put(queryHash, currentCount + 1);
        return currentCount + 1;
    }
}
