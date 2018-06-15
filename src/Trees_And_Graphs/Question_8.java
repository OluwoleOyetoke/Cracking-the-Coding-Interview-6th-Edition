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
     * In order traversal of BSt to find common ancestor
     * @param root root to start traversal from
     * @param a node to find its common ancestor with b
     * @param b node to find its common ancestor with a
     */
    public void findCommonAncestor(Bst.Node root, Bst.Node a, Bst.Node b) {
        if (root == null || a==null || b==null ) {
            return;
        }
        if(a==b){
             System.out.println("Common ancestot for :"+a.value+" and "+b.value+" = "+root.value);
             return;
        }
        int bigger = (a.value>b.value)?a.value:b.value;
         int smaller = (a.value<=b.value)?a.value:b.value;
        
        findCommonAncestor(root.left,a,b);
        //assuming no duplicate values
        if(root.left!=null && smaller<=root.left.value && root.right!=null && bigger>root.right.value){
         System.out.println("Common ancestot for :"+a.value+" and "+b.value+" = "+root.value);   
        }
        
        findCommonAncestor(root.right,a,b);

    }
        
    /**
     * Bst main/test method....uncomment to run
     * @param args command line arguments
     * @throws TreeException Tree Exception
     */
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
        Bst.Node a = new Bst.Node(11);
        Bst.Node b = new Bst.Node(1);
        Question_8 q8 = new Question_8();
        q8.findCommonAncestor(bst.root, a, b);
    }
}
