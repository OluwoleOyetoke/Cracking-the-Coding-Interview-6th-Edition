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

import java.util.HashMap;
import java.util.Iterator;

/**
 * <b>Tries Data Structure Implementation</b>: AddWords, Print All Words, Find
 * Suffixes and Delete
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Tries {

    public Node root;

    /**
     * Constructor
     */
    Tries() {
        root = new Node('-');
        root.ancestor = null;
    }

    /**
     * Add word to tries
     *
     * @param word word to add
     * @return true/false true if successful and false if otherwise
     */
    public boolean add(String word) {
        if (root == null) {
            root.content = '-';
            root.ancestor = null;
            root.children = new HashMap<>();
        }
        if (word == null || word.isEmpty()) {
            System.out.println("Empty word sent");
            return false;
        }
        System.out.println("Add Word: " + word);
        //Convert word to char array
        char[] wordArray = word.toCharArray();

        Node current = root;
        HashMap<Character, Node> currentChildren = null;
        boolean check;

        for (int i = 0; i < wordArray.length; i++) {
            currentChildren = current.children; //get current children
            check = currentChildren.containsKey(wordArray[i]);	//Check if current children contains current char of wordArray
            //try to use getIfContains
            if (check) {//present
                current = currentChildren.get(wordArray[i]);
            } else {//not present, so add and make current
                Node toAdd = new Node(wordArray[i]);
                currentChildren.put(wordArray[i], toAdd);
                toAdd.ancestor = current;
                current = toAdd;
            }
        }
        current.isCompleteWord = true;
        if (current.children.size() == 0) {
            //add word ending character here
            Node endSign = new Node('*');
            current.children.put('*', endSign);
            endSign.ancestor = current;
            current = endSign;
        }
        return true;
    }
    
    /**
     * Delete word from the tree (if exist)
     * @param word word to delete
     * @return true/false true if successfull and false if otherwise 
     */
    public boolean delete(String word) {
        //find word in tree
        if (root == null || root.children.size() == 0 || word == null || word.isEmpty()) {
            System.out.println("Tries is empty or empty word send for deletion");
            return true;
        }

        System.out.println("Delete Word: " + word);

        char[] prefixArray = word.toCharArray();

        Node current = root;
        HashMap<Character, Node> currentChildren = null;
        boolean check = false;

        //track down the tree to where prefix ends
        for (int i = 0; i < prefixArray.length; i++) {
            currentChildren = current.children;

            //check if current children contains prefixArray[i]
            check = currentChildren.containsKey(prefixArray[i]);
            if (check) {
                current = currentChildren.get(prefixArray[i]);
            } else {
                System.out.println("Tries doesnt conatain word: " + word);
                return false;
            }
        }//end of for loop

        //last node of word has children, then simply make isComplete word false
        if (current.children.containsKey('*') != true && current.children.size() >= 1) {
            current.isCompleteWord = false;
        } else if (current.children.containsKey('*') == true && current.children.size() == 1) {
            //if last node has no children...delete upwards and stop when u find one of its ancestors that has more children
            Node next;
            while (current.content != '-') {
                next = current.ancestor;
                if (current.children == null || current.children.size() == 0 || (current.children.size() == 1 && current.children.containsKey('*'))) {
                    current.ancestor.children.remove(current);
                    current.children = null;
                    current = null;
                } else {
                    break;
                }
                current = next;
            }
        }
        System.out.println("Deletion Successful");
        return true;
    }

    /**
     * Get the suffixes of a word
     *
     * @param prefix word to get its suffixes
     */
    public void getSuffix(String prefix) {
        if (root == null || root.children.size() == 0) {
            System.out.println("Tries is empty");
            return;
        }

        char[] prefixArray = prefix.toCharArray();

        Node current = root;
        HashMap<Character, Node> currentChildren = null;
        boolean check = false;

        //track down the tree to where prefix ends
        for (int i = 0; i < prefixArray.length; i++) {
            currentChildren = current.children;

            //check if current children contains prefixArray[i]
            check = currentChildren.containsKey(prefixArray[i]);
            if (check) {
                current = currentChildren.get(prefixArray[i]);
                check = true;
            } else {
                System.out.println("Tries doesnt conatain prefix: " + prefix);
                return;
            }
        }//end of for loop

        //from there print out other possibilities
        //recursively go through all the children of current
        if (check == true) {
            suffixesFrom(current, "");
        } else {
            System.out.println("Tries doesnt conatain prefix: " + prefix);
            return;
        }
    }

    /**
     * Get suffixes
     *
     * @param current Node to start suffix search from
     * @param pre prefix to the current node
     */
    public void suffixesFrom(Node current, String pre) {
        if (current == null || current.content == '*' || current.children == null) {
            return;
        }

        if (current.isCompleteWord == true) {
            System.out.println(pre);
        }

        //Get current children into an iterator
        HashMap<Character, Node> currentChildren = current.children;

        Iterator<Node> it = currentChildren.values().iterator();
        Node currentNode = null;

        while (it.hasNext()) {
            currentNode = it.next();
            suffixesFrom(currentNode, pre + "" + currentNode.content);
        }
    }
    
    /**
     * Print all words in the tree
     */
    public void printTries() {
        if (root == null || root.children == null) {
            System.out.println("Tree is empty");
        }
        System.out.println("\n\nTREE AS IS:");
        suffixesFrom(root, "");
    }

    class Node {

        char content;
        Node ancestor;
        HashMap<Character, Node> children;
        boolean isCompleteWord;

        Node(char content) {
            this.content = content;
            children = new HashMap<>();
            isCompleteWord = false;
            ancestor = null;
        }

        public boolean isIsCompleteWord() {
            return isCompleteWord;
        }

        public void setIsCompleteWord(boolean isCompleteWord) {
            this.isCompleteWord = isCompleteWord;
        }

        public Node getAncestor() {
            return ancestor;
        }

        public void setAncestor(Node ancestor) {
            this.ancestor = ancestor;
        }

        public char getContent() {
            return content;
        }

        public void setContent(char content) {
            this.content = content;
        }

        public HashMap<Character, Node> getChildren() {
            return children;
        }

        public void setChildren(HashMap<Character, Node> children) {
            this.children = children;
        }
    }

    /**
     * Tries main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Tries2 tries = new Tries2();
        String[] wordArray = {"Hello", "How", "Are", "You", "Doing", "Today", "Andrew", "Andreel", "And", "Please", "Do", "Good", "Today"};
        for (int i = 0; i < wordArray.length; i++) {
            tries.add(wordArray[i].toLowerCase());
        }

        tries.printTries();
        System.out.println("\nGET SUFFIXES OF 'An'");
        tries.getSuffix("An".toLowerCase());
        
        System.out.println("\nDELETION");
        tries.delete("andrew");
        System.out.println("\nGET SUFFIXES OF 'An'");
         tries.getSuffix("An".toLowerCase());

        System.out.println("\nDELETION");
        tries.delete("and");
        tries.printTries();

        System.out.println("\nDELETION");
        tries.delete("please");
        tries.printTries();
    }*/
}
