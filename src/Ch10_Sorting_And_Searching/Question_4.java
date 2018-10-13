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

/**
 * Sorted Search, No Size: You are given an array-like data structure Listy
 * which lacks a size method. It does, however, have an elementAt (i) method
 * that returns the element at index i in 0( 1) time. If i is beyond the bounds
 * of the data structure, it returns -1. (For this reason, the data structure
 * only supports positive integers.) Given a Listy which contains sorted,
 * positive integers, find the index at which an element x occurs. If x occurs
 * multiple times, you may return any index.
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class Question_4 {

    /**
     * Find a number in a sorted list where we cannot ascertain the size of the
     * list at search time
     *
     * @param toFind to find
     * @param list list
     * @return true/false true if found and false, otherwise
     */
    public boolean find(int toFind, Listy list) {
        int previousIndex = 0;
        int currentIndex = 1;
        int result = list.elementAt(previousIndex);
        if (result == toFind) {
            return true;
        } else if (result == -1) {
            return false;
        }
        int overshoots = 0;
        while (true) {
            result = list.elementAt(currentIndex);
            if (result == toFind) {
                return true;
            } else if (result < toFind && result != -1) {
                previousIndex = currentIndex;
                currentIndex = currentIndex * 2;
            } else {
                overshoots++;
                if (overshoots > 1) {
                    currentIndex = previousIndex;
                    while (true) {
                        result = list.elementAt(currentIndex);
                        if (result == toFind) {
                            return true;
                        } else if (result == -1) {
                            return false;
                        } else {
                            currentIndex++;
                        }
                    }
                } else {
                    currentIndex = (int) Math.floor((double) (currentIndex + previousIndex) / 2);
                }
            }

        }
    }

    /**
     * Listy data structure
     */
    static class Listy {

        private int SIZE;
        int[] data;

        Listy(int size) {
            SIZE = size;
            data = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                data[i] = i * 3;
            }
        }

        public int elementAt(int index) {
            if (index >= SIZE) {
                return -1;
            } else {
                return data[index];
            }
        }
    }

    /**
     * Main method for sorting and searching question 3
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        Question_4 q4 = new Question_4();
        int toFind = 66;
        Listy listy = new Listy(40);
        int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        boolean answer = q4.find(toFind, listy);
        System.out.println("Is " + toFind + " in " + Arrays.toString(listy.data) + ": " + answer);
    }*/
}
