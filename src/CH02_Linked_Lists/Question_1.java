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

import java.util.HashMap;

/**
 * <b>Remove Dumps</b> Write code to remove duplicates from an unsorted linked
 * list. FOLLOW UP How would you solve this problem if a temporary buffer is not
 * allowed?....page 94S
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}gmail.com{@literal >}
 */
public class Question_1 {

    /**
     * Use Hash Map to keep track of duplicates
     *
     * @param linkedList linked list from which duplicates should be removed
     */
    public static void solve(Q1_LinkedList linkedList) {
        HashMap map = new HashMap();
        if (linkedList.head != null) {
            Q1_LinkedList.Node current = linkedList.head;
            Q1_LinkedList.Node previous = linkedList.head;
            boolean check;
            while (current != null) {
                check = map.containsKey(current.content);
                if (check == true) {
                    previous.nextNode = current.nextNode;
                    current = current.nextNode;
                    linkedList.size--;
                } else {
                    map.put(current.content, 1);
                    previous = current;
                    current = current.nextNode;
                }
            }
        }
    }

    /**
     * Use two pointers
     * @param linkedList linked list from which duplicates are to be removed
     */
    public static void solve2(Q1_LinkedList linkedList) {
        if (linkedList.head != null) {
            Q1_LinkedList.Node current = linkedList.head;
            Q1_LinkedList.Node previous = linkedList.head;
            Q1_LinkedList.Node runner = linkedList.head;
            int count = 0;
            while (current != null) {

                runner = linkedList.head;
                boolean check = false;
                while (!runner.equals(current)) {
                    if (runner.content.equals(current.content)) {
                        check = true;
                        break;
                    }
                    runner = runner.nextNode;
                }

                if (check == true) {
                    previous.nextNode = current.nextNode;
                    current = current.nextNode;
                    linkedList.size--;
                } else {
                    previous = current;
                    current = current.nextNode;
                }
            }
        }
    }

    /**
     * Sort the linked list, then move through it linearly to remove duplicates
     */
    public static void solve3() {
        //use merge sort
    }

    /**
     * Linked list Question_1 main method.....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Q1_LinkedList linkedList = new Q1_LinkedList();
        Q1_LinkedList linkedList2 = new Q1_LinkedList();
        linkedList.add(0);
        linkedList2.add(0);
        for (int i = 0; i < 20; i++) {
            linkedList.add(i);
            linkedList2.add(i);
        }
        linkedList.add(4);
        linkedList2.add(4);
        linkedList.add(16);
        linkedList2.add(16);

        System.out.print("Linked List 1: ");
        linkedList.printList();
        System.out.print("Remove Duplicates (Using HashMaps): ");
        Question_1.solve2(linkedList);
        linkedList.printList();
        System.out.println("-------------------");

        System.out.print("Linked List 2: ");
        linkedList2.printList();
        System.out.print("Remove Duplicates (A Runner): ");
        Question_1.solve2(linkedList2);
        linkedList2.printList();
    }*/

    /**
     * Linked list implementation. Assuming Integer type only
     */
    static class Q1_LinkedList {

        Node head = new Node();
        Node tail = new Node();
        int size = 0;

        /**
         * Constructor
         */
        Q1_LinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        /**
         * Add to linked list
         *
         * @param content content to add
         * @return true/false true if operation was successful
         */
        private boolean add(int content) {
            Node newNode = new Node(content);
            if (head == null) {

                head = newNode;
                tail = head;
                size++;
            } else {
                Node current = head;
                while (current != null) {
                    if (current.nextNode == null) {
                        current.nextNode = newNode;
                        tail = current.nextNode;
                        size++;
                        return true;
                    }
                    current = current.nextNode;
                }
            }
            return false;
        }

        /**
         * Print content of linked list
         */
        private void printList() {
            if (head == null) {
                return;
            }
            Node current = head;
            System.out.println(" ");
            while (current != null) {
                System.out.print(current.content);
                if (current.nextNode != null) {
                    System.out.print(",");
                }
                current = current.nextNode;
            }
            System.out.println(" ");
        }

        /**
         * Node definition
         */
        class Node {

            Integer content;
            Node nextNode;

            /**
             * Constructor
             *
             * @param content content to pass into node object
             */
            Node(int content) {
                this.content = content;
            }

            /**
             * Default constructor
             */
            Node() {
                content = null;
                nextNode = null;
            }

            /**
             * Get content of node object
             *
             * @return content node content
             */
            public Integer getContent() {
                return content;
            }

            /**
             * Set node content
             *
             * @param content content in node
             */
            public void setContent(Integer content) {
                this.content = content;
            }

            /**
             * Get current node's next node
             *
             * @return nextNode next node
             */
            public Node getNextNode() {
                return nextNode;
            }

            /**
             * Set current node's next node
             *
             * @param nextNode next node
             */
            public void setNextNode(Node nextNode) {
                this.nextNode = nextNode;
            }

        }
    }
}
