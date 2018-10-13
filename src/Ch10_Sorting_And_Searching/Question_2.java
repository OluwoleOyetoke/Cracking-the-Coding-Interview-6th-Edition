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

import java.util.Arrays;
import java.util.Comparator;

/**
 * <b>Group Anagrams:</b> Write a method to sort an array of strings so that all
 * the anagrams are next to each other.
 *
 * @author Oluwole Oyetoke
 * {@literal <}oluwoleoyetoke{@literal @}gmail.com{@literal>}
 */
public class Question_2 {

    /**
     * Sorts to bring anagrams in an array closer to each other. This uses a
     * custom built sort class which extends comparator
     *
     * @param arr array to sort
     */
    public void anagramSort(String[] arr) {
        Arrays.sort(arr, new CustomCompare());
        System.out.println("Array A (After Anagram Sort): " + Arrays.toString(arr));
    }

    /**
     * Sorting and Searching Question 2 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        String[] stringArray = {"tar", "elbow", "inch", "below", "dusty", "study", "chin", "rat"};
        Question_2 q2 = new Question_2();
        System.out.println("Array A (Before Anagram Sort): " + Arrays.toString(stringArray));
        q2.anagramSort(stringArray);
    }*/
}


/**
 * Custom sort class
 * @author Oluwole Oyetoke
 */
class CustomCompare implements Comparator<String> {

    @Override
    public int compare(String lhs, String rhs) {
        char[] lCharArr = lhs.toCharArray();
        char[] rCharArr = rhs.toCharArray();

        Arrays.sort(lCharArr);
        Arrays.sort(rCharArr);
        String newLhs = new String(lCharArr);
        String newRhs = new String(rCharArr);
        return newLhs.compareTo(newRhs);
    }
}
