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
package Linked_Lists;

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

        while (current != null) {
            if ((evenPalindrome == true && counter <= (midPoint - 1)) || ((evenPalindrome == false) && (counter < (midPoint - 1)))) {
                stack.add(current.content);
            } else if ((evenPalindrome == true && counter > (midPoint - 1)) || ((evenPalindrome == false) && (counter > (midPoint - 1)))) {
                if (!stack.pop().equals(current.content)) {
                    return false;
                }
            }
            counter++;
            current = current.nextNode;
        }
        return true;
    }

    /**
     * Making the check in O(N) time and without any other extra data structure
     * such as a stack.
     *
     * List left un arranged
     *
     * @param list list to check if it is a palindrome
     * @return true/false returns true if content of linked list is a palindrome
     */
    public static boolean solve2(MySinglyLinkedList list) {
        //Quick exit criteria
        if (list == null) {
            return false;
        } else if (list.size == 1) {
            return false;
        }
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

        MySinglyLinkedList.Node latestTail = list.head;

        MySinglyLinkedList.Node previous = list.head;
        MySinglyLinkedList.Node current = list.head.nextNode;
        MySinglyLinkedList.Node nextNode = current.nextNode;

        while (current != null) {
            nextNode = current.nextNode;
            if ((evenPalindrome == true && counter <= (midPoint - 2)) || ((evenPalindrome == false) && (counter < (midPoint - 2)))) {
                current.nextNode = previous;
                latestTail = current;
            } else if ((evenPalindrome == true && counter > (midPoint - 2)) || ((evenPalindrome == false) && (counter > (midPoint - 2)))) {
                if (!latestTail.content.equals(current.content)) {
                    return false;
                } else {
                    latestTail = latestTail.nextNode;
                }
            }
            counter++;
            previous = current;
            current = nextNode;
        }
        return true;
    }

    /**
     * Linked list question 6 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<String> list = new MySinglyLinkedList();
        list.add("a");
        list.add("a");
        list.add("l");
        list.add("u");
        list.add("l");
        list.add("a");
        list.add("a");

        boolean returnedList = solve(list);
        System.out.println(returnedList);
        boolean returnedList2 = solve2(list);
        System.out.println(returnedList2);
    }*/
}
