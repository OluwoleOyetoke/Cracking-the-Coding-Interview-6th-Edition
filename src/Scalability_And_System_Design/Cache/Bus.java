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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 */
public class Bus implements IBus {
    List<IMachine> machines = new ArrayList();
    private static Bus bus;
    
    private Bus(){
        
    }
    
    public static Bus getBusInstance(){
        if(bus==null){
            bus = new Bus();
            return bus;
        }else{
            return bus;
        }
    }
    
    @Override
    public boolean request(int queryHash, IMachine.IResponse callback) {
       Iterator<IMachine> it = machines.listIterator();
       while(it.hasNext()){
           it.next().checkYourCache( queryHash, callback);
       }
       return true;
    }

    @Override
    public void register(IMachine machine) {
        machines.add(machine);
    }
    
}
