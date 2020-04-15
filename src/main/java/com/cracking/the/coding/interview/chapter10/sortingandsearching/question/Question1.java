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

import java.util.Arrays;

/**
 * <b>Sorted Merge:</b> You are given two sorted arrays, A and B, where A has a
 * large enough buffer at the end to hold B. Write a method to merge B into A in
 * sorted order.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke{@literal @}gmail.com{@literal>}
 */
public class Question1 {

    /**
     * Merge Array a and b together by starting with the buffer space at the back
     *
     * @param a array a
     * @param b array b
     */
    public void sortedMerge(int[] a, int[] b) {
        int aIndex = a.length - b.length - 1;
        int bIndex = b.length - 1;
        int count = 0;
        while(aIndex>=0 || bIndex>=0){
            if(aIndex<0){
                a[a.length-1-count] = b[bIndex]; 
                 bIndex--;
                 count++;
                 continue;
            }else if(bIndex<0){
                a[a.length-1-count] = a[aIndex];
                aIndex--;
                count++;
                continue;
            }
            if(a[aIndex]>=b[bIndex]){
                a[a.length-1-count] = a[aIndex];
                aIndex--;
            }else{
                a[a.length-1-count] = b[bIndex];
                bIndex--;
            }
            count++;
            continue;
        }
        
        System.out.println("\nArray A (After Sorted Merge): "+Arrays.toString(a));
    }
    
    /**
     * Sorting and Searching Question 1 main/test method
     * @param args command line arguments
     *//*
    public static void main(String[] args){
        int[] a = new int[8];
        a[0] = 2;
        a[1] =3;
        a[2] = 9;
        a[3] = 19;
        int[] b = {1, 3, 8, 29};
        Question_1 q1 = new Question_1();
        System.out.println("Array A (Before Sorted Merge): "+Arrays.toString(a));
        System.out.println("Array B (Beffore Sorted Merge): "+Arrays.toString(b));
        q1.sortedMerge(a, b);
    }*/
}
