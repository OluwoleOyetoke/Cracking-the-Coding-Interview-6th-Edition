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

import java.util.LinkedList;
import java.util.Stack;

/**
 * <b>Stack of Plates:</b> Imagine a (literal) stack of plates. If the stack
 * gets too high, it might topple. Therefore, in real life, we would likely
 * start a new stack when the previous stack exceeds some threshold. Implement a
 * data structure SetOfStacks that mimics this. SetO-fStacks should be composed
 * of several stacks and should create a new stack once the previous one exceeds
 * capacity. SetOfStacks. push() and SetOfStacks. pop() should behave
 * identically to a single stack (that is, pop ()
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class SetOfStacks {

    static int currentBucketIndex = 0;
    static int currentAvailabeBuckets = 0;
    static int maxStackSize = 0;
    static LinkedList<Stack> setOfStacks = new LinkedList<>();

    /**
     * Default constructor
     */
    SetOfStacks() {
        currentAvailabeBuckets = 1;
        currentBucketIndex = 0;
        Stack<Integer> newStack = new Stack<>();
        setOfStacks.add(newStack);
        maxStackSize = 20;
    }

    /**
     * Constructor
     *
     * @param maxStackSize maximum size per stack
     */
    SetOfStacks(int maxStackSize) {
        currentAvailabeBuckets = 1;
        currentBucketIndex = 0;
        Stack<Integer> newStack = new Stack<>();
        setOfStacks.add(newStack);
        this.maxStackSize = maxStackSize;
    }

    /**
     * Push new value to stack
     *
     * @param element element to push
     * @return true/false returns true if push was successful
     */
    public static boolean push(Integer element) {
        Stack currentBucket = setOfStacks.get(currentBucketIndex);
        boolean isBucketFull = isBucketFull(currentBucket);
        if (!isBucketFull) {
            setOfStacks.get(currentBucketIndex).add(element);
        } else {
            if (currentBucketIndex + 1 < currentAvailabeBuckets) {
                currentBucketIndex++;
            } else {
                currentBucketIndex++;
                currentAvailabeBuckets++;
                Stack<Integer> newStack = new Stack<>();
                setOfStacks.add(newStack);
            }
            setOfStacks.get(currentBucketIndex).add(element);
            System.out.println("Bucket " + currentBucketIndex + " was full. New bucket created");
        }
        return true;
    }

    /**
     * Pop from set of stacks
     *
     * @return poped poped value
     */
    public static int pop() {
        if (currentBucketIndex < 0) {
            System.out.println("Entire set of stacks is empty");
            currentBucketIndex = 0;
            return -1;
        }

        Stack currentBucket = setOfStacks.get(currentBucketIndex);
        boolean bucketIsEmpty = isBucketEmpty(currentBucket);
        int poped;
        if (!bucketIsEmpty) {
            return (int) setOfStacks.get(currentBucketIndex).pop();
        } else {
            System.out.println("Attempting to moving to a lower bucket");
            currentBucketIndex--;
            poped = pop();
        }
        return poped;
    }

    /**
     * Pops stack at a particular index of the set
     *
     * @param bucketIndex bucket with stack to pop
     * @return poped poped value
     */
    public static int popAt(int bucketIndex) {
        if (bucketIndex > currentBucketIndex) {
            System.out.println("Bucket Index out of bound");
            return -1;
        } else {
            Stack currentBucket = setOfStacks.get(bucketIndex);
            if (currentBucket.isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            } else {
                return (int) setOfStacks.get(bucketIndex).pop();
            }
        }
    }

    public static boolean isBucketEmpty(Stack bucket) {
        return bucket.isEmpty();
    }

    /**
     * Checks if bucket is full
     *
     * @param bucket bucket to check
     * @return true/false return true if bucket is empty
     */
    public static boolean isBucketFull(Stack bucket) {
        if (bucket.size() >= maxStackSize) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Main method for stacks & queues question 3....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        SetOfStacks sos = new SetOfStacks(20);
        for (int i = 0; i < 101; i++) {
            sos.push((int) i * 9);
        }

        System.out.println("Sets of Stacks Created: " + sos.currentAvailabeBuckets);
        int currentlyOn = sos.currentBucketIndex+1;
        System.out.println("Currently on Set: " + currentlyOn);

        System.out.println("\nSTARTING POPS");
        for (int i = 0; i < 66; i++) {
            System.out.println("POP: " + sos.pop());
        }
        System.out.println("POP At Set 0 (should be 171): " + sos.popAt(0));
        System.out.println("Currently on Set: " + sos.currentBucketIndex);
         for (int i = 0; i < 38; i++) {
            System.out.println("POP: " + sos.pop());
        }
    }*/
}
