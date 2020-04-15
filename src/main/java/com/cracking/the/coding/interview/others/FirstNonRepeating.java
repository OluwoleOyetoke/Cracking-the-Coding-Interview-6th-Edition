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
package com.cracking.the.coding.interview.others;

import java.util.HashMap;

/**
 * Find the first non repeating character in an array
 *
 * @author Oluwole Oyetoke {@literal <}
 * oluwoleoyetoke{@literal @}gmail.com{@literal >}
 */
public class FirstNonRepeating {
    
    
    /**
     * Assuming characters are only asci based, use character count array to track occurrence;
     * @param input input char array
     */
    public void firstNonRepeating(char[] input) {
        int[] characterCount = new int[256];
        for (int i = 0; i < input.length; i++) {
            characterCount[input[i]]++;
        }
        for (int i = 0; i < input.length; i++) {
            if (characterCount[input[i]] == 1) {
                System.out.println("First Non Repeating Character is: " + input[i]);
                return;
            }
        }
    }
    /**
     * Method 2: Use hash map to track occurence
     * @param input input char array
     */
    public void firstNonRepeating2(char[] input) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (map.containsKey(input[i])) {
                map.put(input[i], map.get(input[i]) + 1);
            } else {
                map.put(input[i], 1);
            }
        }
        for (int i = 0; i < input.length; i++) {
            if (map.get(input[i]) == 1) {
                System.out.println("First Non Repeating Character is: " + input[i]);
                return;
            }
        }
    }

    /**
     * Main method...uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd', 'a', 'c', 'e', 'd', 'c', 'b', 'f', 'g', 'h'};
        FirstNonRepeating nonRepeat = new FirstNonRepeating();
        nonRepeat.firstNonRepeating(arr);
        nonRepeat.firstNonRepeating2(arr);
    }*/
}
