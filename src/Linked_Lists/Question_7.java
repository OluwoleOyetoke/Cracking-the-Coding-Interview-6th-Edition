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
 * <b>Intersection:</b> Given two (singly) linked lists, determine if the two
 * lists intersect. Return the intersecting node. Note that the intersection is
 * defined based on reference, not value. That is, if the kth node of the first
 * linked list is the exact same node (by reference) as the jth node of the
 * second linked list, then they are intersecting.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_7 {

    /**
     * Check to see if intersecting node exists and also return node where
     * intersection happens.
     *
     * Find intersecting node by reversing one of the list and keeping the other
     * one constant. Move backwards from the reversed list (1 at a time) while
     * you move forward from the list kept constant (full swoop), for every one
     * step movement on the reversed list.
     *
     * Remember now, the previous tail of list2 is now its new head, and it is
     * from this new head we start the 'backward' tracking (1 at a time).
     *
     * Every step moved backwards on the reversed list, move forward in a full
     * swoop on the un-reversed list. During one of the itterations, the
     * backward move will be on a node traversed by the full swoop forward
     * movement of the un-reversed list. The point at which this forward swoop
     * meets the back ward track is the intersecting point.
     *
     * This is because the forward move of list 1 will never reach its original
     * tail again. It will start from its head and end at the (initial) head of
     * list 2 (which is now its tail), because through the reversal process of
     * list 2, the intersecting node now has its pointer pointing backwards to
     * the head of list 2 (now list 2 tail). Therefore, list one will always
     * find itself ending up at the (initial) head of list 2 without being able
     * to get to its own original tail again.
     *
     * As list 2 tracks back one step at a time from its original tail (now its
     * head), the point at which list 1's track forward touches list 2 track
     * backwards is the point where the diversion has been happening, and that
     * is the intersecting node
     *
     * ...Good solution, but not the most efficient
     *
     * @param list1 linked list 1
     * @param list2 linked list 2
     * @return intersection node at which intersection happened (if any)
     */
    public static MySinglyLinkedList.Node solve(MySinglyLinkedList list1, MySinglyLinkedList list2) {
        if (list1 == null || list2 == null || list1.size() < 2 || list2.size() < 2) {
            return null;
        }
        boolean isIntersecting = checkForIntersection(list1, list2);
        if (isIntersecting == false) {
            return null;
        }

        MySinglyLinkedList reversedList2 = reverse(list2);
        MySinglyLinkedList.Node current = reversedList2.head;

        //move and track
        boolean check;
        while (current != null) {
            check = trackAndCheck(list1, current);
            if (check == true) {
                return current;
            }
            current = current.nextNode;
        }
        return null;
    }

    /**
     * Tracks to see if the forward swoop will intersect with the current
     * position of the backward track
     *
     * @param list1 un-reversed list
     * @param list2Current current node on the reversed list
     * @return true/false true if the forward swoop traverses through a node
     * that is the same as the current node on the reversed list
     */
    public static boolean trackAndCheck(MySinglyLinkedList list1, MySinglyLinkedList.Node list2Current) {
        MySinglyLinkedList.Node list1Current = list1.head;
        while (list1Current != null) {
            if (list1Current.equals(list2Current)) {
                return true;
            }
            list1Current = list1Current.nextNode;
        }
        return false;
    }

    /**
     * Reverse the linked list
     *
     * @param list list to reverse
     * @return list reversed list
     */
    public static MySinglyLinkedList reverse(MySinglyLinkedList list) {
        if (list == null || list.size() < 2) {
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
            current = next;
        }
        list.head = previous;
        return list;
    }

    /**
     * Check to see if intersecting node exists
     *
     * @param list1 linked list 1
     * @param list2 linked list 2
     * @return true/false returns true if intersection exists and false if
     * otherwise
     */
    public static boolean checkForIntersection(MySinglyLinkedList list1, MySinglyLinkedList list2) {
        if (list1.size() < 2 || list2.size() < 2) {
            return false;
        }
        MySinglyLinkedList.Node list1Current = list1.head;
        MySinglyLinkedList.Node list2Current = list2.head;
        while (list1Current.nextNode != null) {
            list1Current = list1Current.nextNode;
        }
        while (list2Current.nextNode != null) {
            list2Current = list2Current.nextNode;
        }
        //check if tails equals to each other
        if (list1Current.equals(list2Current)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Balance the length of both linked list from the tail, then trace forwards
     *
     * @param listA list 1
     * @param listB list 2
     * @return intersection intersecting Node
     */
    public static MySinglyLinkedList.Node solve2(MySinglyLinkedList listA, MySinglyLinkedList listB) {
        if (listA == null || listB == null) {
            return null;
        }
        int listASize = listA.size();
        int listBSize = listB.size();
        if (listASize < 2 || listBSize < 2) {
            return null;
        }
        boolean isIntersecting = checkForIntersection(listA, listB);
        if (isIntersecting == false) {
            return null;
        }

        int diff = Math.abs(listASize - listBSize);
        int longest = (listASize >= listBSize) ? listASize : listBSize;
        boolean longerA = (listASize >= listBSize) ? true : false;

        MySinglyLinkedList.Node currentA = listA.head;
        MySinglyLinkedList.Node currentB = listB.head;

        for (int i = 0; i < longest; i++) {
            if (currentA.equals(currentB)) {
                return currentA;
            }
            if (longerA && i < diff) {
                currentA = currentA.nextNode;
            } else if ((!longerA) && (i < diff)) {
                currentB = currentB.nextNode;
            } else {
                currentB = currentB.nextNode;
                currentA = currentA.nextNode;
            }
        }
        return null;
    }
    
    /**
     * Just a resolve
     * @param listA list 1
     * @param listB list 2
     * @return intersection node where intersection happened (if any)
     */
    public static MySinglyLinkedList.Node resolve(MySinglyLinkedList listA, MySinglyLinkedList listB) {
        if (listA == null || listB == null) {
            return null;
        }
        int listASize, listBSize;
        listASize = listA.size();
        listBSize = listB.size();
        if (listASize < 2 || listBSize < 2) {
            return null;
        }
        boolean isIntersecting = checkForIntersection(listA, listB);
        if (isIntersecting == false) {
            return null;
        }

        //get length differeces
        int difference = Math.abs(listASize - listBSize);
        boolean isALongest = (listASize >= listBSize) ? true : false;

        MySinglyLinkedList.Node itterator;
        MySinglyLinkedList.Node normal;

        if (isALongest) {
            itterator = listA.head;
            normal = listB.head;
        } else {
            itterator = listB.head;
            normal = listA.head;
        }
        int counter = 1;
        while (itterator != null) {
            if(itterator.equals(normal)){
                return itterator;
            }
            if (counter <= difference) {
                itterator = itterator.nextNode;
            } else if (counter > difference) {
                itterator = itterator.nextNode;
                normal = normal.nextNode;
            }
            counter++;
        }
        return null;
    }

    /**
     * Linked List question_7 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<Integer> list1 = new MySinglyLinkedList<>();
        list1.add(7);
        list1.add(1);
        list1.add(6);
        list1.add(10);
        list1.add(2);
        list1.add(17);
        list1.add(66);
        list1.add(3);
        MySinglyLinkedList<Integer> list2 = new MySinglyLinkedList<>();
        list2.add(5);
        list2.add(9);
        list2.add(44);
        list2.add(97);
        list2.add(26);
        list2.add(33);
        list2.add(41);

        System.out.println("List 1 before intersection: " + list1.thisToString());
        System.out.println("List 2 before intersection: " + list2.thisToString());

        //make list intersect from list2 node with content 97 and list 1 content 6
        list2.head.nextNode.nextNode.nextNode.nextNode = list1.head.nextNode.nextNode;

        System.out.println("List 1 after intersection: " + list1.thisToString());
        System.out.println("List 2 after intersection: " + list2.thisToString());
        //MySinglyLinkedList.Node intersection = solve(list1, list2);
        MySinglyLinkedList.Node intersection = solve2(list1, list2);
        //MySinglyLinkedList.Node intersection = resolve(list1, list2);

        if (intersection != null) {
            System.out.println("Intersecting Node: " + intersection.content);
        } else {
            System.out.println("No intersection");
        }
    }*/
}
