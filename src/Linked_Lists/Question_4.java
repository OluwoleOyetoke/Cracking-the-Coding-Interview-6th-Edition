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
 * <b>Partition:</b> Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater than or equal
 * to x. If x is contained within the list, the values of x only need to be
 * after the elements less than x (see below). The partition element x can
 * appear anywhere in the "right partition"; it does not need to appear between
 * the left and right partitions.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_4 {

    /**
     * Partitioning the linked list with regards to ordering of the elements
     * partitioned. Add lesser elements to the head and bigger or equal elements
     * to tail
     *
     * @param linkedList list to partition
     * @param partitionElement partition value
     * @return partitionedList partitioned list
     */
    public static MySinglyLinkedList<Integer> solve(MySinglyLinkedList<Integer> linkedList, int partitionElement) {
        MySinglyLinkedList<Integer> lessThanList = new MySinglyLinkedList<>();
        MySinglyLinkedList<Integer> greaterThanList = new MySinglyLinkedList<>();
        MySinglyLinkedList.Node current = linkedList.head;
        while (current != null) {
            if ((int) current.content < partitionElement) {
                lessThanList.add((Integer) current.content);
            } /*else if ((int) current.content == partitionElement) {
            //if partition elelment must be immediately after the elements less than it    
            MySinglyLinkedList.Node newNode = greaterThanList.new Node((Integer) current.content);
                newNode.nextNode = greaterThanList.head;
                greaterThanList.head = newNode;
            }  */ else {
                greaterThanList.add((Integer) current.content);
            }
            current = current.nextNode;
        }
        lessThanList.tail.nextNode = greaterThanList.head;
        return lessThanList;
    }

    /**
     * Partitioning the linked list in place but without regards to ordering of
     * the elements partitioned. Add lesser elements to the head and bigger or
     * equal elements to tail
     *
     * @param linkedList list to partition
     * @param partitionElement partition value
     * @return partitionedList partitioned list
     */
    public static MySinglyLinkedList<Integer> solve2(MySinglyLinkedList<Integer> linkedList, int partitionElement) {
        if (linkedList == null || linkedList.size() < 2) {
            return linkedList;
        }
        MySinglyLinkedList.Node current = linkedList.head;
        MySinglyLinkedList.Node itterator = linkedList.head;
        MySinglyLinkedList.Node defaultTail = linkedList.tail;
        MySinglyLinkedList.Node initialTail = linkedList.tail;
        MySinglyLinkedList.Node initialHead = linkedList.head;
        int headCount = 0;
        int tailCount = 0;
        while (current != null) {
            itterator = current.nextNode;

            if ((int) current.content < partitionElement) {
                current.nextNode = linkedList.head;
                linkedList.head = current;
                if (headCount == 0) {
                    initialHead = linkedList.head;
                }
                headCount++;
            } else {
                current.nextNode = null;
                linkedList.tail.nextNode = current;
                linkedList.tail = current;
                if (tailCount == 0) {
                    initialTail = linkedList.tail;
                }
                tailCount++;
            }

            if (current.equals(defaultTail)) {
                break;
            }
            current = itterator;
        }
        if (headCount == 0) {
            linkedList.head = initialTail;
        } else if (tailCount == 0) {
            linkedList.tail = initialHead;
        } else {
            initialHead.nextNode = initialTail;
        }
        return linkedList;
    }

    public static MySinglyLinkedList<Integer> resolve2(MySinglyLinkedList<Integer> linkedList, int partitionElement) {
        if (linkedList == null || linkedList.size() < 2) {
            return linkedList;
        }

        MySinglyLinkedList.Node current = linkedList.head;
        MySinglyLinkedList.Node left = linkedList.head;
        MySinglyLinkedList.Node right = linkedList.tail;

        MySinglyLinkedList.Node firstLeft = linkedList.head;
        MySinglyLinkedList.Node firstRight = linkedList.head;

        int leftCount = 0;
        int rightCount = 0;
        while (current != null) {
            if ((int) current.content < partitionElement) {
                if (leftCount == 0) {
                    left = current;
                    firstLeft = current;
                } else {
                    left.nextNode = current;
                    left = current;
                }
                leftCount++;
            } else {
                if (rightCount == 0) {
                    right = current;
                    firstRight = current;
                } else {
                    right.nextNode = current;
                    right = current;
                }
                rightCount++;
            }
            current = current.nextNode;
        }
        if (leftCount == 0) {
            linkedList.head = firstRight;
            right.nextNode = null;
            linkedList.tail = right;
        } else if (rightCount == 0) {
            linkedList.head = firstLeft;
            left.nextNode = null;
            linkedList.tail = left;
        }
        left.nextNode = firstRight;
        return linkedList;
    }

    /**
     * Main method for Linked list question 4....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<Integer> linkedList = new MySinglyLinkedList<>();
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(8);
        linkedList.add(5);
        linkedList.add(10);
        linkedList.add(2);
        linkedList.add(1);

        System.out.println("BEFORE PARTITIONING: ");
        System.out.println(linkedList.thisToString());

        System.out.println("AFTER PARTITIONING WITH 3: ");
        MySinglyLinkedList<Integer> returnedList = solve(linkedList, 3);
        if (returnedList != null) {
            System.out.println(returnedList.thisToString());
        }
        System.out.println("AFTER PARTITIONING WITH 5 (IN PLACE): ");
        MySinglyLinkedList<Integer> returnedList2 = solve2(linkedList, 5);
        if (returnedList2 != null) {
            System.out.println(returnedList2.thisToString());
        }

        System.out.println("AFTER PARTITIONING WITH 5 (IN PLACE): ");
        MySinglyLinkedList<Integer> returnedList3 = resolve2(linkedList, 5);
        if (returnedList3 != null) {
            System.out.println(returnedList3.thisToString());
        }
    }*/
}
