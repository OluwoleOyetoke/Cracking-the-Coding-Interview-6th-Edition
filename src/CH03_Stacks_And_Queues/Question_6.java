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
import java.util.LinkedList;
import java.util.Queue;

/**
 * <b>Animal Shelter:</b> An animal shelter, which holds only dogs and cats,
 * operates on a strictly"first in, first out" basis. People must adopt either
 * the "oldest" (based on arrival time) of all animals at the shelter, or they
 * can select whether they would prefer a dog or a cat (and will receive the
 * oldest animal of that type). They cannot select which specific animal they
 * would like. Create the data structures to maintain this system and implement
 * operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may
 * use the built-in Linked list data structure.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_6 {
    
    Queue dogQueue = new LinkedList();
    Queue catQueue = new LinkedList();
     
    /**
     * Enqueue animal
     * @param type type of animal
     * @param name  name of the animal
     */
    public void enqueue(int type, String name){
        if(type==0){ //dog
            dogQueue.add("Dog_"+name);
        }else{ //cat
            catQueue.add("Cat_"+name);
        }
    }
    
    /**
     * Dequeue both cat and dog
     * @return toReturn oldest cat and dog
     */
    public String[] dequeAny(){
        String[] toReturn = new String[2];
        toReturn[0] = String.valueOf(dogQueue.poll());
        toReturn[1] = String.valueOf(catQueue.poll());
        return toReturn;
    }
    /**
     * Dequeue dog
     * @return oldest dog
     */
    public String dequeueDog(){
     return String.valueOf(dogQueue.poll());   
    }
    
    /**
     * Dequeue cat
     * @return oldest cat
     */
    public String dequeueCat(){
        return String.valueOf(catQueue.poll());
    }
    
    /**
     * View shelter to see animals left
     */
    public void viewShelter(){
        System.out.println("SHELTER VIEW");
        if(!dogQueue.isEmpty()){
            System.out.println("Dogs in Shelter: "+Arrays.toString(dogQueue.toArray()));
        }else{
            System.out.println("There are no Dogs in shelter");
        }
        
        if(!catQueue.isEmpty()){
            System.out.println("Cats in Shelter: "+Arrays.toString(catQueue.toArray()));
        }else{
            System.out.println("There are no Cats in shelter");
        }
    }
    
    
    /**
     * Main method for stacks and queues question 6...uncomment to run
     * @param args command line arguments
     *//*
    public static void main(String[] args){
        Question_6 q = new Question_6();
        //Enqueue Dogs
        System.out.println("Enqueuing Dogs");
        q.enqueue(0, "Aladin");
        q.enqueue(0, "Shrek");
        q.enqueue(0, "Captain");
        q.enqueue(0, "Belaruz");
        q.viewShelter();
        
        System.out.println("Enqueuing Cats");
        //Enqueue Cats
        q.enqueue(1, "Meown");
        q.enqueue(1, "Raymond");
        q.enqueue(1, "Bean");
        q.enqueue(1, "Jag");
        q.viewShelter();
        
        System.out.println("Dequeue Any: "+Arrays.toString(q.dequeAny()));
        q.viewShelter();
        System.out.println("Dequeue Cat: "+q.dequeueDog());
        q.viewShelter();
        System.out.println("Dequeue Cat: "+q.dequeueCat());
        q.viewShelter();
    }*/
}
