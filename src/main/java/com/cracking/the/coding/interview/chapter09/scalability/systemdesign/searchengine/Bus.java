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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Bus to which all machines on the search engine backend are registered to.
 * Uses the Observer pattern
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class Bus implements IBus {

    List<IMachine> machines = new ArrayList();
    private static Bus bus;
    public static int NO_OF_MACHINES_ATTACHED = MachineFactory.NO_OF_MACHINES;

    private Bus() {

    }

    public static Bus getBusInstance() {
        if (bus == null) {
            bus = new Bus();
            return bus;
        } else {
            return bus;
        }
    }

    @Override
    public boolean request(int queryHash, IMachine.IResponse callback, RequestType requestType, RequestSource from, IMachine requester) {
        Iterator<IMachine> it = machines.listIterator();
        IMachine tempMachine;
        while (it.hasNext()) {
            tempMachine = it.next();
            if (tempMachine != requester) {
                tempMachine.checkYourCache(queryHash, callback, requestType, from, requester);
            }
        }
        return true;
    }

    @Override
    public void register(IMachine machine) {
        machines.add(machine);
    }

    @Override
    public void invalidateAll(int queryHash) {
        Iterator<IMachine> it = machines.listIterator();
        IMachine tempMachine;
        while (it.hasNext()) {
            tempMachine = it.next();
            if (tempMachine.getDirectory().containsKey(queryHash)) {
                tempMachine.getDirectory().put(queryHash, 4);
            }
        }
    }
}
