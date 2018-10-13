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
package CH02_Linked_Lists;

import java.util.Stack;

/**
 * <b>Palindrome:</b> Implement a function to check if a linked list is a
 * palindrome.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_6 {

    /**
     * Use a stack to keep track of the first half of the palindrome and then
     * pop the stack for the second half of the palindrome. If what is being
     * popped from the stack is not same a content of the second half of the
     * linked list, then it is not a palindrome
     *
     * @param list list to check if it is a palindrome
     * @return true/false returns true if content of linked list is a palindrome
     */
    public static boolean solve(MySinglyLinkedList list) {
        //Quick exit criteria
        if (list == null) {
            return false;
        } else if (list.size == 1) {
            return false;
        }
        System.out.println("List Received: " + list.thisToString());
        Stack stack = new Stack();
        int midPoint = 0;
        boolean evenPalindrome;
        if (list.size % 2 == 0) {
            midPoint = list.size / 2;
            evenPalindrome = true;
        } else {
            midPoint = (int) Math.ceil((float) list.size / 2);
            evenPalindrome = false;
        }
        int counter = 0;
        MySinglyLinkedList.Node current = list.head;
        boolean decision = true;
        while (current != null) {
            if ((evenPalindrome == true && counter <= (midPoint - 1)) || ((evenPalindrome == false) && (counter < (midPoint - 1)))) {
                stack.add(current.content);
            } else if ((evenPalindrome == true && counter > (midPoint - 1)) || ((evenPalindrome == false) && (counter > (midPoint - 1)))) {
                if (!stack.pop().equals(current.content)) {
                    decision = false;
                }
            }
            counter++;
            current = current.nextNode;
        }
        System.out.println("List After Check: " + list.thisToString());
        return decision;
    }

    /**
     * Making the check in O(N) time and without any other extra data structure
     * such as a stack.
     *
     * List returned is un arranged
     *
     * @param list list to check if it is a palindrome
     * @return true/false returns true if content of linked list is a palindrome
     */
    public static boolean solve2(MySinglyLinkedList list) {
        //Quick exit criteria
        if (list == null) {
            return false;
        } else if (list.size() == 1) {
            return false;
        }

        System.out.println("List Received: " + list.thisToString());
        int midPoint = 0;
        boolean evenPalindrome;
        int listSize = list.size();
        if (listSize % 2 == 0) {
            midPoint = listSize / 2;
            evenPalindrome = true;
        } else {
            midPoint = (int) Math.ceil((float) listSize / 2);
            evenPalindrome = false;
        }
        int counter = 0;

        MySinglyLinkedList.Node latestTail = list.head;

        MySinglyLinkedList.Node previous = list.head;
        MySinglyLinkedList.Node current = list.head.nextNode;
        MySinglyLinkedList.Node nextNode = current.nextNode;
        boolean decision = true;
        while (current != null) {
            nextNode = current.nextNode;
            if ((evenPalindrome == true && counter <= (midPoint - 2)) || ((evenPalindrome == false) && (counter < (midPoint - 2)))) {
                current.nextNode = previous;
                latestTail = current;
            } else if ((evenPalindrome == true && counter > (midPoint - 2)) || ((evenPalindrome == false) && (counter > (midPoint - 2)))) {
                if (!latestTail.content.equals(current.content)) {
                    decision = false; //exit on discovery, therefore leaving list unarranged
                } else {
                    latestTail = latestTail.nextNode;
                }
            }
            counter++;
            previous = current;
            current = nextNode;
        }
        System.out.println("List After Check: Altered");
        return decision;
    }

    /**
     * Reverse the first half of the list and then compare (first half
     * backwards) against the second half forwards. reverse the left part of the
     * list back to normal as you check it backwards. This returns the list in
     * its original state
     *
     * @param list list to check if it is a palindrome
     * @return true/false returns true if content of linked list is a palindrome
     */
    public static boolean solve3(MySinglyLinkedList list) {
        if (list == null) {
            return false;
        }
        int listSize = list.size();
        if (listSize < 2) {
            return false;
        }
        System.out.println("List Received: " + list.thisToString());
        //check if even or odd palindrom and note midpoint
        int midPoint = 0;
        boolean isEven = false;
        if (listSize % 2 == 0) {
            midPoint = listSize / 2;
            isEven = true;
        } else {
            midPoint = (int) Math.ceil((float) listSize / 2);
            isEven = false;
        }
        
        MySinglyLinkedList.Node previous = null;
        MySinglyLinkedList.Node current = list.head;
        MySinglyLinkedList.Node next = list.head.nextNode;

        MySinglyLinkedList.Node reversePrevious = list.head.nextNode; //assuming tracking from the reverse direction
        MySinglyLinkedList.Node reverseCurrent = list.head;
        MySinglyLinkedList.Node reverseNext = null;
        

        int counter = 0;
        boolean decision = true;
        while (current != null) {
            next = current.nextNode;

            if (isEven) {
                if (counter <= midPoint - 1) {
                    reversePrevious = current.nextNode;
                    reverseCurrent = current;
                    current.nextNode = previous;
                    previous = current;
                } else {
                    if (reverseCurrent.content != current.content) {
                        decision = false;
                    }

                    reverseNext = reverseCurrent.nextNode;
                    reverseCurrent.nextNode = reversePrevious;
                    reversePrevious = reverseCurrent;
                    reverseCurrent = reverseNext;
                }

            } else if ((!isEven)) {
                if (counter < midPoint - 1) {
                    reversePrevious = current.nextNode;
                    reverseCurrent = current;
                    current.nextNode = previous;
                    previous = current;
                } else if (counter > midPoint - 1) {
                    if (reverseCurrent.content != current.content) {
                        decision = false;
                    }
                    reverseNext = reverseCurrent.nextNode;
                    reverseCurrent.nextNode = reversePrevious;
                    reversePrevious = reverseCurrent;
                    reverseCurrent = reverseNext;
                } else {

                }
            }
            counter++;
            current = next;
        }
        System.out.println("List After Check: " + list.thisToString());
        return decision;
    }

    /**
     * Linked list question 6 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<String> list = new MySinglyLinkedList();
        list.add("k");
        list.add("a");
        list.add("y");
        list.add("a");
        list.add("k");

        //list.add("n");
        //list.add("o");
        //list.add("o");
        //list.add("n");

        //list.add("a");
        //list.add("l");
        //list.add("u");
        //list.add("l");
        //list.add("a");
        boolean answer;

        //Find if is palindrome through in-place reversal of the first half of 
        //the list.Keep list unaltered
        System.out.println("METHOD 3");
        answer = solve3(list);
        System.out.println("Is Palindrome (Method 3): " + answer + "\n");

        //Find if its palindrom using a stacks
        System.out.println("METHOD 1");
        answer = solve(list);
        System.out.println("Is Palindrome (Method 1): " + answer + "\n");

        //Find if is palindrome through in-place reversal of the first half of 
        //the list. Exit on find, therefore list remains altered
        System.out.println("METHOD 2");
        answer = solve2(list);
        System.out.println("Is Palindrome Method 2): " + answer + "\n");

    }*/


}
