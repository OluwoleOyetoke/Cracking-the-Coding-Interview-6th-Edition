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
 * <b>Find Primes</b> Find all the prime numbers between n and m
 *
 @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Primes {

    /**
     * Use sieve of eratosthenes
     *
     * @param m from 0 to m
     */
    public void findPrimes(int m) {
        int[] base = new int[m - 1];
        for (int i = 0; i < base.length; i++) {
            if(i+2<=m){
            base[i] = i + 2;
            }
        }

        int hops = 0;
        int start = 0;
        for (int i = 0; i < base.length; i++) {
            if (base[i] != 0) {
                hops = base[i];
                start = i + hops;
                for (i = start; i < base.length; i = i + hops) {
                    base[i] = 0;
                }
            }   
        }

        //print primes
        for (int i = 0; i < base.length; i++) {
            if (base[i] != 0) {
                System.out.print(base[i] + ", ");
            }
        }
    }
    
    /**
     * Main method...uncomment to run
     * @param args 
     *//*
    public static void main(String[] args) {
        Primes p = new Primes();
        p.findPrimes(20);
    }
*/

}
