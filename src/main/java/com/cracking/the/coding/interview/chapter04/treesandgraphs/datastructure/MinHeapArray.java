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
package com.cracking.the.coding.interview.chapter04.treesandgraphs.datastructure;

/**
 * <b>Min Heap Implementation</b> using an Array
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class MinHeapArray {

    int size;
    int[] heap;
    int currentSize;

    /**
     * Default constructor
     *
     * @param size size of the heap
     */
    public MinHeapArray(int size) {
        this.size = size;
        heap = new int[size];
        currentSize = -1;
    }

    /**
     * Used to insert into the heap
     *
     * @param value value to insert
     * @return true/false true if operation is successful
     */
    public boolean insert(int value) {
        if (currentSize >= (size - 1)) {
            System.out.println("Heap is full");
            return false;
        }
        currentSize++;
        heap[currentSize] = value;
        heapifyUp(currentSize);
        return true;
    }

    /**
     * Used to delete from the heap
     *
     * @param value value to add
     * @return true/false true if operation is successful
     */
    public boolean delete(int value) {
        int position = 0;
        boolean available = false;

        for (int i = 0; i <= currentSize; i++) {
            if (heap[i] == value) {
                position = i;
                heap[position] = heap[currentSize];
                available = true;
                break;
            }
        }

        if (available == false) {
            System.out.print(value + " not in heap");
            return false;
        }
        if (position == currentSize) {
            currentSize--;
            return true;
        }

        //decide if to heapify up or downards
        double headPosDouble = (double) (position - 1) / 2;
        int headPos = (int) Math.floor(headPosDouble);
        if (headPos < 0) {
            heapifyDown(position);
        } else if (heap[position] < heap[headPos]) {
            heapifyUp(position);
        } else {
            heapifyDown(position);
        }
        currentSize--;
        return true;
    }

    /**
     * Heapify smaller values up the heap
     *
     * @param pos position to start heapify from
     * @return true/false true if operation is successful
     */
    public boolean heapifyUp(int pos) {
        if (pos == 0) {
            return true;
        }
        boolean checker = false;

        while (checker == false) {
            double headPosDouble = (double) (pos - 1) / 2;
            int headPos = (int) Math.floor(headPosDouble);
            if (headPos < 0) {
                break;
            } else if (heap[pos] < heap[headPos]) {
                heap[pos] = heap[pos] + heap[headPos];
                heap[headPos] = heap[pos] - heap[headPos];
                heap[pos] = heap[pos] - heap[headPos];
                pos = headPos;

            } else {
                break;
            }
        }
        return true;
    }

    /**
     * Heapify big values downwards
     *
     * @param position position to start heapify from
     * @return true/false true if operation is successful
     */
    public boolean heapifyDown(int position) {
        int currentSize = this.currentSize - 1;
        if (position >= currentSize) {
            System.out.println("Heap not as large as this");
            return false;
        }
        boolean checker = false;
        int tail1Pos = 0;
        int tail2Pos = 0;
        while (checker == false) {
            //get position of two tails
            tail1Pos = (position * 2) + 1;
            tail2Pos = (position * 2) + 2;

            if (tail1Pos > currentSize) {
                checker = true;
            }

            if (tail1Pos <= currentSize && tail2Pos <= currentSize && heap[tail1Pos] < heap[position] && heap[tail2Pos] < heap[position]) {
                if (heap[tail1Pos] <= heap[tail2Pos]) {
                    swap(tail1Pos, position);
                    position = tail1Pos;
                } else {
                    swap(tail2Pos, position);
                    position = tail2Pos;
                }
                continue;
            }

            if (tail1Pos <= currentSize && heap[tail1Pos] < heap[position]) {
                swap(tail1Pos, position);
                position = tail1Pos;
            } else if (tail2Pos <= currentSize && heap[tail2Pos] < heap[position]) {
                swap(tail2Pos, position);
                position = tail2Pos;
            } else {
                break;
            }
        }

        return true;
    }

    /**
     * Get min value in heap
     *
     * @return val minimum value
     */
    public int getMin() {
        if (currentSize < 0) {
            System.out.println("Heap is empty");
            //throw an exception
            return 0;
        }
        return heap[0];
    }

    /**
     * Swap values in two different positions
     *
     * @param a position 1
     * @param b position 2
     */
    public void swap(int a, int b) {
        heap[a] = heap[a] + heap[b];
        heap[b] = heap[a] - heap[b];
        heap[a] = heap[a] - heap[b];
    }

    /**
     * Traverse the head array linearly
     */
    public void plainTraverse() {
        System.out.print("\nHeap Plain Traversal \n");
        for (int i = 0; i <= currentSize; i++) {
            System.out.print(heap[i] + ", ");
        }
        System.out.print("\n");
    }

    /**
     * Do a level order traversal of the heap;
     */
    public void levelOrderTraversal() {
        System.out.print("\nHeap Level Order Traversal \n");
        int level = 0;
        int counter = 0;
        for (int i = 0; i <= currentSize; i++) {
            if ((int) Math.pow(2, level) == counter) {//New Level
                level++;
                counter = 0;
                System.out.println(" ");
            }

            System.out.print(heap[i] + ", ");
            counter++;
        }
        System.out.print("\n");
    }

    /**
     * MinHeap Array main/test method...uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MinHeapArray minHA = new MinHeapArray(50);
        for (int i = 20; i > 3; i--) {
            minHA.insert(i);
            System.out.println(i + " Inserted, New Min: " + minHA.getMin());
        }

        minHA.plainTraverse();
        minHA.levelOrderTraversal();

        System.out.println("Delete 14");
        minHA.delete(14);
        minHA.levelOrderTraversal();

        System.out.println("Delete 4");
        minHA.delete(4);
        minHA.levelOrderTraversal();
    }*/
}