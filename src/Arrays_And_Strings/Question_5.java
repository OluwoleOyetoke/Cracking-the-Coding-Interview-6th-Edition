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

import java.util.Arrays;

/**
 * <b>One Away:</b> There are three types of edits that can be performed on
 * strings: insert a character, remove a character, or replace a character.
 * Given two strings, write a function to check if they are one edit (or zero
 * edits) away...page 91
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 */
public class Question_5 {
    
    /**
     * Solve by sorting the two strings, then walking linearly through the string and making char to char comparison
     * @param testWord word to be checked if it is 1 or 0 edits away from being same as mainWord
     * @param mainWord word to be checked against
     * @return true/false true if testWord is one edit away from being same with mainWord
     */
    public static boolean solve(String testWord, String mainWord) {
        //get string lengths
        int testLength = testWord.length();
        int mainLength = mainWord.length();

        //quick elimination checkes
        if (Math.abs(testLength - mainLength) > 1) {
            return false;
        }
        
        //convert to char array
        char[] testWordArray = testWord.toLowerCase().toCharArray();
        char[] mainWordArray = mainWord.toLowerCase().toCharArray();

        int editCounts = 0;
        int testPointer = 0;
        int mainPointer = 0;
        int loopLength = (mainLength>testLength)?mainLength:testLength;
        
        for (int i = 0; i < loopLength; i++) {
            if ((mainWordArray[mainPointer] != testWordArray[testPointer]) && (mainLength < testLength)) {
                editCounts++;
                testPointer++;
            } else if ((mainWordArray[mainPointer] != testWordArray[testPointer]) && (mainLength > testLength)) {
                editCounts++;
                mainPointer++;
            } else if ((mainWordArray[mainPointer] != testWordArray[testPointer]) && (mainLength == testLength)) {
                editCounts++;
                testPointer++;
                mainPointer++;
            }else if(mainWordArray[mainPointer] == testWordArray[testPointer]){
                testPointer++;
                mainPointer++;
            }
            
            //reorganize count (for when difference is at the last string in a set of equal strings)
            if(testPointer>=testLength){testPointer--;}
            if(mainPointer>=mainLength){mainPointer--;}
            
            //if more than 1 edits has been made
            if(editCounts>1){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean answer = false; 
        String[][] inputs = {{"pale", "ple"}, {"pales", "pale"}, {"pale", "bale"}, {"pale","bake"}, {"soccer", "ssoccerr"}};   
         for(int i=0; i<inputs.length; i++){
             answer = solve(inputs[i][0], inputs[i][1]);
             System.out.println("Is '"+inputs[i][0]+"' 1 edit (del,add,replace) away from becoming '"+inputs[i][1]+"': "+answer);
         }
    }
}
