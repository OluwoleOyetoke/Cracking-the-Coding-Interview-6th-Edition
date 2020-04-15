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
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target. You may assume that each input would have
 * exactly one solution, and you may not use the same element twice.
 *
 * Example: Given nums = [2, 7, 11, 15], target = 9, return [0, 1]
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class TwoSum {

    /**
     * Using a hash map
     *
     * @param input input array
     * @param target target sum
     * @return answer two index whose content sum up to the target
     */
    public int[] solve(int[] input, int target) {
        if (input == null || input.length < 2) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean check = false;
        int opposite = 0;
        for (int i = 0; i < input.length; i++) {
            opposite = target - input[i];
            check = map.containsKey(opposite);
            if (check) {
                return new int[]{map.get(opposite), i};
            } else {
                map.put(input[i], i);
            }
        }
        return null;
    }

    /**
     * Main function...uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args){
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum sum = new TwoSum();
        int[] answer = sum.solve(nums, target);
        System.out.println("Input: "+Arrays.toString(nums));
        System.out.println("Target: "+target);
        System.out.println("Answer: "+Arrays.toString(answer));
    }*/
}
