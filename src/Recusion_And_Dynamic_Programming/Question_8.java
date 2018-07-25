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
package Recusion_And_Dynamic_Programming;

/**
 * <b>Permutations with Dups:</b> Write a method to compute all permutations of a
 * string whose characters are not necessarily unique. The list of permutations
 * should not have duplicates
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_8 {

    int count = 0;

    /**
     * Find permutations of the string
     *
     * @param toPermute string containing no duplicates
     */
    public void permute(String toPermute) {
        doPermute(toPermute, 0);
        System.out.println(count + " permutations");
    }

    /**
     * recursive permute
     *
     * @param toPermute string to find its permutations
     * @param position position to concentrate on
     */
    private void doPermute(String toPermute, int position) {
        if (toPermute == null) {
            System.out.println("Empty String Received");
            return;
        }
        if (position == toPermute.length()) {
            System.out.println(toPermute);
            count++;
        }

        char[] strArr = toPermute.toCharArray();
        String temp;
        for (int i = position; i < strArr.length; i++) {
            temp = swap(strArr, position, i);
            if (!checkExist(strArr, strArr[position], i+1)) {
                doPermute(new String(temp), position + 1);
            }
        }
    }

    /**
     * Swap char in two locations
     *
     * @param strArr char array to check
     * @param a index a
     * @param b index b
     * @return swapped string with indexes a,b swapped
     */
    private String swap(char[] strArr, int a, int b) {
        char temp = strArr[a];
        strArr[a] = strArr[b];
        strArr[b] = temp;
        return new String(strArr);
    }
    
    /**
     * Checks to know if the current character has duplicates ahead
     * @param strArr string array
     * @param toCheck character to search for
     * @param startIndex index to start search from
     * @return true/flase true if duplicate exists
     */
    private boolean checkExist(char[] strArr, char toCheck, int startIndex) {
        for (int i = startIndex; i < strArr.length; i++) {
            if (strArr[i] == toCheck) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recursion and Dynamic Programming Question 8 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_8 permutations = new Question_8();
        permutations.permute("ABA");
    }*/
}
