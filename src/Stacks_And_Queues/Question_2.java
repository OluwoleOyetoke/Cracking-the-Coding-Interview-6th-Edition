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
package Stacks_And_Queues;

/**
 * <b>Stack Min:</b> How would you design a stack which, in addition to push and
 * pop, has a function min which returns the minimum element? Push, pop and min
 * should all operate in 0(1) time.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_2 {

    static final int STACK_SIZE = 20;
    static MyStack<Integer> normalStack = new MyStack<>(STACK_SIZE);
    static MyStack<Integer> minStack = new MyStack<>(STACK_SIZE);
    static int minimum;

    /**
     * Modified Push: Push elements into stack
     *
     * @param element element to add
     * @return true/false true if successful
     */
    public static boolean push(int element) {
        boolean wasEmpty = normalStack.isEmpty();
        boolean pass = normalStack.push(element);
        if (pass && wasEmpty) {
            minimum = element;
            minStack.push(minimum);
        } else if (pass && element < minimum) {
            minimum = element;
            minStack.push(minimum);
        }
        return pass;
    }

    /**
     * Modified pop: Pops out element from the stack and keeps track of minimum
     *
     * @return poped element poped out (if any)
     */
    public static int pop() {
        Integer poped = normalStack.pop();
        if ((poped != null) && (poped <= minimum)) {
            minStack.pop();
            minimum = min();
        }
        return poped;
    }

    /**
     * Retrieve current minimum in stack
     *
     * @return
     */
    public static int min() {
        if (minStack.isEmpty()) {
            System.out.println("Stack is empty. Return -1");
            return -1;
        }
        return minStack.peek();
    }

    /**
     * Main method for stacks & queues question 2....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        System.out.println("PUSH INTO NORM STACK");
          
        push(10);
        push(60);
        push(25);
        push(9);
        push(14);
        push(7);
        push(6);
        push(33);
        push(4);
        push(99);
        
        System.out.println("NORM. STACK: "+normalStack.thisToString());
        System.out.println("MIN. STACK: "+minStack.thisToString());
        for(int i=0; i<10; i++){
            System.out.println("Poped: "+pop()+", New minimal: "+min());
        }
    }*/

}
