/*
 * Copyright (C) 2018 Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This prsogram is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cracking.the.coding.interview.chapter08.recursionanddynamicprog.question;

/**
 * <b>Permutation without Dups:</b> Write a method to compute all permutations of a string of unique
 * characters
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question7 {

    /**
     * Find permutations of the string
     * @param toPermute string containing no duplicates
     */
    public void permute(String toPermute) {
        doPermute(toPermute, toPermute.length() - 1, 0);
    }
    
    
    /**
     * recursive permute
     * @param toPermute string to find its permutations
     * @param position position to concentrate on
     * @param count number of iterations
     */
    public void doPermute(String toPermute, int position, int count){
        if(toPermute==null){
            System.out.println("Empty String Received");
            return;
        }
        if(position==0){
            System.out.println(toPermute);
        }    
        
        char[] strArr = toPermute.toCharArray();
        String temp;
        String newStr;
        for(int i=0; i<strArr.length-count; i++){
            temp = pop(strArr, position);
            newStr = strArr[position]+""+temp;
            strArr = newStr.toCharArray();
            doPermute(newStr, position-1, count+1);
        }    
    }
    
    /**
     * Pop string from a particular index
     * @param strArr char array to check
     * @param index index to pop out
     * @return poped poped string
     */
    public String pop(char[] strArr, int index){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<strArr.length; i++){
            if(i!=index){
                sb.append(strArr[i]);
            }
        }
        return sb.toString();
    }
    
      /**
     * Recursion and Dynamic Programing Question 7 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_7 permutations = new Question_7();
        permutations.permute("ABC");
    }*/
}
