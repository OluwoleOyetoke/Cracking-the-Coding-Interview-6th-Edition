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
package Stacks_And_Queues;

import java.sql.Timestamp;
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
public class Question_6b {
    
       
    /**
     * Tests the shelter structure class
     * @throws InterruptedException due to Thread.sleep used
     */
       public void solve() throws InterruptedException {
           shelterStructure ss = new shelterStructure(); 
           System.out.println("Enqueuing Dogs");
            ss.enqueue(new Dogs(new Timestamp(System.currentTimeMillis()), "Aladin"));
            ss.enqueue(new Dogs(new Timestamp(System.currentTimeMillis()), "Shrek"));
            ss.enqueue(new Dogs(new Timestamp(System.currentTimeMillis()), "Captain"));
            ss.enqueue(new Dogs(new Timestamp(System.currentTimeMillis()), "Belaruz"));
            ss.enqueue(new Dogs(new Timestamp(System.currentTimeMillis()), "Jaden"));
            ss.viewShelter();
            Thread.sleep(50);
            System.out.println("Enqueuing Cats");
            ss.enqueue(new Cats(new Timestamp(System.currentTimeMillis()), "Meown"));
            ss.enqueue(new Cats(new Timestamp(System.currentTimeMillis()), "Raymond"));
            ss.enqueue(new Cats(new Timestamp(System.currentTimeMillis()), "Bean"));
            ss.enqueue(new Cats(new Timestamp(System.currentTimeMillis()), "Jag"));
            ss.enqueue(new Cats(new Timestamp(System.currentTimeMillis()), "Barkk"));
            ss.viewShelter();
            System.out.println("Dequeue Any: " + ss.dequeAny().getName());
            ss.viewShelter();
            System.out.println("Dequeue Dog: " + ss.dequeueDog().getName());
            ss.viewShelter();
            System.out.println("Dequeue Cat: " + ss.dequeueCat().getName());
            ss.viewShelter();
        }
    
    
    /**
     * Data structure to help carry out the animal selection sorting
     */
    class shelterStructure {

        Queue<Dogs> dogQueue = new LinkedList();
        Queue<Cats> catQueue = new LinkedList();

        /**
         * Enqueue animal
         *
         * @param type type of animal
         * @param name name of the animal
         */
        public void enqueue(Animal animal) {
            if (animal instanceof Dogs) {
                dogQueue.add((Dogs) animal);
            } else {
                catQueue.add((Cats) animal);
            }
        }

        /**
         * Dequeue either cat and dog
         *
         * @return toReturn oldest cat and dog
         */
        public Animal dequeAny() {
            if(dogQueue.isEmpty() && catQueue.isEmpty()){
                return null;
            }else if(dogQueue.isEmpty() && !catQueue.isEmpty()){
                return catQueue.poll();
            }else if(!dogQueue.isEmpty() && catQueue.isEmpty()){
                return dogQueue.poll();
            }

            Timestamp dogTime = dogQueue.peek().getTimeIn();
            Timestamp catTime = catQueue.peek().getTimeIn();
            if(dogTime.after(catTime)){
                return catQueue.poll();
            }else if(catTime.after(dogTime)){
                 return dogQueue.poll();
            }else{
                System.out.println(dogTime.toString()+"-&-"+catTime.toString());
                System.out.println("Same time");
                return catQueue.poll(); //incase same timestamp exists
            }
        }

        /**
         * Dequeue dog
         *
         * @return oldest dog
         */
        public Animal dequeueDog() {
            return dogQueue.poll();
        }

        /**
         * Dequeue cat
         *
         * @return oldest cat
         */
        public Animal dequeueCat() {
            return catQueue.poll();
        }

        /**
         * View shelter to see animals left
         */
        public void viewShelter() {
            System.out.println("SHELTER VIEW");
            if (!dogQueue.isEmpty()) {
                System.out.println("Dogs in Shelter: " + Arrays.toString(dogQueue.toArray()));
            } else {
                System.out.println("There are no Dogs in shelter");
            }

            if (!catQueue.isEmpty()) {
                System.out.println("Cats in Shelter: " + Arrays.toString(catQueue.toArray()));
            } else {
                System.out.println("There are no Cats in shelter");
            }
        }

    }
    
    /**
     * Animal superclass
     */
    abstract class Animal {
        String name;
        Timestamp timeIn;
        
     Animal(Timestamp timeIn, String name) {
            this.timeIn = timeIn;
            this.name = name;
        }
         public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Timestamp getTimeIn() {
            return timeIn;
        }

        public void setTimeIn(Timestamp timeIn) {
            this.timeIn = timeIn;
        }
        
        @Override
        public String toString(){
            return this.name;
        }
    }

    class Cats extends Animal {

        Cats(Timestamp timeIn, String name) {
            super(timeIn,name);
            this.timeIn = timeIn;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Timestamp getTimeIn() {
            return timeIn;
        }

        @Override
        public void setTimeIn(Timestamp timeIn) {
            this.timeIn = timeIn;
        }
        String name;
        Timestamp timeIn;
    }

    class Dogs extends Animal {

        Dogs(Timestamp timeIn, String name) {
            super(timeIn,name);
            this.timeIn = timeIn;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Timestamp getTimeIn() {
            return timeIn;
        }

        @Override
        public void setTimeIn(Timestamp timeIn) {
            this.timeIn = timeIn;
        }
        String name;
        Timestamp timeIn;
    }

    /**
     * Main method for stacks and queues question 6...uncomment to run
     *
     * @param args command line arguments
     * @throws InterruptedException due to the Thread.sleeps added
     */
    public static void main(String[] args) throws InterruptedException {
        Question_6b q = new Question_6b();
        q.solve();

    }
}
