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
package Trees_And_Graphs;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * </b> Max Heap </b> implementation
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class MaxHeap {

    HeapNode root;
    HeapNode found;
    int noOfElelemts;

    MaxHeap(int value) {
        root = new HeapNode(value);
        noOfElelemts = 1;
    }

    public boolean insert(int value) {
        if (root == null) {
            root.value = value;
            noOfElelemts = 1;
            return true;
        }

        int n = noOfElelemts + 1;
        double inBase2 = Math.log(n)/Math.log(2);
        int height = (int) Math.floor(inBase2);
        int x = height-1;
        ArrayList<HeapNode> preLastNodes = new ArrayList<>();
        getPreLastRow(root, (height-1), preLastNodes);
        Iterator<HeapNode> it = preLastNodes.iterator();
        HeapNode toAdd = new HeapNode(value);
        HeapNode toAddTo = null;
        while (it.hasNext()) {
            toAddTo = it.next();
            if (toAddTo.left == null && toAddTo.right == null) {
                toAddTo.left = toAdd;
                toAdd.ancestor = toAddTo;
                break;
            } else if (toAddTo.left != null && toAddTo.right == null) {
                toAddTo.right = toAdd;
                toAdd.ancestor = toAddTo;
                break;
            }
        }
        heapifyUp(toAdd);
        noOfElelemts++;
        return true;
    }

    public void heapifyUp(HeapNode root) {
        if (root == null) {
            System.out.println("Null node epast to heapify");
            return;
        }
        
        HeapNode current = root;
       // System.out.println("Heapifying up: "+root.value);
        while (current != null) {

            if (current.value > current.ancestor.value) {
               // System.out.println("Heapifying up: "+current.value+"...swaping it with "+current.ancestor.value);
                //swap
                current.value = current.value + current.ancestor.value;
                current.ancestor.value = current.value - current.ancestor.value;
                current.value = current.value - current.ancestor.value;
            } else {
               // System.out.println(""+current.value+" Not greater than "+current.ancestor.value);
                break;
            }

            current = current.ancestor;
            if (current.ancestor == null) {
                this.root = current;
                break;
            }
        }
    }

    public void heapifyDown(HeapNode root) {
        if (root == null) {
            return;
        }
        HeapNode current = root;
        while (current != null) {

            if (current.left != null && current.right != null) {

                if (current.value < current.left.value && current.value < current.right.value) {
                    if (current.left.value >= current.right.value) {
                        swapLeft(current);
                        current = current.left;
                    } else {
                        swapRight(current);
                        current = current.right;
                    }
                    continue;
                }
            }

            if (current.right != null && current.value < current.right.value) {
                swapRight(current);
                current = current.right;
            } else if (current.left != null && current.value < current.left.value) {
                swapLeft(current);
                current = current.left;
            } else {
                break;
            }
        }
    }

    public void swapLeft(HeapNode current) {
        current.value = current.value + current.left.value;
        current.left.value = current.value - current.left.value;
        current.value = current.value - current.left.value;

    }

    public void swapRight(HeapNode current) {
        current.value = current.value + current.right.value;
        current.right.value = current.value - current.right.value;
        current.value = current.value - current.right.value;
    }

    public void getPreLastRow(HeapNode root, int level, ArrayList<HeapNode> storage) {
        if (root == null) {
            return;
        }

        if (level == 0) {
            storage.add(root);
        } else {
            getPreLastRow(root.left, level - 1, storage);
            getPreLastRow(root.right, level - 1, storage);
        }

    }

    public boolean delete(HeapNode root, int value) {
        if(root==null){
            return false;
        }
        ArrayList<HeapNode> preLastNodes = new ArrayList<>();
        
        //get last element in heap
        int n = noOfElelemts + 1;
        double inBase2 = Math.log(n)/Math.log(2);
        int height = (int) Math.floor(inBase2);
        getPreLastRow(root, height - 1, preLastNodes);
        Iterator<HeapNode> it = preLastNodes.iterator();
        HeapNode check;
        HeapNode last=null;
        while (it.hasNext()) {
            check = it.next();
            if (check.left != null) {
                last = check.left;
            }
            if (check.right != null) {
                last = check.right;
            }
        }
        if(last==null){
            System.out.println("Error, last element is null");
            return false;
        }

        //find and replace
    found=null;
        getNode(root,value);
        if(found!=null){
            found.value= last.value;
            if(last.ancestor!=null && last.ancestor.left!=null && last.ancestor.left.value==last.value){
                last.ancestor.left=null; //remove last elemet
            }else if(last.ancestor!=null && last.ancestor.right!=null && last.ancestor.right.value==last.value){
                last.ancestor.right=null; //remove last element
            }
        }else{
            System.out.println("No Such node "+value+" in heap");
            return false;
        }
        
        //decide if to heapify down or up
        if(found.ancestor!=null && found.value>found.ancestor.value){
            heapifyUp(found);
        }else if(found.left!=null && found.value<found.left.value){
            heapifyDown(found);
        }else if(found.right!=null && found.value<found.right.value){
            heapifyDown(found);
        }
        noOfElelemts--;
        return true;
    }

    public int getMax() {
        return root.value;
    }

    public void getNode(HeapNode root,int toFind) {
        if (root == null) {
            return;
        }

        if (root.value == toFind) {
            System.out.println("Found...........");
            found = root;
        } else {
            getNode(root.left, toFind);
            getNode(root.right,toFind);
        }
    }

    public void levelOrderTraversal(HeapNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 0) {
            System.out.print(root.value + ", ");
        } else {
            levelOrderTraversal(root.left, level - 1);
            levelOrderTraversal(root.right, level - 1);
        }
    }

    /**
     * Max Heap main/test method.....uncomment to run
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
           //LOAD HEAP
        MaxHeap maxHeap = new MaxHeap(7);
        System.out.println("After adding Root: 7, Max= " + maxHeap.getMax());
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            maxHeap.insert(toAdd);
            System.out.println("After adding: " + toAdd + ", Max= " + maxHeap.getMax());
        }
        maxHeap.insert(9);
        System.out.println("After adding: 9, Max= " + maxHeap.getMax());
        maxHeap.insert(11);
        System.out.println("After adding: 11, Max= " + maxHeap.getMax());
        maxHeap.insert(1);
        System.out.println("After adding: 1, Max= " + maxHeap.getMax());

        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            maxHeap.levelOrderTraversal(maxHeap.root, i);
            System.out.println("\n");
        }

        System.out.println("Delete 1: ");
        maxHeap.delete(maxHeap.root, 1);
        System.out.println("Delete 4: ");
        maxHeap.delete(maxHeap.root, 4);
        System.out.println("Delete 7: ");
        maxHeap.delete(maxHeap.root, 7);
        System.out.println("Delete 6: ");
        maxHeap.delete(maxHeap.root, 6);

        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            maxHeap.levelOrderTraversal(maxHeap.root, i);
            System.out.println("\n");
        }

        System.out.println("Delete 12: ");
        maxHeap.delete(maxHeap.root, 12);
        System.out.println("Delete 10: ");
        maxHeap.delete(maxHeap.root, 10);
        System.out.println("Delete 4: ");
        maxHeap.delete(maxHeap.root, 4);
        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            maxHeap.levelOrderTraversal(maxHeap.root, i);
            System.out.println("\n");
        }

        System.out.println("Delete 0: ");
        maxHeap.delete(maxHeap.root, 0);
        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            maxHeap.levelOrderTraversal(maxHeap.root, i);
            System.out.println("\n");
        }

    }

    
}
