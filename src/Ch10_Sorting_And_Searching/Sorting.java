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
 * Sorting
 * @author Oluwole Oyetoke {@literal <}
 * oluwoleoyetoke{@literal @}gmail.com{@literal >}
 */
public class Sorting {

    /**
     * Bubble biggest values to the end at each iteration. Run time complexity
     * 0(n*n)
     *
     * @param input Input
     * @return result sorted array
     */
    public int[] bubbleSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - 1 - i; j++) {
                if (input[j] > input[j + 1]) {
                    input[j] = input[j] + input[j + 1];
                    input[j + 1] = input[j] - input[j + 1];
                    input[j] = input[j] - input[j + 1];
                }
            }
        }
        return input;
    }

    /**
     * Scan through array for the smallest element during each iteration. Swap
     * smallest element with spot where current iteration started from. Run time
     * complexity 0(n*n)
     *
     * @param input Input
     * @return result sorted array
     */
    public int[] selectionSort(int[] input) {
        int lowest = 0;
        int pos = 0;
        for (int i = 0; i < input.length - 1; i++) {
            lowest = input[i];
            pos = i;
            for (int j = i; j < input.length; j++) {
                if (input[j] < lowest) {
                    lowest = input[j];
                    pos = j;
                }
            }
            input[i] = input[i] + input[pos];
            input[pos] = input[i] - input[pos];
            input[i] = input[i] - input[pos];
        }
        return input;
    }

    /**
     * recursively divide array into to and rebuild it back. But before every
     * rebuild attempt, sort the array, build and return. At the end of all the
     * build activities, the finally return array will be already sorted.
     * Runtime complexity is somewhere around 0(nlogn)
     *
     * @param input input array
     * @return result sorted array
     */
    public int[] mergeSort(int[] input) {
        if (input == null || input.length == 0) {
            System.out.println("Throw error");
        }
        int inputLength = input.length;
        if (inputLength == 1) {
            return input;
        }

        int pivotingIndex = (int) Math.floor(inputLength / 2);
        int[] left = new int[pivotingIndex];
        int[] right = new int[inputLength - pivotingIndex];
        for (int i = 0; i < inputLength; i++) {
            if (i < pivotingIndex) {
                left[i] = input[i];
            } else {
                right[i - pivotingIndex] = input[i];
            }
        }
        int[] resultLeft = mergeSort(left);
        int[] resultRight = mergeSort(right);

        //sort out left and right
        int[] result = new int[inputLength];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (leftIndex >= resultLeft.length) {
                result[i] = resultRight[rightIndex];
                rightIndex++;
            } else if (rightIndex >= resultRight.length) {
                result[i] = resultLeft[leftIndex];
                leftIndex++;
            } else if (resultLeft[leftIndex] <= resultRight[rightIndex]) {
                result[i] = resultLeft[leftIndex];
                leftIndex++;
            } else if (resultRight[rightIndex] < resultLeft[leftIndex]) {
                result[i] = resultRight[rightIndex];
                rightIndex++;
            }
        }
        return result;
    }

    /**
     * move one step, then burble back this variable to its appropriate space.
     * Runtime complexity of 0(n*n)
     *
     * @param input input
     * @return result sorted array
     */
    public int[] insertionSort(int[] input) {
        int loc = 0;
        for (int i = 0; i < input.length; i++) {
            loc = i;
            while ((loc - 1) >= 0) {
                if (input[loc - 1] > input[loc]) {
                    input[loc] = input[loc] + input[loc - 1];
                    input[loc - 1] = input[loc] - input[loc - 1];
                    input[loc] = input[loc] - input[loc - 1];
                } else {
                    break;
                }
                loc--;
            }
        }
        return input;
    }

    /**
     * Perform quick sort
     *
     * @param input input array to sort
     * @param start start index
     * @param stop stop index
     */
    public void quickSort(int[] input, int start, int stop) {;
        if (start < stop) {
            int newPivotIndex = partition(input, start, stop);
            quickSort(input, start, newPivotIndex - 1);
            quickSort(input, newPivotIndex + 1, stop);
        }
    }

    /**
     * Perform quick sort operation on the chunk of provided input array.
     * Starting from 'start' to 'end'
     *
     * @param input input array
     * @param start start index
     * @param end end index
     * @return partitionIndex new partition index
     */
    private int partition(int[] input, int start, int end) {
        int indexOne = start;
        int temp;
        int pivotVal = input[end];
        for (int i = start; i < end; i++) {
            if (input[i] < pivotVal) {
                //swap(input, i, indexOne);
                temp = input[indexOne];
                input[indexOne] = input[i];
                input[i] = temp;
                indexOne++;
            }
        }
        swap(input, indexOne, end);
        return indexOne;
    }

    /**
     * Swap array content in index a with index b
     *
     * @param input input
     * @param a index a
     * @param b index b
     */
    private void swap(int[] input, int a, int b) {
        input[a] = input[a] + input[b];
        input[b] = input[a] - input[b];
        input[a] = input[a] - input[b];
    }

    public void searchIndexOf(int[] arr, int toFind, int start, int end) {
        if (start == end || end < start) {
            if (arr[start] != toFind) {
                System.out.println("Not Found");
                return;
            } else {
                System.out.println("Index of " + toFind + " is " + start);
                return;
            }
        }

        int pivot = (int) Math.floor((start + end) / 2);
        if (arr[pivot] == toFind) {
            System.out.println("Index of " + toFind + " is " + pivot);
        } else if (toFind < arr[pivot]) {
            searchIndexOf(arr, toFind, start, pivot - 1); //move leftwards
        } else {
            searchIndexOf(arr, toFind, pivot + 1, end); //move rightwards
        }
    }

    /**
     * Main/test method for sorting class...uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        int[] toSort = {9, 8, 0, 12, -1, 13, 88, 56, 432, 3, 3, 4, 2, 1, 7, 5};
        int[] result;
        Sorting sort = new Sorting();
     */
 /*
        System.out.println("Buble Sort: " + Arrays.toString(toSort));
        result = sort.bubbleSort(toSort);
        System.out.println("Answer: " + Arrays.toString(result) + "\n");
     */

 /*  
        System.out.println("Selection Sort: " + Arrays.toString(toSort));
        result = sort.selectionSort(toSort);
        System.out.println("Answer: " + Arrays.toString(result) + "\n");
     */
 /*
        System.out.println("Insertion Sort: " + Arrays.toString(toSort));
        result = sort.insertionSort(toSort);
        System.out.println("Answer: " + Arrays.toString(result) + "\n");
     */
 /*
        System.out.println("Merge Sort: " + Arrays.toString(toSort));
        result = sort.mergeSort(toSort);
        System.out.println("Answer: " + Arrays.toString(result) + "\n");
     */
 /*
        System.out.println("Quick Sort: " + Arrays.toString(toSort));
        sort.quickSort(toSort, 0, toSort.length - 1);
        System.out.println("Answer: " + Arrays.toString(toSort) + "\n");
     */
 /*
        int[] toSearch = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        sort.searchIndexOf(toSearch, -9, 0, toSearch.length - 1);
     */
//    }
}
