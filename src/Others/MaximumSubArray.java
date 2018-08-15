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
package Others;

import java.util.Arrays;

/**
 * Write an efficient program to find the sum of contiguous subarray within a
 * one-dimensional array of numbers which has the largest sum.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}gmail.com{@literal >}
 */
public class MaximumSubArray {

    /**
     * Use Kadanes algorithm
     *
     * @param input input array
     * @return answer solution
     */
    public int solve(int[] input) {
        if (input == null) {
            return 0;
        } else if (input.length == 1) {
            return input[0];
        }
        
        int previousMax = input[0];
        int maxMax = previousMax;
        for (int i = 1; i < input.length; i++) {
            if ((previousMax+input[i])>=input[i] ) {
                previousMax = input[i]+ previousMax;
            } else {
                previousMax = input[i];
            }
            if(previousMax>maxMax){
                maxMax = previousMax;
            }
        }
        return maxMax;
    }

    /**
     * Main function...uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
        //int[] nums = {2, 7, 11, 15, -5, -1, 3, 1, 6, 9, -12};
        // int[] nums3 = {-2, -6,-1, -9};

        MaximumSubArray max = new MaximumSubArray();
        int answer = max.solve(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Answer: " + answer);
    }*/
}
