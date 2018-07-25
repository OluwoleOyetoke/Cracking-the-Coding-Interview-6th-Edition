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
package Recusion_And_Dynamic_Programming;

/**
 * <b>Boolean Evaluation:</b> Given a boolean expression consisting of the symbols 0
 * (false), 1 (true), AND (AND), OR (OR), and XOR (XOR), and a desired boolean result
 * value result, implement a function to count the number of ways of
 * parenthesizing the expression such that it evaluates to result. The
 * expression should be fully parenthesized (e.g., ( 0) A( 1)) but not
 * extraneously (e.g., ( ( ( 0)) XOR ( 1)) ).
 *
 * EXAMPLE: countEval("l XOR 0 OR 0 OR 1", false) == 2 countEval("0 AND 0 AND 0 AND 1 XOR l OR 0", true)== 10
 * 
 * INCOMPLETE
 * 
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_14 {

    public void parenthesize(String expression, boolean result) {
        int intRes = (result == true) ? 1 : 0;
        permuteParenthesis(expression, "", 1, intRes);
    }

    private String permuteParenthesis(String expression, String outcome, int step, int result) {
       
        return "";
    }

    private String evaluateLeftRight(String left, String operation, String right) {
        if (operation.equals("") || left.equals("")) {
            return String.valueOf(right);
        }
        if (operation.equals("|")) {
            return String.valueOf(Integer.valueOf(left) | Integer.valueOf(right));
        } else if (operation.equals("&")) {
            return String.valueOf(Integer.valueOf(left) & Integer.valueOf(right));
        } else if (operation.equals("^")) {
            return String.valueOf(Integer.valueOf(left) ^ Integer.valueOf(right));
        }
        return "0";
    }

    private String evaluateExpression(String expression) {
        char[] expArr = expression.toCharArray();
        int previousVal = 0;

        for (int i = 0; i < expArr.length; i++) {
            if (i % 2 != 0) {
                if (expression.substring(i, i + 1).equals("|")) {
                    previousVal = previousVal | Integer.valueOf(expression.substring(i + 1, i + 2));
                } else if (expression.substring(i, i + 1).equals("&")) {
                    previousVal = previousVal & Integer.valueOf(expression.substring(i + 1, i + 2));
                } else if (expression.substring(i, i + 1).equals("^")) {
                    previousVal = previousVal ^ Integer.valueOf(expression.substring(i + 1, i + 2));
                }
                i++;
            } else {
                previousVal = Integer.valueOf(expression.substring(i, i + 1));
            }
        }
        return String.valueOf(previousVal);
    }

    /**
     * Recursion and Dynamic Programming Question 14 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_14 q14 = new Question_14();
        String toEvaluate = "1&0&1";
        String answer = q14.evaluateExpression(toEvaluate);
        System.out.println(toEvaluate + " = " + answer);
        q14.parenthesize("1|0|0|1&0", true);
    }*/
}
