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
package com.cracking.the.coding.interview.chapter03.stacksandqueus.datastructure;

import java.util.LinkedList;

/**
 * Queue data structure implementation
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class MyQueue<Type> {

    LinkedList<Type> queue = new LinkedList();
    
    /**
     * Add element to the queue
     * @param element element to add
     * @return true/false returns true if element was successfully added
     */
    boolean enqueue(Type element){
        queue.add(element);
        return true;
    }
    
    /**
     * Check the element at the queue door
     * @return element next element the door of the queue
     */
    Type peek(){
        return queue.element();
    }
    
    /**
     * Dequeue
     * @return element element removed 
     */
    Type dequeue(){
        return queue.poll();
    }
    
    /**
     * Converts queue into a string representation
     * @return queueString stack string
     */
    String thisToString(){
        Type[] arr;
        arr = (Type[]) queue.toArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            if (i != arr.length-1) {
                sb.append(String.valueOf(arr[i]));
                sb.append(",");
            } else {
                sb.append(String.valueOf(arr[i]));
            }    
        }
        return sb.toString();
    }
    
    /**
     * Main method for queue implementation......uncomment to run
     * @param args Command line argument
     *//*
    public static void main(String[] args){
           MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue((Integer) i * 9);
        }

        System.out.println("QUEUE: " + queue.thisToString());
        System.out.println("DEQUEUE: " + queue.dequeue());
        System.out.println("AFTER DEQUEUE: " + queue.thisToString());
        System.out.println("ENQUEUE 141: " + queue.enqueue(141));
        System.out.println("QUEUE: " + queue.thisToString());
        System.out.println("PEEK: " + queue.peek());
    }
*/
}
