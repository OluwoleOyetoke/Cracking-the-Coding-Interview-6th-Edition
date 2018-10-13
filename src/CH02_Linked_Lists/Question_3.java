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

/**
 *<b>Delete Middle Node: </b>Implement an algorithm to delete a node in the middle (i.e., any node but
 * the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
 * that node.
 * <p><b>EXAMPLE</b></p>
 * Input: the node c from the linked list a-b-c-d-e-f
 * Result: nothing is returned, but the new linked list looks like a-b-d-e-f 
 * 
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_3 {
    
    /**
     * Delete node when given access to head and the content to delete
     * 
     * @param linkedList linked list to delete from
     * @param toDelete content of node to delete
     */
    public static void solve(MySinglyLinkedList<Integer> linkedList, Integer toDelete){
        linkedList.remove(toDelete);  
    }
    
    /**
     * Delete from linked list with only information about the node to delete. No access to head
     * @param nodeToDelete node to be deleted 
     */
    public static void solve2(MySinglyLinkedList.Node nodeToDelete){
        MySinglyLinkedList.Node current = nodeToDelete;
        MySinglyLinkedList.Node previous = nodeToDelete;
        while(current!=null){
            if(current.nextNode!=null){
            current.content = current.nextNode.content;
            }else{
                previous.nextNode = null;
            }
            previous=current;
            current = current.nextNode;
            
        }
    }
    
    
    
       /**
     * Main method for Linked List question 3.
     * Remove a node from the linked list...uncomment to run
     *
     * @param args Command line argument
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<Integer> linkedList = new MySinglyLinkedList<>();
        for (int i = 0; i < 8; i++) {
            linkedList.add(i * 7);
        }
        
        System.out.println("BEFORE REMOVAL: ");
        System.out.println(linkedList.thisToString());
        
         System.out.println("AFTER REMOVAL OF NODE WITH CONTENT 7: ");
         solve(linkedList, 7);
         System.out.println(linkedList.thisToString());
        
        
        System.out.println("NOW ALSO REMOVE NODE IN INDEX 1: ");
        solve2(linkedList.getNode(1)); //get Node in index 1
        System.out.println(linkedList.thisToString());
    }*/
}
