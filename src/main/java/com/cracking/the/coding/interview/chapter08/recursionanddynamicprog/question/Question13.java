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
package com.cracking.the.coding.interview.chapter08.recursionanddynamicprog.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * <b>Stack of Boxes:</b> You have a stack of n boxes, with widths w1 , heights
 * h i, and depths di . The boxes cannot be rotated and can only be stacked on
 * top of one another if each box in the stack is strictly larger than the box
 * above it in width, height, and depth. Implement a method to compute the
 * height of the tallest possible stack. The height of a stack is the sum of the
 * heights of each box.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question13 {

    int highestHeight = 0;

    /**
     * Compute the stack of boxes with the highest height and incompliance with
     * the height,breadth and lenght constraint
     *
     * @param boxes list of boxes
     */
    public void stackBoxes(List<Box> boxes) {
        stackUp(boxes, new Stack<>(), 0, 0); //begin stack up
        System.out.println("\nHighest Possible Height is: " + highestHeight);
    }
    
    /**
     * Stack up the boxes according to specified rule
     * @param boxes boxes
     * @param stack current stack
     * @param count count of iteration
     * @param height current height of stack
     */
    public void stackUp(List<Box> boxes, Stack<Box> stack, int count, int height) {
        if (height > highestHeight) {
            highestHeight = height;
            printStack(stack);
        }

        Box tempBox;
        List<Box> tempBoxes;
        Box A;
        Box B;
        for (int i = 0; i < boxes.size(); i++) {
            if (stack.size() != count) {
                stack.pop();
            }
            if (stack.size() == 0) {
                tempBoxes = copyList(boxes);
                tempBox = tempBoxes.remove(i);
                stack.push(tempBox);
                stackUp(tempBoxes, stack, count + 1, tempBox.height);
            } else {
                A = stack.peek();
                B = boxes.get(i);
                if (A.breadth > B.breadth && A.height > B.height && A.length > B.length) { //check condition
                    tempBoxes = copyList(boxes);
                    tempBox = tempBoxes.remove(i);
                    stack.push(tempBox);
                    stackUp(tempBoxes, stack, count + 1, (tempBox.height + height));
                }
            }
        }
    }

    /**
     * Perform deep copy of an array list
     *
     * @param list array list
     * @return newList new array list
     */
    private List<Box> copyList(List<Box> list) {
        ArrayList<Box> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
    
    /**
     * Print stack
     * @param stack stack of boxes 
     */
    private void printStack(Stack<Box> stack) {
        System.out.println("\n\nSTACK OF " + stack.size() + " BOXE(S) ");
        Iterator<Box> it = stack.iterator();
        while (it.hasNext()) {
            System.out.print("[" + it.next().name + "], ");
        }
    }

    static class Box {

        private int height;
        private int length;
        private int breadth;
        private String name;

        Box(String name, int length, int breadth, int height) {
            this.name = name;
            this.length = length;
            this.breadth = breadth;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getBreadth() {
            return breadth;
        }

        public void setBreadth(int breadth) {
            this.breadth = breadth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Box{" + "name=" + name + '}';
        }

    }

    /**
     * Recursion and Dynamic Programming Question 13 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_13 q13 = new Question_13();
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Question_13.Box("England", 9, 1, 9));
        boxes.add(new Question_13.Box("Ireland", 3, 3, 2));
        boxes.add(new Question_13.Box("Germay", 8, 8, 8));
        boxes.add(new Question_13.Box("Netherlands", 6, 6, 7));
        boxes.add(new Question_13.Box("Romania", 4, 5, 4));
        q13.stackBoxes(boxes);
    }*/
}
