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
package CH03_Stacks_And_Queues;
import java.util.Arrays;

/**
 * Describe how you could use a single array to implement three stacks.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_1 {

    int top0 = -1;
    int top1 = 0;
    int top2 = 0;
    int size = 0;
    int[] stack;

    Question_1() {
        this.size = 20;
        top1 = top0 + size;
        top2 = top1 + size;
        stack = new int[size * 3];
    }

    Question_1(int size) {
        top1 = top0 + size;
        top2 = top1 + size;
        this.size = size;
        stack = new int[size * 3];
    }

    /**
     * Push into stack
     *
     * @param element element to add
     * @param whichStack which of the three stacks to add to
     * @return true/false true if successful
     */
    boolean push(int element, int whichStack) {
        int top = getTop(whichStack);
        top++;
        if ((top > (size * (whichStack + 1)) - 1)) {
            System.out.println("Stack " + whichStack + " is full");
            top--;
            return false;
        }
        stack[top] = element;
        updateTop(whichStack, top);
        return true;
    }

    /**
     * Pop out element from the stack
     *
     * @param whichStack which of the three stacks to pop from
     * @return toReturn element poped out (if any)
     */
    int pop(int whichStack) {
        int top = getTop(whichStack);
        if (top < (whichStack * size)) {
            System.out.println("Stack is empty");
            return -1;
        }
        int toReturn = stack[top];
        top--;
        updateTop(whichStack, top);
        return toReturn;
    }

    /**
     * Check the element at the top of the stack, but don't pop;
     *
     * @param whichStack stack under action
     * @return element element at the top of the stack
     */
    int peek(int whichStack) {
        int top = getTop(whichStack);
        if (top < (whichStack * size)) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stack[top];
    }

    /**
     * Check if stack is empty
     *
     * @param whichStack stack under action
     * @return true/false true if stack is empty
     */
    boolean isEmpty(int whichStack) {
        int top = getTop(whichStack);
        if (top < (whichStack * size)) {
            return true;
        }
        return false;
    }

    /**
     * Gets value for the top of the stack under action
     *
     * @param whichStack stack under action
     * @return topValue value of the location fo the top of the stack
     */
    int getTop(int whichStack) {
        int top;
        switch (whichStack) {
            case 0:
                top = top0;
                break;
            case 1:
                top = top1;
                break;
            case 2:
                top = top2;
                break;
            default:
                return -1;
        }
        if (top > size * (whichStack + 1) - 1) {
            System.out.println("Stack " + whichStack + " is full");
            return -1;
        }
        return top;
    }

    /**
     * Update the new value of the stack top
     *
     * @param whichStack stack under action
     * @param newTopValue new top value
     * @return true/false true if operation was successful
     */
    boolean updateTop(int whichStack, int newTopValue) {
        switch (whichStack) {
            case 0:
                this.top0 = newTopValue;
                return true;
            case 1:
                this.top1 = newTopValue;
                return true;
            case 2:
                this.top2 = newTopValue;
                return true;
            default:
                return false;
        }
    }

    /**
     * Converts stack into a string
     *
     * @return stackString stack string
     */
    String thisToString(int whichStack) {
        int top = getTop(whichStack);
        if (top == -1) {
            return null;
        }
        String toReturn = "";
        if (top < (whichStack * size)) {
            toReturn = "Stack is Empty";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (whichStack * size); i <= top; i++) {
            if (i != top) {
                sb.append(stack[i]);
                sb.append(",");
            } else {
                sb.append(stack[i]);
            }
        }
        return sb.toString();
    }

    /**
     * Main method for stacks & queues question 1....uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        Question_1 stack = new Question_1(20);
        System.out.println("PUSH INTO STACK 0");
        for (int i = 0; i < 20; i++) {
            stack.push((int) i * 9, 0);
        }

        System.out.println("STACK 0: " + stack.thisToString(0));
        System.out.println("POP: " + stack.pop(0));
        System.out.println("AFTER POP: " + stack.thisToString(0));
        System.out.println("PUSH 141 INTO STACK: " + stack.push(141, 0));
        System.out.println("STACK 0: " + stack.thisToString(0));
        System.out.println("PEEK: " + stack.peek(0));
        System.out.println("TRIED TO PUSH MORE THANK 20 ELEMENTS INTO STACK 0: " + stack.push(166, 0));
        System.out.println("IS STACK 1 EMPTY: " + stack.isEmpty(1));
        System.out.println("IS STACK 2 EMPTY: " + stack.isEmpty(2));
        
        
        System.out.println("\n\nPUSH INTO STACK 1");
        for (int i = 0; i < 6; i++) {
            stack.push((int) i * 3, 1);
        }

        System.out.println("STACK 1: " + stack.thisToString(1));
        System.out.println("POP: " + stack.pop(1));
        System.out.println("AFTER POP: " + stack.thisToString(1));
        System.out.println("PEEK: " + stack.peek(1));
        System.out.println("IS STACK 1 EMPTY: " + stack.isEmpty(1));
        System.out.println("IS STACK 2 EMPTY: " + stack.isEmpty(2));

        System.out.println("\n\nPUSH INTO STACK 2");
        for (int i = 0; i < 13; i++) {
            stack.push((int) i * 44, 2);
        }

        System.out.println("STACK 2: " + stack.thisToString(2));
        System.out.println("POP: " + stack.pop(2));
        System.out.println("AFTER POP: " + stack.thisToString(2));
        System.out.println("PEEK: " + stack.peek(2));
        System.out.println("IS STACK 1 EMPTY: " + stack.isEmpty(1));
        System.out.println("IS STACK 2 EMPTY: " + stack.isEmpty(2));

        System.out.println("\n\nSINGLE ARRAY USED AS STACK: " + Arrays.toString(stack.stack));
    }
*/
}
