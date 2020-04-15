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

/**
 * <b>Recursive Multiply:</b> Write a recursive function to multiply two
 * positive integers without using the operator.You can use addition,
 * subtraction, and bit shifting, but you should minimize the number of those
 * operations.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question5 {

    /**
     * If number is a factor of two, simply use binary shifting
     *
     * @param a number a
     * @param b number b
     */
    public void multiply(int a, int b) {
        if (b == 0) {
            System.out.println(a + " multiplied by " + b + " = 0");
            return;
        } else if (a < 0 || b < 0) {
            System.out.println("Only positive integers accepted");
            return;
        }

        int numberOfShifts;
        if (a % 2 == 0) {
            System.out.println("Binary Shift");
            numberOfShifts = a / 2;
            int answer = binaryShift(b, numberOfShifts, 0, 0);
            System.out.println(a + " multiplied by " + b + " = " + answer);
        } else if (b % 2 == 0) {
            System.out.println("Binary Shift");
            numberOfShifts = b / 2;
            int answer = binaryShift(a, numberOfShifts, 0, 0);
            System.out.println(a + " multiplied by " + b + " = " + answer);
        } else {
            recursiveMultiply(a, b);
        }
    }

    /**
     * Double using binary shift
     *
     * @param toShift number to be doubled
     * @param noOfShifts total number of required shifts
     * @param shiftCount shifts so far
     * @param answer answer
     * @return answer multiplication result
     */
    private int binaryShift(int toShift, int noOfShifts, int shiftCount, int answer) {
        if (shiftCount == noOfShifts) {
            return answer;
        }
        answer = answer + (toShift << 1);
        answer = binaryShift(toShift, noOfShifts, shiftCount + 1, answer);
        return answer;
    }

    /**
     * Multiply two numbers without using the * sign
     *
     * @param a number a
     * @param b number b
     */
    private void recursiveMultiply(int a, int b) {
        System.out.println("Recursive Multiply");
        int answer = mult(a, a, b - 1);
        System.out.println(a + " multiplied by " + b + " = " + answer);
    }

    /**
     *
     * @param val value
     * @param constant constant to keep adding
     * @param level number of multiplications
     * @return val multiplied value through addition
     */
    private int mult(int val, int constant, int level) {
        if (level == 0) {
            return val;
        }
        val = val + constant;
        val = mult(val, constant, level - 1);
        return val;
    }

    /**
     * Recursion and Dynamic Programing Question 5 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_5 rMultiply = new Question_5();
        rMultiply.multiply(8, 8);
    }*/
}
