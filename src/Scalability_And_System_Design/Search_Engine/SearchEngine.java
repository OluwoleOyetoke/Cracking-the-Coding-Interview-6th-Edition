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
package Scalability_And_System_Design.Search_Engine;

import java.util.Random;

/**
 * Performs the search
 *
 * @author Oluwole Oyetoke (oluwoleoyetoke@gmail.com)
 */
public class SearchEngine {

    QueryBase queryBase = new QueryBase();
    MachineFactory machineFactory;
    IMachine machine;

    public SearchEngine() {
        machineFactory = new MachineFactory();
        machineFactory.generateMachines();
    }

    /**
     * Trigger search
     *
     * @param query search string
     */
    public void search(String query) {
        int queryHash = query.hashCode();
        int count = queryBase.logQuery(queryHash);
        machine = getMachine();
        machine.init();
        System.out.println("Machine " + machine.getMachineId() + " selected to provide answer for: " + query);
        machine.search(queryHash, query, count, new IMachine.IResponse() {
            @Override
            public void response(String result, int present, int machineId) {
                System.out.println("Search Result for " + query + " is: " + result);
            }
        });
    }

    /**
     * A pool using a queue should be implemented here to make sure a currently
     * busy machine is placed behind the dispatch queue.
     *
     * To avoid using the same machine object to respond to two different search
     * simultaneously
     *
     * @return aMachine available machine
     */
    IMachine getMachine() {
        Random rand = new Random();
        int machineNo = rand.nextInt(MachineFactory.NO_OF_MACHINES);
        return machineFactory.machines.get(machineNo);
    }
}
