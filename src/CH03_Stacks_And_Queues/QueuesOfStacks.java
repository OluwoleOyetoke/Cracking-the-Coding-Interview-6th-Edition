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
package CH03_Stacks_And_Queues;

import java.util.Arrays;
import java.util.Stack;

/**
 *<b>Queue via Stacks:</b> Implement a MyQueue class which implements a queue using two stacks.
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class QueuesOfStacks {
    
    private Stack stack = new Stack();
    private Stack queue = new Stack();
    
    /**
     * Push element into the queue
     * @param element element to be pushed into queue
     * @return true/false true if push was successful
     */
    public boolean push(int element){
        stack.push(element);
        return true;
    }
    
    /**
     * Retrieve element from the queue
     * @return element element from the queue 
     */
    public int pop(){
        //move bottom of stack to top of queue
        int  stackSize= stack.size();
        for(int i=0; i<stackSize; i++){
            queue.push((int)stack.pop());
            
        }
        int poped = (int) queue.pop();
        //move que back to stack
        int  queueSize= queue.size();
        for(int i=0; i<queueSize; i++){
            stack.push((int)queue.pop());
        }
        return poped;
    }
    
    /**
     * Used to check the door of the queue
     * @return peeked the value on top of the stack
     */
    public int peek(){
    //move bottom of stack to top of queue
    int  stackSize= stack.size();
        for(int i=0; i<stackSize; i++){
            queue.push(stack.pop());
        }    
        int peeked =(int) queue.peek();
        
         //move que back to stack
         int  queueSize= queue.size();
        for(int i=0; i<queueSize; i++){
            stack.push(queue.pop());
        }
        return peeked;
    }
    
    
    /**
     * Stacks and Queues Question 4 main method....uncomment to run
     * @param args Command line arguments
     *//*
    public static void main(String[] args){
       QueuesOfStacks qos = new QueuesOfStacks();
       for(int i=1; i<21; i++){
       qos.push(i);    
       }
       System.out.println("STACK: " + Arrays.toString(qos.stack.toArray()));
       System.out.println("POP: " + qos.pop());
       System.out.println("AFTER POP: " + Arrays.toString(qos.stack.toArray()));
       System.out.println("FIFO: 1 was the firs in, and it also is the first out");
       System.out.println("POP: " + qos.pop());
       System.out.println("AFTER POP: " + Arrays.toString(qos.stack.toArray()));
       System.out.println("PEEK: " + qos.peek());
    }*/
}
