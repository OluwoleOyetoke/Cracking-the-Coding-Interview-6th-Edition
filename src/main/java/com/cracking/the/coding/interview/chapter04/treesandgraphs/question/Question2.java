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
package com.cracking.the.coding.interview.chapter04.treesandgraphs.question;

import com.cracking.the.coding.interview.chapter04.treesandgraphs.datastructure.Bst;
import com.cracking.the.coding.interview.chapter04.treesandgraphs.exception.TreeException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>Minimal Tree:</b> Given a sorted (increasing order) array with unique
 * integer elements, write an algorithm to create a binary search tree with
 * minimal height.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question2 {

    int[] list;
    Bst tree;

    /**
     * default constructor
     *
     * @param length length of sorted array to create
     */
    Question2(int length) {
        list = new int[length + 1];
        for (int i = 0; i <= length; i++) {
            list[i] = i;
        }
        tree = new Bst(0);
        try {
            tree.delete(tree.root, 0);
        } catch (TreeException ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * solve
     */
    public void solve() {
        makeBalancedTree(list);
    }
    
    /**
     * recursive function that makes the balanced tree
     * @param localList list to place in tree
     */
    public void makeBalancedTree(int[] localList) {
        if (localList == null || localList.length == 0) {
            return;
        }

        int length = localList.length;

        if (length == 1) {
            //do something
            tree.add(localList[0]);
            return;
        }

        int pivot = (int) Math.ceil(((double) (length / 2)));
        tree.add(localList[pivot]);

        //make new subarrays
        int[] left = new int[pivot];
        int[] right = new int[(length - pivot - 1)];
        for (int i = 0; i < length; i++) {
            if (i < pivot) {
                left[i] = localList[i];
            } else if (i > pivot) {
                right[(i - pivot - 1)] = localList[i];
            }
        }
        makeBalancedTree(left);
        makeBalancedTree(right);
    }

    /**
     *Main method for trees and graph question 2...uncomment to run
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_2 q2 = new Question_2(20);
        q2.solve();
        q2.tree.printTree(4);
        System.out.println("IN-ORDER TRAVERSAL");
        q2.tree.inOrderTraversal(q2.tree.root);
    }*/
}
