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

/**
 * <b>Check Subtree: </b> Tl and T2 are two very large binary trees, with Tl
 * much bigger than T2. Create an algorithm to determine if T2 is a subtree of
 * Tl. A tree T2 is a subtree of Tl if there exists a node n in Tl such that the
 * subtree of n is identical to T2. That is, if you cut off the tree at node n,
 * the two trees would be identical.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question10 {

    boolean found = false;

    /**
     * Find if t2 is a subtree of t1
     *
     * @param t1 main tree
     * @param t2 tree to find in t1
     */
    public void findSubtree(Bst t1, Bst t2) {
        if (t1 == null || t2 == null) {
            return;
        }
        //find the hash of t2 (hash is based on value and position [lef,right] in tree)
        int hash = hashTree(t2.root, 0);
        preFind(t1.root, t2.root.value, hash);
        if (found == false) {
            System.out.println("T2 is not a Subtree of T1");
        }
    }

    /**
     * Traverses through the main tree. Only runs the find/match function when
     * it encounters a node that is the same as the root node of the tree to
     * find.
     *
     * @param root root node
     * @param toFindRoot content of the root node of the subtree
     * @param hash hash value of the subtree
     */
    public void preFind(Bst.Node root, int toFindRoot, int hash) {
        if (root == null) {
            return;
        }
        preFind(root.left, toFindRoot, hash);
        preFind(root.right, toFindRoot, hash);

        if (root.value == toFindRoot) {
            int retrievedHash = hashTree(root, 0);
        if (retrievedHash == hash) {
            found = true;
             System.out.println("T2 is a Subtree of T1");
            return;
        }
        }
    }
    
    /**
     * Traverses through tree and computes its hash.
     * @param root node to start from
     * @param start 0 used to signal that node is root and its ancestors should not be considered 
     * @return hash hash code of tree
     */
    public int hashTree(Bst.Node root, int start) {
        int hash = 0;
        if (root == null) {
            return hash;
        }
        if (start == 0) {
            hash = 1;
        }
        int hashLeft = hashTree(root.left, start + 1);
        int hashRight = hashTree(root.right, start + 1);

        //on the left of ancestor
        if (start != 0 && (root.ancestor != null && root.ancestor.left != null && root.ancestor.left.value == root.value)) {
            hash = 3 * (hashLeft + hashRight + root.value);
        } else if (start != 0 && (root.ancestor != null && root.ancestor.right != null && root.ancestor.right.value == root.value)) { //on the right of ancestor
            hash = 5 * (hashLeft + hashRight + root.value);
        } else {//root
            hash = 7 * (hashLeft + hashRight + root.value);
        }

        return hash;
    }

    /**
     * Trees and graphs question_10 main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        //LOAD BST
        Bst bst = new Bst(7);
        bst.add(2);
        bst.add(1);
        bst.add(3);

        Bst bst2 = new Bst(2);
        bst2.add(1);
        bst2.add(3);
        Question_10 q10 = new Question_10();
        q10.findSubtree(bst, bst2);
    }*/
}
