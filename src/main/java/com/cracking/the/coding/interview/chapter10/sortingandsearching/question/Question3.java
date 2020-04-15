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
package com.cracking.the.coding.interview.chapter10.sortingandsearching.question;

/**
 * Search in Rotated Array: Given a sorted array of n integers that has been
 * rotated an unknown number of times, write code to find an element in the
 * array. You may assume that the array was originally sorted in increasing
 * order.
 *
 * EXAMPLE lnput: find 5 in{l5, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14} Output: 8
 * (the index of 5 in the array)
 *
 * @author Oluwole Oyetoke oluwoleoyetoke@gmail.com
 */
public class Question3 {

    /**
     * Use binary search
     *
     * @param toFind integer to find
     * @param arr    array to search in
     * @return true/false true if found and false otherwise
     */
    public boolean find(int toFind, int[] arr) {
        return find(toFind, arr, 0, arr.length - 1);
    }

    /**
     * Find using binary search. Check to be sure that the portion of the array
     * being searched with BS in sorted. Else use a for loop
     *
     * @param toFind number to find
     * @param arr array to search
     * @param start start position
     * @param stop stop position
     * @return true/false true if found and false otherwise
     */
    private boolean find(int toFind, int[] arr, int start, int stop) {
        //Exit cases
        if (arr.length == 1 && arr[start] != toFind) {
            return false;
        } else if (arr.length == 1 && arr[start] == toFind) {
            return true;
        } else if (stop >= arr.length || start>arr.length) {
            return false;
        } else if (start < 0 || stop<0) {
            return false;
        }

        //Find pivot
        int pivot = (int) Math.floor((double) (start + stop) / 2);

        //is value = pivot
        if (arr[pivot] == toFind) {
            return true;
        }

        //is end of the array reached
        if ((pivot + 1) >= arr.length) {
            return false;
        }

        if ((arr[pivot + 1] <= arr[stop])) {
            if ((arr[pivot + 1] <= toFind) && arr[stop] >= toFind) {
                return find(toFind, arr, pivot + 1, stop);
            } else {
                return find(toFind, arr, start, pivot - 1);
            }
        } else if ((arr[start] <= arr[pivot - 1])) {
            if ((arr[start] <= toFind) && arr[pivot - 1] >= toFind) {
                return find(toFind, arr, start, pivot - 1);
            } else {
                return find(toFind, arr, pivot + 1, stop);
            }
        } else {
            for (int i = start; i <= stop; i++) {
                if (arr[i] == toFind) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Main method for sorting and searching question 3
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        Question_3 q3 = new Question_3();
        int toFind = 15;
        int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        boolean answer = q3.find(toFind, arr);
        System.out.println("Is " + toFind + " in " + Arrays.toString(arr) + ": " + answer);
    }*/
}
