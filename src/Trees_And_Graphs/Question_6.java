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
 * <b>Successor:</b>Write an algorithm to find the "next" node (i.e., in-order
 * successor) of a given node in a binary search tree. You may assume that each
 * node has a link to its parent.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_6 {

    /**
     * 1) If right subtree of node is not NULL, then successor lies in right
     * subtree. Go to right subtree and return the node with minimum value. 2)
     * If right subtree of node is NULL, then successor is one of the ancestors.
     * Travel up using the parent pointer until you see a node which is left
     * child of it’s parent. The parent of such a node is the successor.
     *
     * @param root node to start from
     * @param toFind content of node to find
     */
    public void inOrderTraversal(Bst.Node root, int toFind) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, toFind);
        if (root.value == toFind) {
            int sucessor = 0;
            boolean exists = false;

            if (root.right != null) {
                Bst.Node current = root.right;
                while (current != null) {
                    sucessor = current.value;
                    if (current.left != null) {
                        current = current.left;
                    } else if (current.left == null) {
                        break;
                    }
                }
                exists = true;
                System.out.println("Successor to " + toFind + " is " + sucessor);
            } else if (root.right == null) {
                Bst.Node current = root;
                while (current != null) {
                    if (current.ancestor != null) {
                        if (current.ancestor.left != null && current.ancestor.left.value == current.value) {
                            System.out.println("Successor to " + toFind + " is " + current.ancestor.value);
                            exists = true;
                            break;
                        }
                    }
                    current = current.ancestor;
                }
            }
            if (exists == false) {
                System.out.println(toFind + " has no in-order sucessor in this tree");
            }
            return;
        }
        inOrderTraversal(root.right, toFind);
    }

    /**
     * Trees and graphs question_6 main method....uncomment to run
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
        Question_6 q6 = new Question_6();
        q6.inOrderTraversal(bst.root, 2);
    }*/
}
