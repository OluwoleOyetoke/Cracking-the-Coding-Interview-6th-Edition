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

import java.util.LinkedList;

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
    
    LinkedList<LinkedList> list = new LinkedList<>();
    
    /**
     * Uses level order traversal 0(DlogN)...D = number of levels... N = number of content in tree
     * @param tree bst
     * @param depth number of levels in tree
     */
    public void solve1(Bst tree, int depth) {
        //get number of levels
        list = new LinkedList<>();
        for (int i = 0; i < depth; i++) {
            //System.out.println(" ");
            list.add(new LinkedList());
            levelOrderTraversal(tree.root, i, i);
        }
    }
    
    /**
     * Uses pre_order traversal... 0(logN)
     * @param tree bst
     *  @param depth number of levels in tree
     */
    public void solve2(Bst tree, int depth){
        list = new LinkedList<>();
        for(int i=0; i<depth; i++){
            list.add(new LinkedList());
        }
        preOrderTraversal(tree.root, 0);
    }

    public void levelOrderTraversal(Bst.Node root, int level, int levelToSave) {
        if (root == null) {
            return;
        }
        if (level == 0) {
            list.get(levelToSave).add(root.value);
            //System.out.print(root.value + ", ");
            return;
        }
        levelOrderTraversal(root.left, level - 1, levelToSave);
        levelOrderTraversal(root.right, level - 1,levelToSave);
    }
    
      /**
     * Pre order traversal of BST
     * @param root root to start traversal from
     * @param level level
     */
    public void preOrderTraversal(Bst.Node root, int  level) {
        if (root == null) {
            return;
        }
        list.get(level).add(root.value);
        preOrderTraversal(root.left, level+1);
        preOrderTraversal(root.right, level+1);

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
        //q3.solve1(bst,5);
        q3.solve2(bst, 5);
        for(int i=0; i<q3.list.size(); i++){
            System.out.println("\nLevel: "+i);
            if(q3.list.get(i)!=null || q3.list.get(i).size()!=0){
                    System.out.print(q3.list.get(i).toString());
            }
        }
        System.out.println("\n");
    }  */
}
