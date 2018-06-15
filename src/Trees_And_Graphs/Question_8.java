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
     * First check if the two values whose ancestors are to be found are present in the tree
     * @param tree tree
     * @param a node to find its common ancestor with b
     * @param b node to find its common ancestor with a
     * @throws TreeException  tree exception
     */
    public void solve(Bst tree, Bst.Node a, Bst.Node b) throws TreeException {
        //check if tree contains a and b
        boolean check1 = tree.search(a.value);
        boolean check2 = tree.search(b.value);
        if (check1 == false || check2 == false) {
            System.out.println("Common ancestor not calculated. One of the numbers not in tree");
            return;
        }
        findCommonAncestor(tree.root, a, b);
    }

    /**
     * In order traversal of BSt to find common ancestor
     *
     * @param root root to start traversal from
     * @param a node to find its common ancestor with b
     * @param b node to find its common ancestor with a
     */
    public void findCommonAncestor(Bst.Node root, Bst.Node a, Bst.Node b) {
        if (root == null || a == null || b == null) {
            return;
        }
        if (a.value == b.value) {
            if(root.value == a.value){
                System.out.println("Same: Common ancestor for :" + a.value + " and " + b.value + " = " + root.ancestor.value);
               return;
            } 
        }

        //assuming no duplicate values
        if (a.value <= root.value && b.value > root.value) {
            System.out.println("Common ancestot for :" + a.value + " and " + b.value + " = " + root.value);
            return;
        } else if (b.value < root.value && a.value >= root.value) {
            System.out.println("Common ancestot for :" + a.value + " and " + b.value + " = " + root.value);
            return;
        }
        findCommonAncestor(root.left, a, b);
        findCommonAncestor(root.right, a, b);
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
        Bst.Node a = new Bst.Node(1);
        Bst.Node b = new Bst.Node(14);
        Question_8 q8 = new Question_8();
        q8.solve(bst, a, b);
    }
*/
}
