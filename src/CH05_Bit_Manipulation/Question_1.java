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
 * <b>Insertion:</b> You are given two 32-bit numbers, N and M, and two bit
 * positions, i and j. Write a method to insert M into N such that M starts at
 * bit j and ends at bit i. You can assume that the bits j through i have enough
 * space to fit all of M. That is, if M = 10011, you can assume that there are
 * at least 5 bits between j and i. You would not, for example, have j = 3 and i
 * = 2, because M could not fully fit between bit 3 and bit 2. EXAMPLE Input: N
 * 10000000000, M = 10011 Output: N = 10001001100
 *
 *  @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_1 {

    /**
     * Replaces a chunk of the binary Example:
     *
     * Input N: 10000000000
     *
     * Input M: 10011 
     * i = 2 j = 6
     *
     * expected Output: 10001001100
     *
     * @param N main binary
     * @param M binary to be placed between index i to j of N
     * @param i end index of replacement
     * @param j start index of replacement
     */
    public void replace(String N, String M, int i, int j) {
        int lengthN = N.length();
        int lengthM = M.length();

        Bits bits = new Bits();
        int intM = bits.toInteger(M);
        int intN = bits.toInteger(N);

        //clear the position where insertion will be done
        int dummyN = 0 << lengthN;
        int dummyM = 0 << lengthN;
        int dummyA = 0 << lengthN;
        int toMove = j - (lengthM - 1);
        int space = bits.ones(lengthM);
        dummyN = dummyN | (space << toMove);
        dummyN = bits.ones(lengthN) ^ dummyN;
        dummyN = intN & dummyN;

        //create mask for m
        dummyM = dummyM | (space << toMove);
        dummyM = (intM << toMove) & dummyM;

        dummyA = dummyN | dummyM;

        System.out.println("Input received: N = " + N + ", M = " + M);
        String outcome =  bits.toBinary(dummyA);
        outcome = bits.pad(outcome, N.length());
        System.out.println("Output = " +outcome);
    }
    
     /**
     * Replaces a chunk of the binary Example:
     *
     * Input N: 10000000000
     *
     * Input M:
     *
     * 10011 i = 2 j = 6
     *
     * expected Output: 100010011
     *
     * @param N main binary
     * @param M binary to be placed between index i to j of N
     * @param i end index of replacement
     * @param j start index of replacement
     */
    public void replace2(String N, String M, int i, int j) {
        Bits bits = new Bits();
        int intM = bits.toInteger(M);
        int intN = bits.toInteger(N);

        //clear the position in N where i want to insert into
        int space = j - i + 1;
        int toMove = j - (space-1);

        int dummy = bits.ones(space) << toMove;
        int clearerMask = bits.ones(j + 1) ^ (dummy);
        int expectantN = intN & (intN | clearerMask);

        //position my M properly to align into N
        int answer = expectantN | (intM << toMove);

        System.out.println("Input received: N = " + N + ", M = " + M);
        String outcome =  bits.toBinary(answer);
        outcome = bits.pad(outcome, N.length());
        System.out.println("Output = " +outcome);
    }

    /**
     * Bit Manipulation Question_1 main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Bits bits = new Bits();
        Question_1 q1 = new Question_1();
        String N = "10000000000";
        String M = "10011";
        int i = 2;
        int j = 6;
        q1.replace(N, M, i, j);
        q1.replace2(N, M, i, j);
    }
*/
}
