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
package Ch10_Sorting_And_Searching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Given a string, rearrange it in decreasing order by the characters frequency
 * and in lexicographical order if their frequency is equal.
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class SortString {
    
    
    /**
     * Sort string based on frequency of occurence
     * @param given given string
     */
    public void rearrange(String given) {
        if(given==null || given.length()==0){
            return;
        }
        System.out.println("Given: "+given);
        char[] arr = given.toCharArray();
      
        HashMap<Character, Integer> map = new HashMap<>();
        //character count
        int currentCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                currentCount = map.get(arr[i]);
                currentCount++;
                map.put(arr[i], currentCount);
            } else {
                map.put(arr[i], 1);
            }
        }
         List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
             Comparator<Entry<Character, Integer>> comparator = new Comparator<Entry<Character, Integer>>() {
            @Override
            public int compare(Entry left, Entry right) {
                if(right.getValue()!=left.getValue()){
                  return (int) right.getValue() - (int) left.getValue();  
                }else{
                    return String.valueOf(left.getKey()).compareTo(String.valueOf(right.getKey()));
                }
            }
        };
         Collections.sort(list, comparator);
           Iterator<Entry<Character, Integer>> it = list.iterator();
           System.out.println("Result:\n");
           int times = 0;
           char toPrint;
           Entry<Character, Integer> entry;
           while(it.hasNext()){
               entry = it.next();
               times = entry.getValue();
               toPrint = entry.getKey();
               for(int i=0; i<times; i++){
                   System.out.print(toPrint);
               }
               
           }
           System.out.println("\n");
    }
    
    /**
     * String sorting Main Method
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        String given = "25285688";
        SortString sort = new SortString();
        sort.rearrange(given);
    }*/
}
