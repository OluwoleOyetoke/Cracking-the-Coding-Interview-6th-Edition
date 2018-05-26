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

import java.util.HashMap;
import java.util.Iterator;

/**
 * <b>Tries Data Structure Implementation</b>: AddWords, Print All Words, Find Suffixes
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Tries {

    Node root;

    /**
     * Default Constructor
     */
    Tries() {
        root = new Node('-');
    }

    /**
     * Node class
     */
    class Node {

        char content;
        HashMap<Character, Node> children;
        boolean isCompleteWord;

        Node(char content) {
            this.content = content;
            children = new HashMap<>();
            isCompleteWord = false;
        }
    }

    /**
     * Add words to the tree
     *
     * @param word word to add
     * @return true/false true if successful and false if otherwise
     */
    public boolean addWords(String word) {
        if (root == null) {
            root = new Node('-');
        }if(word==null || word.isEmpty()){
            System.out.println("Empty word sent");
            return false;
        }

        char[] wordArray = word.toCharArray();
        Node current = root;
        Node checked = root;
        for (int i = 0; i < wordArray.length; i++) {

            if (current.children != null && current.children.size()!=0) {
                checked = current.children.get(wordArray[i]);
                if (checked != null) {
                    current = checked;
                } else {
                    //add character here
                    Node newNode = new Node(wordArray[i]);
                    current.children.put(wordArray[i], newNode);
                    current = newNode;
                }
            } else {
                //add character here
                Node newNode = new Node(wordArray[i]);
                current.children.put(wordArray[i], newNode);
                current = newNode;
            }

            if (i == (wordArray.length - 1)) {
                current.isCompleteWord = true;
                if (current.children == null || current.children.size()==0) {
                    Node newNode = new Node('*');
                    current.children.put(wordArray[i], newNode);  //add word ending character here
                }
            }
        }
        return true;
    }

    /**
     * USed to get word suffix
     *
     * @param word word whose suffix is to be found
     */
    public void suffixOf(String word) {
        if(word==null || word.isEmpty()){
            System.out.println("Empty word sent");
            return;
        }
        char[] wordArray = word.toCharArray();

        Node current = root;
        Node check;
        boolean prefixExists = true;
        for (int i = 0; i < wordArray.length; i++) {
            if (current.children.containsKey(wordArray[i])) {
                current = current.children.get(wordArray[i]);
            } else {
                prefixExists = false;
                break;
            }
        }

        if (prefixExists == false) {
            System.out.println("Word with prefix " + word + " Does not exist in tree");
            return;
        }
        //get all possibel suffixes
        suffixes(current, "");
    }

    /**
     * Suffix concatenator
     *
     * @param current current node
     * @param prefix prefixes to concatenate as the suffix
     */
    public void suffixes(Node current, String prefix) {
        if (current == null || current.content == '*' || current.children == null) {
            return;
        }
        if (current.isCompleteWord == true) {
            System.out.println("Suffix: " + prefix);
        }

        Iterator<Node> it = null;
        it = current.children.values().iterator();
        Node check = null;
        while (it.hasNext()) {
            check = it.next();
            if (check.content != '*') {
                suffixes(check, prefix + "" + check.content);
            } else {
                suffixes(check, prefix);
            }
        }
    }

    public void printTries() {
        if (root == null || root.children == null) {
            System.out.println("Tree is empty");
        }
        System.out.println("Tree as is: ");
        suffixes(root, "");
    }

    /**
     * Tries main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Tries tries = new Tries();
        String[] wordArray = {"Hello", "How", "Are", "You", "Doing", "Today", "Andrew", "Andreel", "And", "Please", "Do", "Good", "Today"};
        for (int i = 0; i < wordArray.length; i++) {
            tries.addWords(wordArray[i].toLowerCase());
        }

        tries.printTries();
        System.out.println("\nGet Suffixes of An");
        tries.suffixOf("An".toLowerCase());
    }*/
}
