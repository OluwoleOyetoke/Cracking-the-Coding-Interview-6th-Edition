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
 * <b>Check Balanced:</b> Implement a function to check if a binary tree is
 * balanced. For the purposes of this question, a balanced tree is defined to be
 * a tree such that the heights of the two subtrees of any node never differ by
 * more than one.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_4 {

    boolean check = false;

    /**
     * Checks is bst is balanced or not
     *
     * @param root node to start check from
     * @return height height of node
     */
    public int checkBalance(Bst.Node root) {
        int left = 0;
        int right = 0;
        int heightDifference = 0;
        int height;

        if (root.left != null) {
            left = checkBalance(root.left) + 1;
        }

        if (root.right != null) {
            right = checkBalance(root.right) + 1;
        }
        height = chooseMax(left, right);
        heightDifference = Math.abs(left - right);

        if (heightDifference > 1) {
            check = true;
            System.out.println("Imbalanced Encountered at: "+root.value+"...Difference in height = "+heightDifference);
        }
        return height;
    }
    
    public int chooseMax(int a, int b){
        int max = (a>b)?a:b;
        return max;
    }

    /**
     * trees and graphs question 3 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        //LOAD BST - Unbalanced
        Bst bst = new Bst(7);
        bst.add(6);
        bst.add(8);
        bst.add(5);
        bst.add(4);
        bst.add(9);
        Question_4 q4 = new Question_4();
        int height = q4.checkBalance(bst.root);
        if (q4.check = true) {
            System.out.println("...one of the nodes of the tree is unbalanced");
        } else {
            System.out.println("\nTree is balanced");
        }

        //LOAD BST - Balanced
        Bst bst2 = new Bst(7);
        bst2.add(6);
        bst2.add(8);
        bst2.add(5);
        bst2.add(9);
        q4.check = false;
        height = q4.checkBalance(bst2.root);
        if (q4.check == true) {
            System.out.println("...one of the nodes of the tree is unbalanced");
        } else {
            System.out.println("\nTree is balanced");
        }
    }*/

}
