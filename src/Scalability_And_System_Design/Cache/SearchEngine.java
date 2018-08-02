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

import java.util.Random;

/**
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 */
public class SearchEngine {
    
    QueryBase queryBase = new QueryBase();
    IMachine machine;
    int CACHE_HIT_FREQUENCY_FLAG = 5;
    public void search(String query){
        int queryHash = query.hashCode();
        int count = queryBase.logQuery(queryHash);
        machine = getMachine();
        machine.search(queryHash, query, count);
        
    }
    
    
    IMachine getMachine(){
        Random rand = new Random();
        int machineNo = rand.nextInt(4);
        if(machineNo==0){
            return Machine1.getMachineInstance();
        }else if(machineNo==1){
            return Machine2.getMachineInstance();
        }else if(machineNo==2){
            return Machine3.getMachineInstance();
        }else if(machineNo==3){
            return Machine4.getMachineInstance();
        }else{
            System.out.println("Index Out of bound");
            return Machine1.getMachineInstance();
        }
    }
}
