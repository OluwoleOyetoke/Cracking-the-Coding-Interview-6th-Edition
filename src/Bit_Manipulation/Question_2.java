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
package Bit_Manipulation;

import java.util.Arrays;

/**
 * <b>Binary to String:</b> Given a real number between O and 1 (e.g., 0.72)
 * that is passed in as a double, print the binary representation. If the number
 * cannot be represented accurately in binary with at most 32 characters, print
 * "ERROR:'
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_2 {

    /**
     * Takes a chunk of real number, works separately on the fractional and
     * whole number part to provide the binary representation of the real number
     *
     * @param number number to convert to binary
     * @return outcome converted (binary representation)
     */
    public String realToBinary(double number) {
        Bits bits = new Bits();
        String strVal = String.valueOf(number);
        String[] split = strVal.split("\\.");
        String left = "";
        String right = "";

        if (split.length == 2) {
            left = bits.toBinary(Integer.valueOf(split[0]));
            right = getFractionalBinary(Double.valueOf("0." + split[1]));
        } else {
            System.out.println("Error encountered with received number");
            return null;
        }
        String concatenated = left + "" + right;
        return concatenated;
    }

    /**
     * Get the binary representation of the fractional part of a decimal
     *
     * @param number number whose decimal part should be calculated
     * @return output converted (binary representation)
     */
    public String getFractionalBinary(double number) {
        if (number == 0) {
            return "0";
        } else if (number < 0) {
            System.out.println("Please input real numbers");
            return null;
        } else if (number > 1) {
            System.out.println("Please input only the fractional part of your number e.g 0.25 not 5.25");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String output = "";
        double currentVal = number;
        double nextVal = number;
        int count = 0;
        sb.append(".");
        while (currentVal > 0) {
            nextVal = currentVal * 2;
            if (nextVal >= 1) {
                sb.append(1);
                currentVal = nextVal - 1;
            } else {
                sb.append(0);
                currentVal = nextVal;
            }
            count++;
            if (count > 31) {
                System.out.println("ERROR"); //More than 32 bits required to represent number
                break;
            }
        }
        output = sb.toString();
        return output;
    }

    /**
     * Bit Manipulation Question_2 main/test method....uncomment to run
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Question_2 q2 = new Question_2();
        String binary = q2.getFractionalBinary(0.25);
        System.out.println("Binary Representation for 0.25: " + binary);

        binary = q2.realToBinary(4.25);
        System.out.println("Binary Representation for 4.25: " + binary);
    }

}
