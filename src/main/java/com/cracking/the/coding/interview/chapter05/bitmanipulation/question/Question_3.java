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
package com.cracking.the.coding.interview.chapter05.bitmanipulation.question;

/**
 * <b>Flip Bit to Win:</b> You have an integer and you can flip exactly one bit
 * from a 0 to a 1. Write code to find the length of the longest sequence of ls
 * you could create.
 *
 * EXAMPLE Input: 1775 (or: 11011101111)
 *
 * Output: 8
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_3 {

    public int flipToWin(String binary) {
        if (binary == null) {
            return -1;
        }

        //get length of binary
        char[] arr = binary.toCharArray();

        int count = 0;
        int longest = 0;

        int where = 0;
        int longestWhere = -1;

        int hits = 0;

        int beforeMe = 0;
        int afterMe = 0;

        int defaultOnes = 0;
        int longestDefaultOnes = 0;

        //iterate trhough binary bits
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == '1') {
                count++;
                defaultOnes++;
                if (defaultOnes >= longestDefaultOnes) {
                    longestDefaultOnes = defaultOnes;
                }
            } else {
                hits++;
                if (defaultOnes >= longestDefaultOnes) {
                    longestDefaultOnes = defaultOnes;
                }

                defaultOnes = 0;
                if (hits == 1) {
                    beforeMe = count;
                    where = i;
                    count++;
                } else if (hits == 2) {
                    afterMe = count - (beforeMe + 1);
                    hits = 1;
                    if (count >= longest) {
                        longest = count;
                        longestWhere = where;
                    }
                    beforeMe = afterMe;
                    where = i;
                    count = beforeMe + 1; //reset count    
                }

            }

        }

        if (count >= longest && count > longestDefaultOnes) {
            longest = count;
            longestWhere = where;
        }

        int lngst = 0;
        int posFromRight = 0;
        if (longestDefaultOnes > longest) {
            System.out.println("longest contigious bits of 1s in the binary > the ones realized by fliping a zero");
            lngst = longestDefaultOnes;
        } else {
            lngst = longest;
            posFromRight = binary.length() - longestWhere - 1;
            System.out.println("Flip bits in position " + posFromRight + " of (" + binary + ") to get a long stream of " + lngst + " ones");
            //System.out.println("Position counted from the right");
        }
        return lngst;
    }

    /**
     * Bit Manipulation Question_3 main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_3 q3 = new Question_3();

        q3.flipToWin("11011101111");
        q3.flipToWin("10110111");
        q3.flipToWin("0000");
        q3.flipToWin("1111");
        q3.flipToWin("1110");
        q3.flipToWin("0");
        q3.flipToWin("1");
    }*/

}
