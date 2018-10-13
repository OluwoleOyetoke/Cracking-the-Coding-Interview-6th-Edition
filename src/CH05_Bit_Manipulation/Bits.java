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
package CH05_Bit_Manipulation;

/**
 *
 *  @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Bits {
    
    
    
    /**
     * Converts binary string to integer
     *
     * @param val binary string
     * @return sum integer value of binary string
     */
    public int toInteger(String val) {
        char[] binary = val.toCharArray();
        int sum = 0;
        int compute = 0;
        int length = binary.length;
        int pos = 0;
        //start from the back (little endian)
        for (int i = length - 1; i >= 0; i--) {
            if (binary[i] == '1') {
                pos = length - i - 1;
                sum = sum + (int) Math.pow(2, pos);
            }
        }
        return sum;
    }
    
    /**
     * Used to create binary of length length.
     * Comes in handy when we need to do a negation 
     * @param length length of binary to create
     * @return value binary with 1s of length length
     */
    public int ones(int length){
        int sum = 0;
        for(int i=0; i<length; i++){
           sum = sum + (int) Math.pow(2, i);
        }
        return sum;
    }

    /**
     * converts integer to binary string
     *
     * @param val integer value to be converted to binary string
     * @return binary binary string
     */
    public String toBinary(int val) {
        StringBuilder sb = new StringBuilder();
        int rem = 0;

        boolean check = false;
        int dNewVal = val;
        int iNewVal = val;
        while (true) {
            rem = iNewVal % 2;
            sb.append(rem);
            dNewVal = dNewVal / 2;
            if (dNewVal < 1) {
                break;
            } else {
                iNewVal = (int) Math.floor(dNewVal);
                dNewVal = iNewVal;
            }
        }
        String binary = sb.reverse().toString();
        //System.out.println(val + " in Binary is " + binary);
        return binary;

    }
    
    /**
     * To pad the binary to a particular number of bits
     * @param binary to be padded
     * @param toXBits number of required bits
     * @return  paddedBits padded Bits
     */
    public String pad(String binary, int toXBits){
        int currentLength = binary.length();
        int difference = toXBits-currentLength;
        for(int i=0; i<difference; i++){
            binary = "0"+binary;
        }
        
        return binary;
    }
    
        
    /**
     * Bit Manipulation other bits manipulation  main/test method....uncomment to run
     * @param args command line arguments
     *//*
    public static void main(String[] args){
        Bits bits = new Bits();
        String binary = bits.toBinary(6);
        System.out.println("6 in Binary is " + binary);
        int sum = bits.toInteger("1100");
        System.out.println("1100 in Integer is " + sum);
    }
    */
}
