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
package com.cracking.the.coding.interview.chapter08.recursionanddynamicprog.question;

import static java.lang.Math.floor;

/**
 * <b>Magic Index:</b> A magic index in an array A [ 0 ... n -1] is defined to be an index such that A[ i] =
 * i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
 * array A.
 * FOLLOW UP
 * What if the values are not distinct?
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question3 {
    int[] array = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 13, 14, 15, 16};

    public void magicIndex() {
        solve(0, array.length - 1, array.length);
    }

    /**
     * Use binary sort...Assuming values are distinct
     * @param start start position
     * @param stop stop position
     * @param length length
     */
    public void solve(int start, int stop, int length){
        
        if(length==2){
            if(array[start]==start) System.out.println("Magic found at position: "+start);
            else if(array[start+1]==start+1) System.out.println("Magic found at position: "+start+1);
            else System.out.println("No Magic Number");
            return;
        }

        int midIndex = (int) (floor(length/2) - 1);
        midIndex = midIndex+start;
        
        if(array[midIndex]>midIndex ){
            solve(start,midIndex, (midIndex-start)+1); //move leftwards
        }else if(array[midIndex]<midIndex ){
            solve(midIndex+1,stop, stop-midIndex); //move rightwards
        }else if(array[midIndex]==midIndex ){
            System.out.println("Magic found at position: "+midIndex);
            return;
        }
           
    }
    
    
    /**
     * Recursion/Dynamic Programming question 3 main method....uncomment to run
     * @param args command line arguments
     *//*
    public static void main(String[] args){
        Question_3 q3 = new Question_3();
        q3.magicIndex();
    }*/
}
