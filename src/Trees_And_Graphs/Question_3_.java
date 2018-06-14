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
 *
 * <b>List of Depths:</b> Given a binary tree, design an algorithm which creates
 * a linked list of all the nodes at each depth (e.g., if you have a tree with
 * depth D, you'll have D linked lists).
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_3_ {

    public void printTree(Bst tree, int level) {
        for (int i = 0; i < level; i++) {
            System.out.println(" ");
            levelOrderTraversal(tree.root, i);
        }
    }

    public void levelOrderTraversal(Bst.Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 0) {
            System.out.print(root.value + ", ");
            return;
        }

        levelOrderTraversal(root.left, level - 1);
        levelOrderTraversal(root.right, level - 1);
    }

    /**
     * trees and graphs question 3 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args){
         //LOAD BST
        Bst bst = new Bst(7);
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            bst.add(toAdd);
        }
        bst.add(9);
        bst.add(11);
        bst.add(1);
        Question_3_ q3 = new Question_3_();
        q3.printTree(bst, 4);
    }
     */
}
