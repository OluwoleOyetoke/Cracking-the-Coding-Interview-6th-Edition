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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>Check Permutation:</b> Given two strings, write a method to decide if
 * one is a permutation of the other.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 */
public class Question_2 {

    /**
     * Find all the permutations of the strings, then check through all
     * permutations to be sure it is not the same as the second string. Assuming
     * that white spaces count
     *
     * @param input String to permute
     * @param check String to check for
     * @return true/false true if it is a permutation of the other
     */
    /**
     * Uses recursive method to find all the permutations of the string, stores
     * all permutations in an array list, then checks if array list contains the
     * check string. Not the best method
     *
     * @param input
     * @param check
     * @return
     */
    static boolean solve(String input, String check) {
        //Quick exit case
        if (input.length() != check.length()) {
            return false;
        }
        List list = new ArrayList();
        list.add(input);
        doPermute(input.toCharArray(), 0, list);
        //System.out.println("Permutations (" + list.size() + "): " + list);
        return list.contains(check);
    }

    /**
     * Sorts both string and checks them linearly. They should be the same if
     * thy are permutations of one another
     *
     * @param input Base string to search against
     * @param check String to check for
     * @return true/false returns true if check is a permutation of input
     */
    static boolean solve2(String input, String check) {
        //Quick exit case
        if (input.length() != check.length()) {
            return false;
        }
        char[] inputArray = input.toCharArray();
        char[] checkArray = check.toCharArray();
        Arrays.sort(inputArray);
        Arrays.sort(checkArray);
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] != checkArray[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Solves by counting the number of times a character appears. In the right
     * case, both check and input should have the same character count.
     *
     * @param input
     * @param check
     * @return
     */
    static boolean solve3(String input, String check) {
        //Quick exit case
        if (input.length() != check.length()) {
            return false;
        }
        int[] inputSpace = new int[128];
        int[] checkSpace = new int[128];
        char[] inputArray = input.toCharArray();
        char[] checkArray = check.toCharArray();

        for (int i = 0; i < inputArray.length; i++) {
            inputSpace[(int) inputArray[i]]++;
            checkSpace[(int) checkArray[i]]++;
        }
        for (int i = 0; i < inputSpace.length; i++) {
            if (inputSpace[i] != checkSpace[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Generates all permutations of the input string (duplicates un-handled)
     * @param inputArray input char array
     * @param position position where swap should start from
     * @param storage list storing new permutations
     */
      static void doPermute(char[] inputArray, int position, List storage) {
        if (position == inputArray.length - 1) {
            //System.out.println(new String(inputArray));
            storage.add(new String(inputArray));
        } 
        char[] tempArray =new char[inputArray.length];
        System.arraycopy(inputArray, 0, tempArray, 0, inputArray.length); //to make sure array isnt passed by reference
        for (int i = position; i < inputArray.length; i++) {
            tempArray = switchPositions(tempArray, position, i);
            doPermute(tempArray, position + 1, storage);
            tempArray = switchPositions(tempArray, position, i);
        }
    }
      
      /**
         * Used to switch spots in an array
         * @param input input char array
         * @param posA position a
         * @param posB position b
         * @return swapped array
         */
    static char[] switchPositions(char[] input, int posA, int posB) {
        char temp = input[posA];
        input[posA] = input[posB];
        input[posB] = temp;
        return input;
    }
    
    /**
     * Get deep copy of array
     * @param input array to copy
     * @return copied array
     */
    private static char[] deepCopyArray(char[] input) {
        if (input == null) {
            return null;
        }
        char[] newCopy = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            newCopy[i] = input[i];
        }
        return newCopy;
    }

    /**
     * Question_2 main
     *
     * @param args the command line arguments
     *//*
    public static void main(String[] args) {
        String word = "QWERTY";
        String check = "YTREWQ";
        boolean answer = false;
        answer = solve(word, check);
        System.out.println("Is " + check + " a permutation of " + word + ": " + answer);
        answer = solve2(word, check);
        System.out.println("Is " + check + " a permutation of " + word + ": " + answer);
        answer = solve3(word, check);
        System.out.println("Is " + check + " a permutation of " + word + ": " + answer);
    }*/
}
