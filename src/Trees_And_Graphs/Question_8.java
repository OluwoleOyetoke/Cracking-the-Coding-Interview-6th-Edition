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

/**
 * <b>First Common Ancestor:</b> Design an algorithm and write code to find the
 * first common ancestor of two nodes in a binary tree. Avoid storing additional
 * nodes in a data structure. NOTE: This is not necessarily a binary search
 * tree.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_8 {

    /**
     * First check if the two values whose ancestors are to be found are present
     * in the tree
     *
     * @param tree tree
     * @param a node to find its common ancestor with b
     * @param b node to find its common ancestor with a
     * @throws TreeException tree exception
     */
    public void solve(Bst tree, Bst.Node a, Bst.Node b) throws TreeException {
        //check if tree contains a and b
        boolean check1 = tree.search(a.value);
        boolean check2 = tree.search(b.value);
        if (check1 == false || check2 == false) {
            System.out.println("Common ancestor not calculated. One of the numbers not in tree");
            return;
        }
        findCommonAncestor1(tree.root, a.value, b.value);
        Bst.Node node = findCommonAncestor2(tree.root, a.value, b.value);
        System.out.println("Method 2: Common ancestot for :" + a.value + " and " + b.value + " = " + node.value);
    }

    /**
     * Method one (Assuming its a BST and t1, t2 are present in tree)
     *
     * @param node root node
     * @param t1 content of node to find its common ancestor with t2
     * @param t2 content of node to find its common ancestor with t1
     * @return node first common ancestor
     */
    public Bst.Node findCommonAncestor1(Bst.Node node, int t1, int t2) {
        if (node == null) {
            return null;
        }

        if (t1 == t2) {
            if (node.value == t1) {
                if (node.ancestor != null) {
                    System.out.println("Same: Common ancestor for :" + t1 + " and " + t2 + " = " + node.ancestor.value);
                } else {
                    System.out.println("Same: Common ancestor for :" + t1 + " and " + t2 + " = null");
                }
                return node;
            }
        }
        if (node.value > t2 && node.value > t1) {
            // both targets are left
            return findCommonAncestor1(node.left, t1, t2);
        } else if (node.value < t2 && node.value < t1) {
            // both targets are right
            return findCommonAncestor1(node.right, t1, t2);
        } else {
            System.out.println("Method 1: Common ancestot for :" + t1 + " and " + t2 + " = " + node.value);
            return node;
        }
    }

    /**
     * Assuming its not a BST
     *
     * @param node root node
     * @param a content of node to find its common ancestor with b
     * @param b content of node to find its common ancestor with a
     * @return node first common ancestor..returns null if none
     */
    public Bst.Node findCommonAncestor2(Bst.Node node, int a, int b) {
        if (node == null) {
            return null;
        }

        Bst.Node left = null;
        Bst.Node right = null;
        if (node.value == a || node.value == b) {
            return node;
        }
        left = findCommonAncestor2(node.left, a, b);
        right = findCommonAncestor2(node.right, a, b);

        if (left != null && right != null) {
            System.out.println("Method 2: Common ancestot for :" + a + " and " + b + " = " + node.value);
            return node;
        }

        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }else{
          return null;  
        }

        
    }

    /**
     * Trees and graphs question_8 main/test method....uncomment to run
     *
     * @param args command line arguments
     * @throws TreeException Tree Exception
     *//*
    public static void main(String[] args) throws TreeException {
        //LOAD BST
        Bst bst = new Bst(7);
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            bst.add(toAdd);
        }
        bst.add(9);
        bst.add(11);
        bst.add(1);
        Bst.Node a = new Bst.Node(9);
        Bst.Node b = new Bst.Node(10);
        Question_8 q8 = new Question_8();
        q8.solve(bst, a, b);
    }
*/
}
