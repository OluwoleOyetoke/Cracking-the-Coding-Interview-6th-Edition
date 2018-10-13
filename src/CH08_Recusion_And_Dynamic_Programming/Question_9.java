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
package CH08_Recusion_And_Dynamic_Programming;

/**
 * <b>Parens:</b> Implement an algorithm to print all valid (e.g., properly opened and
 * closed) combinations of n pairs of parentheses. 
 * EXAMPLE 
 * Input: 3 
 * Output: ( ( () ) ) , ( () () ) , ( () ) () , () ( () ) , () () ()
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_9 {

    /**
     * print out all possible parenthesis for input
     * Example Input: 3 = (((
     * Output: ( ( () ) ) , ( () () ) , ( () ) () , () ( () ) , () () ()
     * @param input number of open parenthesis e.g 3 = (((
     */
    public void parens(int input) {
        
        if(input==0){
            System.out.println("No paranthesis received");
            return;
        }
        
        //Find maximum number represented by [input*2] bits
        int max = (int) (Math.pow(2, (input * 2)) - 1);

        //get all permutations of the input*2 number with input amount set
        for (int i = 0; i <= max; i++) {
            parenthesize(i, input, input * 2);
        }
    }
    
    /**
     * 
     * @param input input number
     * @param noOfBits number of ON bits we are looking for
     * @param stopAt bit to stop at
     */
    private void parenthesize(int input, int noOfBits, int stopAt) {
        
        int mask = 0;
        int onCount = 0;
        int parensFactor = 0;
        String parens = "";
        
        for (int i = 0; i < stopAt; i++) {
            mask = 1 << i;
            mask = mask & input;
            if (mask > 0) {
                onCount++;
                parensFactor++;
                parens = "(" + parens;
            } else {
                parensFactor--;
                parens = ")" + parens;
            }
            
            //possible quick exit criteria
            if (parensFactor > 0) {
                return;
            }
        }
        
        if (onCount == noOfBits) {
            System.out.println("Possible: " + parens);
            return;
        }
    }
    
    /**
     * Recursion and Dynamic Programming Question 9 main/test method
     * @param args command lien arguments 
     *//*
    public static void main(String[] args) {
        Question_9 q9 = new Question_9();
        q9.parens(3);
    }*/
}