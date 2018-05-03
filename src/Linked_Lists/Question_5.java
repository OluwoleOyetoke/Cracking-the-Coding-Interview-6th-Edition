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

/**
 * Sum Lists: You have two numbers represented by a linked list, where each node
 * contains a single digit. The digits are stored in reverse order, such that
 * the 1 's digit is at the head of the list. Write a function that adds the two
 * numbers and returns the sum as a linked list.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_5 {
    
    
    /**
     * Add rightwards then reverse final answer
     * @param list1 first linked list containing integers
     * @param list2 second linked list containing integers
     * @return answerList list containing the result of list1+list2 added together in reverse order
     */
    public static MySinglyLinkedList solve(MySinglyLinkedList<Integer> list1, MySinglyLinkedList<Integer> list2) {
        if (list1 == null || list2 == null) {
            return null;
        }

        //pad with zeros if necessary
        int lengthDiff = list1.size - list2.size;
        if (lengthDiff > 0) {
            for (int i = 0; i < lengthDiff; i++) {
                list2.add(0);
            }
        } else if (lengthDiff < 0) {
            for (int i = 0; i < (int) Math.abs(lengthDiff); i++) {
                list1.add(0);
            }
        }
        MySinglyLinkedList<Integer> answerList = new MySinglyLinkedList<>();
        MySinglyLinkedList.Node currentList1 = list1.head;
        MySinglyLinkedList.Node currentList2 = list2.head;
        int val = 0;
        int carry = 0;
        int use = 0;
        while (currentList1 != null) {
            val = (int) currentList1.content + (int) currentList2.content + carry;

            use = ((val - 10) < 0) ? val : (val - 10);
            carry = ((val - 10) >= 0) ? 1 : 0;
            if (currentList1.nextNode != null) {
                answerList.add(use);
            } else {
                if (carry == 0) {
                    answerList.add(use);
                } else {
                    answerList.add(use);
                    answerList.add(carry);
                }
            }
            currentList1 = currentList1.nextNode;
            currentList2 = currentList2.nextNode;
        }

        return reverse(answerList);
    }
    
    /**
     * Reverse the linked list
     * @param list
     * @return 
     */
    public static MySinglyLinkedList reverse(MySinglyLinkedList list) {
        if (list == null || list.size<2) {
            return null;
        }
        MySinglyLinkedList.Node previous = list.head;
        MySinglyLinkedList.Node current = list.head.nextNode;
        MySinglyLinkedList.Node next = current.nextNode;
        previous.nextNode = null;
        list.tail = previous;
        while (current != null) {
            next = current.nextNode;

            current.nextNode = previous;
            previous = current;

            if (next == null) {
                list.head = current;
            }
            current = next;
        }
        return list;
    }

    /**
     *Main method for Linked list question 5....uncomment to run
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<Integer> list1 = new MySinglyLinkedList<>();
        list1.add(7);
        list1.add(1);
        list1.add(6);
        MySinglyLinkedList<Integer> list2 = new MySinglyLinkedList<>();
        list2.add(5);
        list2.add(9);
        list2.add(9);
        
        System.out.println("ADD TOGETHER IN REVERSE ORDER: ");
        System.out.print(list1.thisToString()+" + "+list2.thisToString()+" = ");
        MySinglyLinkedList<Integer> answerList = solve(list1, list2);
        
        System.out.print(answerList.thisToString()+"\n");
    }*/
}
