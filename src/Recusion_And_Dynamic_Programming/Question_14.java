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
 * <b>Boolean Evaluation:</b> Given a boolean expression consisting of the
 * symbols 0 (false), 1 (true), AND (AND), OR (OR), and XOR (XOR), and a desired
 * boolean result value result, implement a function to count the number of ways
 * of parenthesizing the expression such that it evaluates to result. The
 * expression should be fully parenthesized (e.g., ( 0) A( 1)) but not
 * extraneously (e.g., ( ( ( 0)) XOR ( 1)) ).
 *
 * EXAMPLE: countEval("l XOR 0 OR 0 OR 1", false) == 2 countEval("0 AND 0 AND 0
 * AND 1 XOR l OR 0", true)== 10
 *
 * INCOMPLETE
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_14 {

    String solutions = "";
    int originalLength;
    int expectedResult;
    int ways = 0;

    public void parenthesize(String expression, boolean result) {
        System.out.println("TO EVALUATE: " + expression);
        expectedResult = (result == true) ? 1 : 0;
        originalLength = expression.length();
        permuteParenthesis(expression);
        System.out.println("Number of ways = " + ways+"...Not correct");
    }

    private String permuteParenthesis(String expression) {
        if (expression.length() == 1) {
            System.out.println("Evaluate One: " + expression + " = " + expression);
            return expression;
        }

        String left = "";
        String right = "";
        String operation = "";
        int start = 0;
        int previousStop = 0;
        int nextStop = 0;
        int finalStop = 0;
        int count = 1;
        String outcome = "";
        int varCount = (int) Math.ceil((double) expression.length() / 2);
        while (count <= varCount) {
            //System.out.println("Count is "+count);
            nextStop = (count * 2) - 1;
            finalStop = (nextStop + 1) + (count * 2) - 1;

            left = expression.substring(start, nextStop);
            if (nextStop == expression.length()) {
                System.out.println("Evaluate AIO: (" + left + ") = [UNKNOWN YET]");
                return evaluateExpression(left);
            }
            operation = expression.substring(nextStop, nextStop + 1);
            right = expression.substring(nextStop + 1, expression.length());

            System.out.println("Evaluate Pre: " + left + "" + operation + "(" + right + ") = [UNKNOWN YET]");
            outcome = evaluateLeftRight(
                    evaluateExpression(left),
                    operation,
                    evaluateExpression(permuteParenthesis(right)));
            count++;
        }
        //System.out.println("Evaluate: " + left + "" + operation + "" + right + " = " + outcome);
        //System.out.println("Outcome: "+outcome);
        return outcome;
    }

    private String evaluateLeftRight(String left, String operation, String right) {
        if (operation.equals("") || left.equals("") || left.equals(" ")) {
            return String.valueOf(right);
        }
        String outcome = "0";
        if (operation.equals("|")) {
            outcome = String.valueOf(Integer.valueOf(left) | Integer.valueOf(right));
        } else if (operation.equals("&")) {
            outcome = String.valueOf(Integer.valueOf(left) & Integer.valueOf(right));
        } else if (operation.equals("^")) {
            outcome = String.valueOf(Integer.valueOf(left) ^ Integer.valueOf(right));
        }
        String concat = left + "" + operation + "" + right;
        if (concat.length() == this.originalLength) {
            if (Integer.valueOf(outcome) == expectedResult) {
                ways++;
            }
        }
        System.out.println("Evaluate LF: " + left + "" + operation + "" + right + " = " + outcome);
        return outcome;
    }

    private String evaluateExpression(String expression) {
        int previousVal = 0;

        for (int i = 0; i < expression.length(); i++) {
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
        if (expression.length() == this.originalLength) {
            if (previousVal == expectedResult) {
                ways++;
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
        String toEvaluate2 = "1^0|0|1";
        //String answer = q14.evaluateExpression(toEvaluate2);
        //System.out.println(toEvaluate2 + " = " + answer);
        q14.parenthesize("1|0", true);
        //q14.parenthesize("1^0|0|1", false);
    }*/
}
