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

/**
 * <b>Longest Common Substring</b>
 *
 * @author Oluwole Oyetoke oluwoleoyetoke@gmail.com
 */
public class SubString {

    /**
     * Use dynamic programming to find longest substring
     *
     * @param in1 input string 1
     * @param in2 input string 2
     * @return longest longest substring
     */
    public String longestSubstring(String in1, String in2) {
        int[][] space = new int[in1.length() + 1][in2.length() + 1];
        int max = 0;
        int maxPos = 0;
        for (int i = 0; i < in1.length(); i++) {
            for (int j = 0; j < in2.length(); j++) {
                if (in1.charAt(i) == in2.charAt(j)) {
                    space[i + 1][j + 1] = space[i][j] + 1;
                } else {
                    space[i + 1][j + 1] = 0;
                }
                if (space[i + 1][j + 1] >= max) {
                    max = space[i + 1][j + 1];
                    maxPos = j;
                }
            }
        }

        return in2.substring(maxPos - (max - 1), maxPos + 1);
    }

    /**
     * Main/Test method
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        SubString sub = new SubString();
        String in1 = "ABAB";
        String in2 = "BABA";
        System.out.println("Longest Common Substring Between " + in1 + " and " + in2 + " is: " + sub.longestSubstring(in1, in2));
    }*/
}
