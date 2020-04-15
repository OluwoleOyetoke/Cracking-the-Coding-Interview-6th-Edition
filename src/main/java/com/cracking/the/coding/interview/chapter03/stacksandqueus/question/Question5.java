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
package com.cracking.the.coding.interview.chapter03.stacksandqueus.question;

import java.util.Arrays;
import java.util.Stack;

/**
 * <b>Sort Stack:</b> Write a program to sort a stack such that the smallest
 * items are on the top. You can use an additional temporary stack, but you may
 * not copy the elements into any other data structure (such as an array). The
 * stack supports the following operations: push, pop, peek, and is Empty.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
@SuppressWarnings("unused")
public class Question5 {

    /**
     * Sort stack using two other temporary stacks
     *
     * @param toSort  stack to sort
     * @param storage storage for sorted stack
     */
    public void sort(Stack<Integer> toSort, Stack storage) {
        if (toSort.size() == 0) {
            System.out.println("RECURSIVE SORT (2 EXTRA STACKS): " + Arrays.toString(storage.toArray()));
            return;
        }

        int maximum = 0;
        int poped = 0;
        int stackToSortSize = toSort.size();
        Stack<Integer> tempStack = new Stack<Integer>();

        //get maximum value in stack
        for (int i = 0; i < stackToSortSize; i++) {
            poped = (int) toSort.pop();
            if (i == 0) {
                maximum = poped;
            }
            if (poped >= maximum) {
                maximum = poped;
            }
            tempStack.push(poped);
        }

        //remove all maximum values from current stack and place in sorted stack
        Stack<Integer> cutDownStack = new Stack<Integer>();
        for (int i = 0; i < stackToSortSize; i++) {
            poped = (int) tempStack.pop();
            if (poped == maximum) {
                storage.push(maximum);
            } else {
                cutDownStack.push(poped);
            }
        }
        sort(cutDownStack, storage);
    }

    /**
     * Sort stack using just one other stack
     *
     * @param toSort stack to sort;
     */
    public void sort2(Stack<Integer> toSort) {
        Stack<Integer> tempStack = new Stack<Integer>();
        int toSortSize = toSort.size();
        int poped = 0;
        int tempStackSize = 0;
        int tempPopCount = 0;
        while (toSortSize > 0) {
            poped = (int) toSort.pop();
            tempPopCount = tempStack.size();
            if (tempStackSize == 0) {
                tempStack.add(poped);
            } else {
                tempPopCount = 0;
                for (int i = 0; i < tempStackSize; i++) {

                    if ((int) tempStack.peek() > poped) {
                        tempStack.add(poped);
                        if (tempPopCount > 0) {
                            for (int j = 0; j < tempPopCount; j++) {
                                tempStack.push(toSort.pop());
                            }
                        }
                    } else {
                        toSort.push((int) tempStack.pop());
                        tempPopCount++;
                    }
                }
            }
            toSortSize = toSort.size();
        }
        System.out.println("ONLY 1 TEMP STACK: " + Arrays.toString(tempStack.toArray()));
    }

    /**
     * Sort stack using just one other stack
     *
     * @param toSort stack to sort;
     */
    public void sort3(Stack<Integer> toSort) {
        //quick return;
        if (toSort == null) {
            return;
        }

        //variables
        Stack<Integer> tempStack = new Stack<>();
        int temp = 0;
        int temp2;
        int count = 0;

        //while stack to sort is not empty
        while (!toSort.isEmpty()) {
            temp = toSort.pop();

            //if temp stack is empty
            if (tempStack.isEmpty()) {
                tempStack.push(temp);
            } else {

                //value on top of temp stack is greater than incoming
                temp2 = tempStack.peek();
                if (temp2 > temp) {
                    count = 0;

                    while (temp2 > temp) {
                        toSort.push(tempStack.pop());
                        count++;

                        if (tempStack.isEmpty()) {
                            break;
                        }
                        temp2 = tempStack.peek();
                    }
                    //System.out.println("Count: "+count);
                    tempStack.push(temp);
                    for (int i = 0; i < count; i++) {
                        tempStack.push(toSort.pop());
                    }
                } //if value on top of temp stack is less than or equal to incoming
                else {
                    tempStack.push(temp);
                }
            }

        }

        while (!tempStack.isEmpty()) {
            toSort.push(tempStack.pop());
        }

        System.out.println("ONLY 1 TEMP STACK: " + Arrays.toString(toSort.toArray()));

    }

    /**
     * Main method for stacks and queues question 5.....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_5 q = new Question_5();
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        for (int i = 0; i < 20; i++) {
            stack.add(i);
            stack2.add(i);
        }
        System.out.println("Stack Before Sort: " + Arrays.toString(stack.toArray()));
        Stack storage = new Stack();
        q.sort(stack, storage);
        q.sort2(stack2);
        //q.sort3(stack2);
    }*/
}
