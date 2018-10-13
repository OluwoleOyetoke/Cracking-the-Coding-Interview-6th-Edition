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
 * Rotate an Array in place
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class RotateArray {
    
    /**
     * Rotate array (left or right shift)
     * @param arr array to rotate
     * @param places number of places to shift
     * @param type 0 = left rotation 1 = right rotation
     */
    public void rotate(int[] arr, int places, int type) {
        System.out.println("Array to be rotated: " + Arrays.toString(arr));
         int len = arr.length - 1;
        if (type == 0) {//left shift
            reverse(arr, 0, len);
            reverse(arr, 0, len-places);
            reverse(arr,len-places+1, len);
        }
        else {//right shift
            reverse(arr, 0, len);
            reverse(arr, 0, places-1);
            reverse(arr, places, len);
        }
        System.out.println("Rotated Arr By " + places + " placces: " + Arrays.toString(arr));
    }
    
    /***
     * Used to reverse array in place
     * @param arr array to be reversed
     * @param start start position
     * @param stop end position
     */
    private void reverse(int[] arr, int start, int stop) {
        while (start < stop) {
            //swap
            arr[start] = arr[start] + arr[stop];
            arr[stop] = arr[start] - arr[stop];
            arr[start] = arr[start] - arr[stop];

            start++;
            stop--;
        }
    }
    
    /**
     * Array Rotation Main Method
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        RotateArray rot = new RotateArray();
        rot.rotate(arr, 10, 0);
    }*/
}
