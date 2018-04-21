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
package Arrays_And_Strings;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 */
public class Question_1 {

    /**
     * Method 1: Using hash maps
     *
     * @param input String to check
     * @return true/false
     */
    public static boolean solve(String input) {
        if (input == null) {
            return false;
        }
        String current;
        boolean check = false;
        HashMap<String, Integer> map = new HashMap();
        current = input.substring(0, 1);
        map.put(current, 1);
        for (int i = 1; i < input.length(); i++) {
            current = input.substring(i, i + 1);
            check = map.containsKey(current);
            if (check == true) {
                return false;
            } else {
                map.put(current, 1);
            }
        }
        return true;
    }

    /**
     * Method 2: Without an available data structure. Two for loops - O(N^2)
     * time complexity, where N = input.length()
     *
     * @param input String to check
     * @return true/false
     */
    static boolean solve2(String input) {
        if (input == null) {
            return false;
        }
        String current, others;
        boolean check = false;
        for (int i = 0; i < input.length(); i++) {
            current = input.substring(i, i + 1);
            for (int j = 0; j < input.length(); j++) {
                others = input.substring(j, j + 1);
                if (i != j && current.equals(others)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method 3: Without an available data structure. Assuming the ASCII
     * character set - O(N) time complexity, where N = input.length()
     *
     * @param input String to check
     * @return true/false
     */
    static boolean solve3(String input) {
        if (input == null) {
            return false;
        } else if (input.length() > 128) {
            return false;
        }
        boolean[] charSet = new boolean[128];
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            index = (int) input.charAt(i);
            if (charSet[index] == true) {
                return false;
            } else {
                charSet[index] = true;
            }
        }
        return true;
    }

    /**
     * Method 4: Without an available data structure. Sort then check linearly
     *
     * @param input String to check
     * @return true/false
     */
    static boolean solve4(String input) {
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sortedString = new String(charArray);
        for (int i = 0; i < sortedString.length() - 1; i++) {
            if (sortedString.charAt(i) == sortedString.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method 5: Without an available data structure. Using binary savings.
     * assume only char [a-z]. Time complexity 0(N), where N = input.length()
     *
     * @param input String to be checked
     * @return true/false
     */
    static boolean solve5(String input) {
        if (input == null) {
            return false;
        }
        int intVal;
        int oldSpace = 0;//an integer is 32 bit
        int newSpace = 0;
        for (int i = 0; i < input.length(); i++) {
            intVal = (int) input.charAt(i) - 97;
            newSpace = (1 << intVal) ^ oldSpace;
            if (newSpace < oldSpace) {
                return false;
            } else {
                oldSpace = newSpace;
            }
        }
        return true;
    }

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean answer;
        String nonUnique = "Hello, how are you doing";
        String unique = "nbcdefghijklma";
        answer = solve(nonUnique);
        System.out.println("With Hashmaps, " + nonUnique + ": " + answer);
        answer = solve(unique);
        System.out.println("With Hashmaps, " + unique + ": " + answer);

        answer = solve2(nonUnique);
        System.out.println("With No Data Structure, " + nonUnique + ": " + answer);
        answer = solve2(unique);
        System.out.println("With No Data Structure, " + unique + ": " + answer);

        answer = solve3(nonUnique);
        System.out.println("With No Data Structure, " + nonUnique + ": " + answer);
        answer = solve3(unique);
        System.out.println("With No Data Structure, " + unique + ": " + answer);

        answer = solve4(nonUnique);
        System.out.println("With No Data Structure, " + nonUnique + ": " + answer);
        answer = solve4(unique);
        System.out.println("With No Data Structure, " + unique + ": " + answer);

        answer = solve5(nonUnique);
        System.out.println("With No Data Structure, " + nonUnique + ": " + answer);
        answer = solve5(unique);
        System.out.println("With No Data Structure, " + unique + ": " + answer);
    }
}
