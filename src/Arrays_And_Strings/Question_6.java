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
 * <b>String Compression:</b> Implement a method to perform basic string
 * compression using the counts of repeated characters. For example, the string
 * aabcccccaaa would become a2blc5a3. If the "compressed" string would not
 * become smaller than the original string, your method should return the
 * original string. You can assume the string has only uppercase and lowercase
 * letters (a - z)....Page 91
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 */
public class Question_6 {
    
    /**
     * Loops through string and writes compressed version in another string builder . Time complexity O(N)
     * @param input string to compress
     * @return return compressed string (if string was compressible)
     */
    public static String solve(String input) {
        //quick exit cases
        if (input == null) {
            return null;
        } else if (input.length() <= 2) {
            return input;
        }

        char[] inputArray = input.toCharArray();

        StringBuilder newString = new StringBuilder();
        char current;
        char previous = inputArray[0];
        int onNew = 0;
        int count = 1;
        for (int i = 1; i < inputArray.length; i++) {
            current = inputArray[i];
            if (current != previous) {
                newString.append(previous);
                newString.append(count);
                count=1;
            }else if(i == inputArray.length-1){
                count++;
                newString.append(previous);
                newString.append(count);
            }
            else {
                count++;
            }
            previous = current;
        }
        
        if(newString.length()>=inputArray.length){
            return input;
        }
        return newString.toString();
    }
    
    /**
     * Question_6 main.....uncomment to run
     * @param args command line arguments 
     *//*
    public static void main(String[] args) {
        String answer;
        
        String toCompress = "aabcccccaaa";
        answer = solve(toCompress);
        System.out.println(answer);
        
        String toCompress2 = "aaaaaaaaaaaabcdefgh";
        answer = solve(toCompress2);
        System.out.println(answer);
        
        String toCompress3 = "abcdefgh";
        answer = solve(toCompress3);
        System.out.println(answer);
    }*/
}
