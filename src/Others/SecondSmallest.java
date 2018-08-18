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
package Others;

import java.util.Arrays;

/**
 * Find the second smallest number from an array of numbers
 *
 * @author Oluwole Oyetoke {@literal <}
 * oluwoleoyetoke{@literal @}gmail.com{@literal >}
 */
public class SecondSmallest {

    int[] minHeap;
    int HEAP_SIZE = 0;
    int cursor = 0;
    
    /**
     * Initializes the heap;
     * @param size size of the needed heap
     */
    public void makeHeap(int size) {
        minHeap = new int[size];
        HEAP_SIZE = size;
        cursor = 0;
    }
    
    /**
     * Add element to heap
     * @param toAdd element to add
     */
    public void addToHeap(int toAdd) {
        minHeap[cursor] = toAdd;
        heapifyUp(cursor);
        cursor++;
    }
    
    /**
     * Heapify downwards
     * @param cursor current position in heap
     */
    public void heapifyDown(int cursor) {
        int child1 = (cursor + 1) * 2;
        int child2 = ((cursor + 1) * 2) - 1;
        int tempAncestorPos = (int) (Math.ceil((double) cursor / 2) - 1);
        //System.out.println("Position: "+tempCursor+" Ancestor: "+tempAncestorPos);
        while (true) {
            if (child1 >= HEAP_SIZE && child2 >= HEAP_SIZE) {
                break;
            } else if (child1 < HEAP_SIZE && child2 < HEAP_SIZE) {
                if (minHeap[cursor] < minHeap[child1] && minHeap[cursor] < minHeap[child2]) {
                    break;
                } else if (minHeap[cursor] >= minHeap[child1] && minHeap[cursor] < minHeap[child2]) {
                    minHeap[cursor] = minHeap[cursor] + minHeap[child1];
                    minHeap[child1] = minHeap[cursor] - minHeap[child1];
                    minHeap[cursor] = minHeap[cursor] - minHeap[child1];
                    cursor = child1;
                    child1 = (cursor + 1) * 2;
                    child2 = ((cursor + 1) * 2) - 1;
                } else if (minHeap[cursor] < minHeap[child1] && minHeap[cursor] >= minHeap[child2]) {
                    minHeap[cursor] = minHeap[cursor] + minHeap[child2];
                    minHeap[child2] = minHeap[cursor] - minHeap[child2];
                    minHeap[cursor] = minHeap[cursor] - minHeap[child2];
                    cursor = child2;
                    child1 = (cursor + 1) * 2;
                    child2 = ((cursor + 1) * 2) - 1;
                } else if (minHeap[cursor] >= minHeap[child1] && minHeap[cursor] >= minHeap[child2]) {
                    int toSwap = 0;
                    if (minHeap[child1] >= minHeap[child2]) {
                        toSwap = child2;
                    } else {
                        toSwap = child1;
                    }
                    minHeap[cursor] = minHeap[cursor] + minHeap[toSwap];
                    minHeap[toSwap] = minHeap[cursor] - minHeap[toSwap];
                    minHeap[cursor] = minHeap[cursor] - minHeap[toSwap];
                    cursor = toSwap;
                    child1 = (cursor + 1) * 2;
                    child2 = ((cursor + 1) * 2) - 1;
                } else {
                    break;
                }
            } else if (child1 < HEAP_SIZE && child2 >= HEAP_SIZE) {
                if (minHeap[cursor] >= minHeap[child1]) {
                    minHeap[cursor] = minHeap[cursor] + minHeap[child1];
                    minHeap[child1] = minHeap[cursor] - minHeap[child1];
                    minHeap[cursor] = minHeap[cursor] - minHeap[child1];
                    cursor = child1;
                    child1 = (cursor + 1) * 2;
                    child2 = ((cursor + 1) * 2) - 1;
                } else {
                    break;
                }
            } else if (child1 >= HEAP_SIZE && child2 < HEAP_SIZE) {
                if (minHeap[cursor] >= minHeap[child2]) {
                    minHeap[cursor] = minHeap[cursor] + minHeap[child2];
                    minHeap[child2] = minHeap[cursor] - minHeap[child2];
                    minHeap[cursor] = minHeap[cursor] - minHeap[child2];
                    cursor = child2;
                    child1 = (cursor + 1) * 2;
                    child2 = ((cursor + 1) * 2) - 1;
                } else {
                    break;
                }
            }
        }
    }
    
    /**
     * Heapify upwards
     * @param cursor current position in heap
     */
    public void heapifyUp(int cursor) {
        int tempAncestorPos = (int) (Math.ceil((double) cursor / 2) - 1);
        //System.out.println("Position: "+tempCursor+" Ancestor: "+tempAncestorPos);
        while (true) {
            if (tempAncestorPos < 0) {
                break;
            }
            if (minHeap[cursor] <= minHeap[tempAncestorPos]) {
                minHeap[cursor] = minHeap[cursor] + minHeap[tempAncestorPos];
                minHeap[tempAncestorPos] = minHeap[cursor] - minHeap[tempAncestorPos];
                minHeap[cursor] = minHeap[cursor] - minHeap[tempAncestorPos];
            } else {
                break;
            }
            cursor = tempAncestorPos;
            tempAncestorPos = (int) (Math.ceil((double) cursor / 2) - 1);
        }
    }
    
    /**
     * Delete from heap
     * @param val value to delete
     */
    public void delete(int val) {
        int pos = 0;
        int ancestorPos = 0;
        int temp = 0;
        for (int i = 0; i < minHeap.length; i++) {
            if (minHeap[i] == val) {
                //swap with the last value in the array
                minHeap[i] = minHeap[cursor - 1];
                minHeap[cursor - 1] = Integer.MAX_VALUE;
                cursor--;
                //decide if to hippify up or down
                pos = i;
                ancestorPos = (int) (Math.ceil((double) (pos / 2)) - 1);
                if (ancestorPos >= 0) {
                    if (minHeap[pos] <= minHeap[ancestorPos]) {
                        heapifyUp(pos);
                        break;
                    } else {
                        heapifyDown(pos);
                        break;
                    }
                } else {
                    heapifyDown(pos);
                    break;
                }
            }
        }
        System.out.println("After Deleting: " + val + " Heap Array is: ");
        System.out.print("[ ");
        for (int i = 0; i < cursor; i++) {
            if (i == cursor - 1) {
                System.out.print("" + minHeap[i] + " ]\n");
            } else {
                System.out.print("" + minHeap[i] + ", ");
            }
        }
    }
    
    /**
     * Find mean value in heap
     * @return min min value
     */
    public int min() {
        if (minHeap == null) {
            System.out.println("Throw Eception here");
            //throw new --;
        }
        return minHeap[0];
    }
    
   /**
    * Get second smallest number in array through min heap
    * @param input input array
    * @return second smallest
    */
    public int secondSmallest(int[] input) {
        makeHeap(input.length);
        for (int i = 0; i < input.length; i++) {
            addToHeap(input[i]);
        }
        System.out.println("Min Heap Array: " + Arrays.toString(minHeap));
        delete(min());
        System.out.println("Second smallest number is "+min());
        return min();
    }
    
    
   /**
    * Get second smallest number in array by 
    * initially sorting and then traversing to find the second smallest
    * @param input input array
    * @return second smallest
    */
    public int secondSmallest2(int[] input){
        if(input==null || input.length==0){
            System.out.println("Throw exception here");
            return Integer.MIN_VALUE;
        }
        Arrays.sort(input);
        int smallest = input[0];
        for(int i=0; i<input.length; i++){
            if(input[i]>smallest){
                System.out.println("Second smallest is: "+input[i]);
                return input[i];
            }
        }
        return Integer.MIN_VALUE; //array is full of the same numbers
    }
    
    /**
    * Get second smallest number in array by scanning through array once
    * @param input input array
    * @return second smallest
    */
    public int secondSmallest3(int[] input){
        if(input==null || input.length==0){
            System.out.println("Throw exception here");
            return Integer.MIN_VALUE;
        }
        int smallest = input[0];
        int secondSmallest = input[0];
        for(int i=0; i<input.length; i++){
            if(input[i]<secondSmallest && input[i]<smallest){
                secondSmallest = smallest;
                smallest = input[i];
            }else if(input[i]>secondSmallest && input[i]>smallest){
                //do nothing
            }else if(input[i]<secondSmallest && input[i]>smallest){
                secondSmallest = input[i];
            }else{
               //do nothing 
            }
        }
        return secondSmallest; //array is full of the same numbers
    }
    
  
    /**
     * Main method... uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {

        int[] toSort = {9, 8, 0, 12, -1, 13, 88, 56, 432, 3, 3, 4, 2, 1, 7, 5};
        int[] result;
        SecondSmallest secondSmallest = new SecondSmallest();
        System.out.println("To Sort: " + Arrays.toString(toSort));
        secondSmallest.secondSmallest(toSort); //method 1
        secondSmallest.secondSmallest2(toSort); //method 2
        secondSmallest.secondSmallest3(toSort); //method 3
        
        //secondSmallest.delete(-1);
        //secondSmallest.delete(0);
        //secondSmallest.delete(1);
        //secondSmallest.delete(2);
        //secondSmallest.delete(5);
        //secondSmallest.delete(3);
        //secondSmallest.delete(3);
        //secondSmallest.delete(3);
    }
*/
}
