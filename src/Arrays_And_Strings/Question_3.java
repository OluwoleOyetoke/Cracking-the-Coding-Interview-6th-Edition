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
package Arrays_And_Strings;

/**
 * <b>URLify:</b> Write a method to replace all spaces in a string with '%20'.
 * You may assume that the string has sufficient space at the end to hold the
 * additional characters, and that you are given the "true" length of the
 * string. (Note: If implementing in Java, please use a character array so that
 * you can perform this operation in place.)
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 */
public class Question_3 {

    /**
     * Assuming ending spaces and spaces at the begining don't count
     *
     * @param input input string
     * @param length length of string
     * @return URLFied string
     */
    static String solve(String input, int length) {
        char[] inputChar = input.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[i] == ' ') {
                sb.append("%20");
            } else {
                sb.append(inputChar[i]);
            }
        }
        return sb.toString();
    }

    /**
     * Assuming a every single space should be replaced by %20 and not string of
     * spaces merged into one space and the one space replacedby a %20
     *
     * @param input Input string with spaces
     * @param length length of string
     * @return URLFied string
     */
    static String solve2(String input, int length) {
        return input.trim().replaceAll(" ", "%20");
    }

    /**
     * Giving space complexity priority and with a time complexity of 0(N)
     * where N = total string length with spaces
     *
     * @param input input string with spaces
     * @param length length of string
     * @return URLFied string
     */
    static String solve3(String input, int length) {
        char[] inputChar = input.toCharArray();
        int totalStringLength = input.length();
        int pos = totalStringLength - (totalStringLength-length)-1; //current string end spot, before the extended buffer
        for(int i=totalStringLength-1; i>=0; i--){ //counting down
            if(inputChar[pos]!=' '){
                inputChar[i] = inputChar[pos];
            }else{
                inputChar[i] = '0';
                inputChar[i-1] = '2';
                inputChar[i-2] = '%';
                i = i-2;
            }
             pos--;
        }
        return new String (inputChar);
    }
   

    /**
     * Question_3 main.....uncomment to run
     *
     * @param args the command line arguments
     *//*
    public static void main(String[] args) {
        String sentence = "Mr John Smith    ";
        int length = 13;
        String answer = "";
        answer = solve(sentence, 13);
        System.out.println("Sentence " + sentence + " has been URLfied to: " + answer);

        answer = solve2(sentence, 13);
        System.out.println("Sentence " + sentence + " has been URLfied to: " + answer);
        
        answer = solve3(sentence, 13);
        System.out.println("Sentence " + sentence + " has been URLfied to: " + answer);
    }*/
}
