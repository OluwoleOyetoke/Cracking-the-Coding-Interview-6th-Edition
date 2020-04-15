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

import com.cracking.the.coding.interview.chapter04.treesandgraphs.exception.TreeException;

/**
 * <b>My Bst implementation 2</b>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 */
public class Bst {

    public Node root;

    /**
     * Default constructor
     * @param value value to be added as root
     */
    public Bst(int value) {
        this.root = new Node(value);
    }
    
    
    /**
     * Used to add nodes into the BST
     * @param value value to be added to BST
     * @return true/false true if value was successfully added
     */
    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node current = root;
        Node toAdd = new Node(value);
        int dec;
        while (current != null) {

            //make a decision to add to left or to traverse to next left node 
            dec = decision(current, toAdd);
            if(dec==0){
                System.out.println("Duplicates not allowed");
                return false;
            }
            else if (dec < 0) { //leftwards  
                if (current.left == null) { //left is empty
                    current.left = toAdd;
                    toAdd.ancestor = current;
                    return true;
                } else { //left is full
                    current = current.left;
                    continue;
                }
            } else if (dec > 0) { //rightwards
                if (current.right == null) { //left is empty
                    current.right = toAdd;
                    toAdd.ancestor = current;
                    return true;
                } else { //right is full
                    current = current.right;
                    continue;
                }
            }
        }
        return false;
    }
    
    /**
     * Delete node from BST
     * @param root node to begin search from
     * @param value value to add the the BST
     * @return valueAdded value added (if successfully added)
     * @throws TreeException Tree exception
     */
    public int delete(Node root, int value) throws TreeException {
        if (this.root == null) {
            throw new TreeException("Bst is empty");
        }else if(this.root.value == value){
            this.root = null;
            System.out.println("Bst emptied");
            return value;
        }

        Node toDelete = new Node(value);
        Node current = root;
        int dec = 0;
        int myPos;
        while (current != null) {
            dec = decision(current, toDelete);

            if (dec == 0) {//Node found
                  myPos = myPositionWithAncestor(current); //check if it is present on the left or right of its ancestor
                 
                  
                //Node is a leaf
                if (isLeaf(current)) {     
                    if(myPos<0){//on the left of ancestor
                        current.ancestor.left = null;
                    }else{//o the right of ancestor
                        current.ancestor.right =null;
                    }  
                    return value;
                }
                

                //Node has subtrees on its left
                if(current.right==null && current.left!=null){
                    if(myPos<0){//on the left of ancestor
                        current.ancestor.left = current.left;
                        current.left.ancestor = current.ancestor.left;
                    }else{//on the right of ancestor
                         current.ancestor.right = current.left;
                        current.left.ancestor = current.ancestor.right;
                    } 
                    return value;
                }
   
                
                
                //Node has subtress on its right
                 if(current.left==null && current.right!=null){
                    if(myPos<0){//on the left of ancestor
                        current.ancestor.left = current.right;
                        current.right.ancestor = current.ancestor.left;
                    }else{//on the right of ancestor
                         current.ancestor.right = current.right;
                        current.right.ancestor = current.ancestor.right;
                    } 
                    return value;
                }
                
                
                 //Node has subtrees on both left and right
                 if(current.left!=null && current.right!=null){
                   //doesnt matter if node is on the right or left of ancestor
                   int minimum = findMinimum(current.right, current.right.value); //find minimum in right subtree
                   current.value = minimum; //replace current with minimum
                   delete(current.right, minimum);
                   return value;
                 }
                       
            } 
      
            else if (dec < 0) {//Node is somewhere on the left
                if (current.left == null) { //node does not exist
                    throw new TreeException("Node does not exist in tree");
                } else {
                    current = current.left;
                }
            } else if (dec > 0) {//Node is somewhere on the right
                if (current.right == null) { //node does not exist
                    throw new TreeException("Node does not exist in tree");
                } else {
                    current = current.right;
                }
            }

        }
        
        //Would never get here. if for any reason, here is reached
        throw new TreeException("Node to delete does not exist");
    }
    
    /**
     * Search through BST for a particular value
     * @param value value to search for
     * @return true/false true if found and false if otherwise
     * @throws TreeException Tree Exception
     */
    public boolean search(int value) throws TreeException {
        if (root == null) {
            throw new TreeException("Tree is empty");
        }

        Node current = root;
        Node toSearchFor = new Node(value);
        int dec = 0;
        while (current != null) {

            dec = decision(current, toSearchFor);

            if (dec == 0) {//found
                System.out.println("Node with value " + value + " Found");
                return true;
            } else if (dec < 0) {//is somewhere on the left
                if (current.left == null) {
                    System.out.println(value + " not available in tree");
                    return false;
                } else {
                    current = current.left;
                }
            } else {//is somewhere on the right
                if (current.right == null) {
                    System.out.println(value + " not available in tree");
                    return false;
                } else {
                    current = current.right;
                }
            }
        }

        return false; //will never get here
    }
    
    /**
     * In order traversal of BST
     * @param root root to start traversal from
     */
    public void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.value + " ,");
        inOrderTraversal(root.right);

    }
  /**
     * Pre order traversal of BST
     * @param root root to start traversal from
     */
    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ,");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }
  
    /**
     * Post order traversal of the BST
     * @param root root to start traversal from
     */
    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ,");
    }
    
    /**
     * Make decision on moving leftwards or rightwards during BST node insertion
     * @param current current Node
     * @param incoming node to be added
     * @return diff
     */
    private int decision(Node current, Node incoming) {
        if (current == null) {
            System.out.println("Current Node is null");
            return -2;
        }
        if (current.compareTo(incoming) < 0) {
            return 1;
        } else if (current.compareTo(incoming) == 0) {
            return 0;
        } else {
            return -1;
        }
    }
    
    /**
     * Checks if node is a leaf node
     * @param node to to check
     * @return true/false true if node is a leaf
     * @throws TreeException Tree Exception
     */
    public boolean isLeaf(Node node) throws TreeException {
        if (node == null) {
            throw new TreeException("Null node passed to isLeaf");
        }
        if (node.left == null && node.right == null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks if current node is in ancestor's left or right
     * @param node node to check position
     * @return val -1 if at the left of ancestor and +1 if at the right of ancestor
     */
    public int myPositionWithAncestor(Node node){
           //check if it is present on the left or right of its ancestor
                    if(node.ancestor.left!=null && node.ancestor.left.value== node.value){//on the left of ancestor
                        return -1; 
                    }else if(node.ancestor.right!=null && node.ancestor.right.value== node.value){//no the right of ancestor
                        return 1; 
                    } 
                    else{
                        return -2;
                    }
    }
    
    /**
     * Find minimum in BST
     * @param root root to start minimum search from
     * @param minimum initial minimum
     * @return minVal min value found
     * @throws TreeException Tree Exception
     */
    public int findMinimum(Node root, int minimum) throws TreeException{
         if (root == null) {
            return minimum;
        }
         
        findMinimum(root.left, minimum);
        findMinimum(root.right, minimum);
   
            if(root.value<=minimum){
                minimum=root.value;
            }
            return minimum;
    }
    
        /**
     * Print out heap using level order traversal
     * @param root node to start search from
     * @param level level to print
     */
    public void levelOrderTraversal(Node root, int level) {
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
    
       public void printTree(int level){
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i <= level; i++) {
           levelOrderTraversal(root, i);
            System.out.println("\n");
        }
    }

    /**
     * Node class
     */
    public static class Node implements Comparable {

        public int value;
        public int height;
        public Node left;
        public Node right;
        public Node ancestor;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
            ancestor = null;
            height = 0;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getAncestor() {
            return ancestor;
        }

        public void setAncestor(Node ancestor) {
            this.ancestor = ancestor;
        }

        //check
        @Override
        public int compareTo(Object obj) {
            if (obj == null) {
                return 1;
            } else if (getClass() != obj.getClass()) {
                return 1;
            }
            Node toCompare = (Node) obj;
            if (this.value == toCompare.value) {
                return 0;
            } else {
                return this.value - toCompare.value;
            }
        }

    }
    
    /**
     * Bst main/test method....uncomment to run
     * @param args command line arguments
     * @throws TreeException Tree Exception
     *//*
    public static void main(String[] args) throws TreeException {
        //LOAD AND LOAD BST
        Bst bst = new Bst(7);
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            bst.add(toAdd);
        }
        bst.add(9);
        bst.add(11);
        bst.add(1);
        
        
        //TRAVERSE BST
        System.out.println("\nPRE ORDER TRAVERSAL: ");
        bst.preOrderTraversal(bst.root);
        System.out.println("\nIN ORDER TRAVERSAL: ");
        bst.inOrderTraversal(bst.root);  
        System.out.println("\nPOST ORDER TRAVERSAL: ");
        bst.postOrderTraversal(bst.root);
        
        //SEARCH, DELETE AND SEARCH
        System.out.println("\nSEARCH FOR 10");
        bst.search(10);
        System.out.println("Deleted: " + bst.delete(bst.root, 10));
        System.out.println("SEARCH FOR 10 AFTER DELETION");
        bst.search(10);     
    }*/
}
