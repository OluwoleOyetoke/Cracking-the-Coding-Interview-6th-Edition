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

import static java.lang.Math.ceil;
import java.util.List;

/**
 * <b>Palindrome Permutation:</b> Given a string, write a function to check if
 * it is a permutation of a palindrome. A palindrome is a word or phrase that is
 * the same forwards and backwards. A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.__Page 91
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 */
public class Question_4 {
    
    static boolean answer=false;
    /**
     * Find all the permutations of the input and check if any of the
     * permutations is a palindrome
     *
     * @param input string input to perform operation on
     * @return true/false. True if it is a permutation of a palindrome and false
     * if other wise
     */
    static void solve(String sentence) {
        boolean checker = false;
        getPermutations(new char[sentence.length()], 0, sentence.toCharArray());
    }
    
    /**
     * Use simple characteristics of a palindrome to find answer. Assuming sentence contains only ascii characters
     * @param sentence sentence to check 
     * @return true/false true if sentence contains a permutation which is a palindrom
     */
    static boolean solve2(String sentence) {
        int[] charCount = new int[256]; //assuming extended ascii
        char[] sentenceChar = sentence.replaceAll(" ", "").toCharArray(); //replaces all spaces in sentence

        //get char count
        int pos = 0;
        for (int i = 0; i < sentenceChar.length; i++) {
            pos = (int) sentenceChar[i];
            charCount[pos]++;
        }

        if ((sentenceChar.length % 2) == 0) {//an even length parlindrome should have even character count
            for (int i = 0; i < charCount.length; i++) {

                if ((charCount[i] % 2) != 0) {
                    return false;
                }
            }
            return true;

        } else {//an odd length palindrome should only have 1 character out of place
            int oddCount = 0;
            for (int i = 0; i < charCount.length; i++) {
                if ((charCount[i] % 2) != 0) {
                    oddCount++;
                    if (oddCount > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    /**
     * Using the bit manipulation method (assuming only 26 letters of the English alphabet [a-z])
     * @param sentence input string to check
     * @return  true/false return true if sentence is a permutation of a palindrome
     */
    public static boolean solve3(String sentence){
        int alphaBit = 0x0000000; //28 bits for  26 letters
        int val=0;
        sentence = sentence.replaceAll(" ", "");
        for(int i=0; i<sentence.length(); i++){
           val = 25 - (122 - (int) sentence.charAt(i));
           alphaBit = alphaBit ^ (0x0000001<<val);
        }
        if((sentence.length()%2==0) && alphaBit==0 ){
            return true;
        }else if((sentence.length()%2!=0) && alphaBit>1 ){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Generates all permutations of the input string (duplicates un-handled)
     *
     * @param inputArray input array
     * @param position position where swap should start from
     * @param storage list storing new permutations
     */
    static void getPermutations(char[] inputArray, int position, List storage) {
        if (position == inputArray.length - 1) {
            //System.out.println(new String(inputArray));
            storage.add(new String(inputArray));
        }
        char[] tempArray = new char[inputArray.length];
        System.arraycopy(inputArray, 0, tempArray, 0, inputArray.length); //to make sure array isnt passed by reference
        for (int i = position; i < inputArray.length; i++) {
            tempArray = switchPositions(tempArray, position, i);
            getPermutations(tempArray, position + 1, storage);
            tempArray = switchPositions(tempArray, position, i);
        }
    }

    /**
     * Generates all permutations of the input string (handles duplicates)
     *
     * @param inputArray input char array
     * @param position position where swaps should happen
     * @param rem char array containing all characters to be swapped in position
     * @param storage list storing permutations
     * @param isPalindrome palindrome checker result storage
     */
    static void getPermutations(char[] inputArray, int position, char[] rem) {
        if (rem == null) {
            boolean palindromeCheck = checkParlindrome(inputArray);
            if(palindromeCheck){
            answer=true; 
            }
            
            return;
        }
        char[] newArray = new char[inputArray.length];
        System.arraycopy(inputArray, 0, newArray, 0, inputArray.length);
        for (int i = 0; i < rem.length; i++) {
            boolean check = shouldPut(rem[i], i + 1, rem);
            if (check) {
                newArray[position] = rem[i];
                char[] remaining = popOut(rem, i);
                getPermutations(newArray, position + 1, remaining);
            }
        }
    }
    
    /**
     * Used to check if an input char array is a palindrome
     * Spaces are ignored in the palindrome checker
     * @param input input char array
     * @return true/false returns true if it is a palindrome
     */
    static boolean checkParlindrome(char[] input){
        char[] withoutSpaces = (new String(input).replaceAll(" ", "")).toLowerCase().toCharArray(); //replaces spaces and makes string lowercase
        for(int i=0; i<ceil(withoutSpaces.length/2); i++){
            if(withoutSpaces[i]!=withoutSpaces[withoutSpaces.length-1-i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if current character has a duplicate ahead
     *
     * @param currentlyOn Current char
     * @param start start position
     * @param available char array of available characters to swap
     * @return
     */
    static boolean shouldPut(char currentlyOn, int start, char[] available) {
        int count = 0;
        boolean check = true;
        for (int i = start; i < available.length; i++) {
            if (available[i] == currentlyOn) {
                return false;
            }
        }
        return check;
    }

    /**
     * Pop out one out of the index of the array
     *
     * @param inputArray input array
     * @param position position to remove
     * @return newArray array with index popped out
     */
    static char[] popOut(char[] inputArray, int position) {
        if (inputArray.length == 1) {
            return null;
        }
        char[] newArray = new char[inputArray.length - 1];
        int pos = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (i != position) {
                newArray[pos] = inputArray[i];
                pos++;
            }
        }
        return newArray;
    }

    /**
     * Used to switch spots in an array
     *
     * @param input input char array
     * @param posA position a
     * @param posB position b
     * @return swapped array
     */
    static char[] switchPositions(char[] input, int posA, int posB) {
        char temp = input[posA];
        input[posA] = input[posB];
        input[posB] = temp;
        return input;
    }

    /**
     * Question_4 main... uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        boolean answer2;
        String sentence = "tact coa";
        //solve method 1  
        solve(sentence);
        System.out.println("Palindrome? :"+answer);
        
        //solve method 2
        answer2 = solve2(sentence);
        System.out.println("Palindrome? :"+answer2);
        
        //solve method 2
        answer2 = solve3(sentence);
        System.out.println("Palindrome? :"+answer2);
    }*/
}
