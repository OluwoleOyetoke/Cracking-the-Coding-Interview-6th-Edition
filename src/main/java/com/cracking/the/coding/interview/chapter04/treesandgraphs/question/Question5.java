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

import java.util.ArrayList;

/**
 * <b>Validate BST:</b> Implement a function to check if a binary tree is a
 * binary search tree.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question5 {

    ArrayList<Integer> list = new ArrayList<>();

    /**
     * In order traversal should print out bst in ascending order
     *
     * @param bst Node to start from
     */
    public void solve(Bst bst) {
        if (bst == null) {
            return;
        }
        list = new ArrayList();
        boolean check = false;
        inOrderTraversal(bst.root);
        int previous = list.get(0);
        int current = previous;
        for (int i = 1; i < list.size(); i++) {
            current = list.get(i);
            if (current < previous) {
                System.out.println("Not a BST");
                check = true;
                break;
            }
            previous = current;
        }
        if (check == false) {
            System.out.println("A BST");
        }

    }

    /**
     * In order traversal
     *
     * @param root Node to start from
     */
    public void inOrderTraversal(Bst.Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        list.add(root.value);
        inOrderTraversal(root.right);

    }

    /**
     * Trees and graphs question_5 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        //LOAD BST
        Bst bst = new Bst(7);
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            bst.add(toAdd);
        }
        bst.add(9);
        bst.add(11);
        bst.add(1);
        Question_5 q5 = new Question_5();
        q5.solve(bst);

        //alter the bst
        bst.root.left.value = bst.root.right.value;
        q5.solve(bst);
    }*/
}
