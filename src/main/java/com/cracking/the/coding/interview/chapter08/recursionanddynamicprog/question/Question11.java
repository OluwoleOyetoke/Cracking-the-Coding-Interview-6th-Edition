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
 * <b>Coins:</b> Given an infinite number of quarters (25 cents), dimes (10
 * cents), nickels (5 cents), and pennies (1 cent), write code to calculate the
 * number of ways of representing n cents.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question11 {

    int count;

    public void represetN(int n) {
        represent(n, 0, "");
        System.out.println(count + " WAYS");
    }

    /**
     * Recursive approach
     *
     * @param inSearchOfN N value in search of
     * @param currentN current n
     * @param toPrint string to print
     */
    private void represent(int inSearchOfN, int currentN, String toPrint) {
        if (currentN > inSearchOfN) {
            return;
        }

        if (currentN == inSearchOfN) {
            System.out.println(toPrint.substring(0, toPrint.length() - 1));
            count++;
            return;
        }

        int quarter = currentN + 25;
        int dime = currentN + 10;
        int nickels = currentN + 5;
        int pennies = currentN + 1;

        represent(inSearchOfN, quarter, " quarter(25) +" + toPrint);
        represent(inSearchOfN, dime, " dime(10) +" + toPrint);
        represent(inSearchOfN, nickels, " nickels(5) +" + toPrint);
        represent(inSearchOfN, pennies, " pennies(1) +" + toPrint);
    }

    /**
     * Recursion and Dynamic Programming Question 11 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_11 q11 = new Question_11();
        q11.represetN(10);
    }*/
}
