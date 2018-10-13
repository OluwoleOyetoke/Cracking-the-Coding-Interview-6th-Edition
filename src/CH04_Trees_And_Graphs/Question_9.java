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
package CH04_Trees_And_Graphs;

import java.util.ArrayList;

/**
 * <b>BST Sequences:</b> A binary search tree was created by traversing through
 * an array from left to right and inserting each element. Given a binary search
 * tree with distinct elements, print all possible arrays that could have led to
 * this tree.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_9 {
    
    /**
     * Tracks down from root and creates lists of possibel sequeces through which the bst could have been formed
     * @param root root node
     * @return listList array list of array lists
     */
    public ArrayList<ArrayList> bstSequence(Bst.Node root) {
        if (root == null) {
            return null;
        }

        ArrayList<ArrayList> tempLeft = bstSequence(root.left);
        ArrayList<ArrayList> tempRight = bstSequence(root.right);
        if (tempLeft == null) {
            tempLeft = new ArrayList<>();
        }
        if (tempRight == null) {
            tempRight = new ArrayList<>();
        }

        //Handling node with no left or right (leafs)
        if (root.left == null && root.right == null) {
            ArrayList thisSeq = new ArrayList();
            thisSeq.add(root.value);
            if (root.ancestor != null && root.ancestor.left != null
                    && root.ancestor.left.value == root.value) {
                tempLeft.add(thisSeq);
                return tempLeft;
            } else if (root.ancestor != null && root.ancestor.right != null
                    && root.ancestor.right.value == root.value) {
                tempRight.add(thisSeq);
                return tempRight;
            } else {
                ArrayList y = new ArrayList();
                y.add(root.value);
                ArrayList<ArrayList> z = new ArrayList();
                z.add(y);
                return z;
            }
        } //Hadling non leafs
        else if (root.left == null && root.right != null) {//node has right child
            for (int i = 0; i < tempRight.size(); i++) {
                tempRight.get(i).add(0, root.value);
            }
            return tempRight;
        } else if (root.left != null && root.right == null) {//node has left child
            for (int i = 0; i < tempLeft.size(); i++) {
                tempLeft.get(i).add(0, root.value);
            }
            return tempLeft;
        } else {//node has left and right child
            ArrayList<ArrayList> newAll = new ArrayList();
            ArrayList<ArrayList> newTemp1 = new ArrayList();
            ArrayList<ArrayList> newTemp2 = new ArrayList();
            newTemp1 = deepCopyListList(tempLeft);
            newTemp2 = deepCopyListList(tempRight);
            for (int i = 0; i < newTemp1.size(); i++) {
                newTemp1.get(i).add(0, root.value);
            }
            for (int i = 0; i < newTemp2.size(); i++) {
                newTemp2.get(i).add(0, root.value);
            }
            for (int i = 0; i < newTemp1.size(); i++) {
                for (int j = 0; j < tempRight.size(); j++) {
                    ArrayList<ArrayList> x = deepCopyListList(newTemp1);
                    x.get(i).addAll(tempRight.get(j));
                    newAll.add(x.get(i));
                }
            }
            for (int i = 0; i < newTemp2.size(); i++) {
                for (int j = 0; j < tempLeft.size(); j++) {
                    ArrayList<ArrayList> x = deepCopyListList(newTemp2);
                    x.get(i).addAll(tempLeft.get(j));
                    newAll.add(x.get(i));
                }
            }
            return newAll;
        }
    }
    
    /**
     * Deep copy array list
     * @param input array list to copy
     * @return arrayList return deep copied array list
     */
    public ArrayList deepCopyList(ArrayList input) {
        ArrayList list = new ArrayList();

        for (int i = 0; i < input.size(); i++) {
            list.add(input.get(i));
        }
        return list;
    }
    
    /**
     * Deep copy an array list of array list
     * @param input aray list of list to copy
     * @return arrayList return deep copied list
     */
    public ArrayList<ArrayList> deepCopyListList(ArrayList<ArrayList> input) {
        ArrayList<ArrayList> list = new ArrayList();

        for (int i = 0; i < input.size(); i++) {
            ArrayList tempList = new ArrayList();
            for (int j = 0; j < input.get(i).size(); j++) {
                tempList.add(input.get(i).get(j));
            }
                list.add(tempList);
        }
        return list;
    }

    /**
     * Trees and graphs question_9 main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args){
        //LOAD BST
        Bst bst = new Bst(7);
        bst.add(3);
        bst.add(9);
        bst.add(8);
        bst.add(10);

        Question_9 q9 = new Question_9();
        ArrayList<ArrayList> list = q9.bstSequence(bst.root);
        System.out.println("SEQUENCES: \n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
*/
}
