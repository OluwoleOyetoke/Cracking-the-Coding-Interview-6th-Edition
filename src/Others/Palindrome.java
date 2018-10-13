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
 * <b>Longest Palindromic Substring:</b> Find the longest palindromic substring
 * in a string
 *
 * @author Oluwole Oyetoke oluwoleoyetoke@gmail.com
 */
public class Palindrome {

    int callCount = 0;

    /**
     * Using the Manacher's algorithm as explained in some Fred Akalina and
     * Prismo Skills articles
     *
     * @see <a href="https://www.akalin.com/longest-palindrome-linear-time">Fred
     * Akalin Explanation</a>
     * @see
     * <a href="https://prismoskills.appspot.com/lessons/Dynamic_Programming/Chapter_29_-_Longest_Palindrome_In_String.jsp">Prismo
     * Skills</a>
     *
     * @param in input string
     */
    public void longestPalindrome(String in) {
        System.out.println("\nMANACHER'S METHOD");
        //quick exit cases
        if (in == null) {
            System.out.println("Input string is null");
            return;
        } else if (in.length() == 1) {
            System.out.println("Size of the Longest Palindorme is: 1");
            return;
        }

        callCount = 0;
        int longest = 0;
        int rightEdge = -1;
        int leftEdge = -1;
        int anchor = 0;
        int currentLength = 0;
        int mirrorPosition = 0;
        int mirrorValue = 0;

        //pad string
        in = stuff(in);

        int[] tracker = new int[in.length()];
        for (int i = 0; i < in.length(); i++) {
            if (i <= rightEdge) {//check its mirror around centrum
                mirrorPosition = anchor - (i - anchor);
                mirrorValue = tracker[mirrorPosition];
                if (rightEdge >= in.length() - 1) {//if the current palindrome is an actual suffix to the entire string itself
                    tracker[i] = mirrorValue;
                } else if ((mirrorPosition - mirrorValue) > leftEdge) {
                    tracker[i] = mirrorValue;
                } else {
                    currentLength = palindromeLength(in, i);
                    if (currentLength > 0) {
                        longest = Integer.max(currentLength, longest);
                        tracker[i] = currentLength;
                        leftEdge = i - currentLength;
                        rightEdge = i + currentLength;
                        anchor = i;
                    }
                }
            } else {
                currentLength = palindromeLength(in, i);
                if (currentLength > 0) {
                    longest = Integer.max(currentLength, longest);
                    tracker[i] = currentLength;
                    leftEdge = i - currentLength;
                    rightEdge = i + currentLength;
                    anchor = i;
                }
            }

        }
        System.out.println("Tracker Array: " + Arrays.toString(tracker));
        System.out.println("Size of the Longest Palindorme is: " + longest);
        System.out.println("Palindrome Computation Function Was Called " + callCount + " Times");
    }

    /**
     * Naive implementation whereby we treate every character as a palindromic
     * centre and try to find how long this palindrome possibly is. Keep track
     * of the result f each attempt and take note of the largest returned value
     * of them all. This is the longest palindrome
     *
     * @param in input string
     */
    public void naiveLongestPalindrome(String in) {
        System.out.println("\nNAIEVE METHOD");
        //quick exit cases
        if (in == null) {
            System.out.println("Input string is null");
            return;
        } else if (in.length() == 1) {
            System.out.println("Size of the Longest Palindorme is: 1");
            return;
        }
        //pad string
        in = stuff(in);

        callCount = 0;
        int longest = 0;
        for (int i = 0; i < in.length(); i++) {
            longest = Integer.max(palindromeLength(in, i), longest);
        }
        System.out.println("Size of the Longest Palindorme is: " + longest);
        System.out.println("Palindrome Computation Function Was Called " + callCount + " Times");
    }

    /**
     * Finds the length of the palindrome
     *
     * @param in input string
     * @param centreSpot centre index
     * @return length length of palindrome (if exist0
     */
    private int palindromeLength(String in, int centreSpot) {
        callCount++;
        int left = centreSpot - 1;
        int right = centreSpot + 1;
        if (left < 0 || right >= in.length()) {
            return 0;
        }
        int count = 0;
        while (true) {
            if (left < 0 || right >= in.length()) {
                return count;
            } else if (in.charAt(left) == in.charAt(right)) {
                count++;
                left--;
                right++;
            } else {
                return count;
            }
        }
    }

    /**
     * Used to stuff palnidrome with special characters if it is even in length.
     * To make it odd
     *
     * @param in input string
     * @return stuffedStrung stuffed string
     */
    private String stuff(String in) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            sb.append("#");
            sb.append(in.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }

    /**
     * Main/Test Method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        String test = "dabacabacab";
        Palindrome pal = new Palindrome();
        pal.longestPalindrome(test);
        pal.naiveLongestPalindrome(test);
    }*/
}
