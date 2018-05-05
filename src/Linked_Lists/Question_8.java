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
 * <b>Loop Detection:</b> Given a circular linked list, implement an algorithm
 * that returns the node at the beginning of the loop.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_8 {

    /**
     * Method just detects if list is cirular or not by using two runners.
     *
     * When the runner meet, it returns the pointer of one of the runners to the
     * head node and then counts step at a time from the head node and the
     * meeting spot. The point where this runners meet again is the begining of
     * the circle.
     *
     * @param list list to check
     * @return true/false returns true if list is circular and false if list is
     * not circular
     */
    public static MySinglyLinkedList.Node solve(MySinglyLinkedList list) {
        if (list == null || list.size < 2) {
            return null;
        }
        MySinglyLinkedList.Node slower = list.head;
        MySinglyLinkedList.Node faster = list.head;

        //If circularity exist, at some point, the slower and the faster nodes will meet each other
        while (faster != null) {
            slower = slower.nextNode;
            faster = faster.nextNode.nextNode;
            if (slower.equals(faster)) {
                slower = list.head;
                
                while(slower!=null){
                    if(slower.equals(faster)){
                        return slower;
                    }
                    slower = slower.nextNode;
                    faster = faster.nextNode;
                }
   
            }
        }
        //to get here means list is not circular
        return null;
    }

    /**
     * Linked list question 8 main method....uncomment to run
     *
     * @param args command line argument
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

        //make node with content 2 point back to head
        //list1.head.nextNode.nextNode.nextNode.nextNode.nextNode = list1.head;
        
        //or simply make tail point back to head....this will make the list circular
        list1.tail.nextNode = list1.head;
        
        MySinglyLinkedList.Node solved = solve(list1);
        if(solved!=null){
           System.out.println("If list is circular, where: " +solved.content ); 
        }else{
            System.out.println("List is not circular");
        }    
    }*/
}
