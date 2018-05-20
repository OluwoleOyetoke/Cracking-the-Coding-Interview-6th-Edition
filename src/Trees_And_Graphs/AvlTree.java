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
 * <b>Avl tree implementation</b>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class AvlTree {

    Node root;
    
    /**
     * Default constructor
     * @param value value to be added in root node
     */
    AvlTree(int value) {
        root = new Node(value);
        System.out.println("Root: " + value);
        System.out.println("Balance factor: "+root.balanceFactor+"\n");
    }
    
    /**
     * Used to add nodes into the AVL
     * @param value value to be added to AVL
     * @return true/false true if value was successfully added
     */
    public boolean add(int value) {
        Node toAdd = new Node(value);
        if (root == null) {
            root = toAdd;
            return true;
        }

        Node current = root;
        int dec = 0;
        while (current != null) {

            dec = decision(current.value, value);
            if (dec < 0) {//move rightwards
                if (current.right == null) {
                    System.out.println("Add " + value + " to right of " + current.value);
                    current.right = toAdd;
                    toAdd.ancestor = current;
                    break;
                } else {
                    current = current.right;
                    continue;
                }
            }
            else {//move leftwards
                if (current.left == null) {
                    System.out.println("Add " + value + " to left of " + current.value);
                    current.left = toAdd;
                    toAdd.ancestor = current;
                    break;
                } else {
                    current = current.left;
                    continue;
                }
            }

        }
                    //update balance factor
            updateBalanceFactorAfterInsertion(toAdd);

            //Check and balance (if need be)
            checkAndBalance(toAdd);
        return true;
    }

    public int decision(int current, int toAdd) {
        return current - toAdd;
    }
    
    /**
     * Rotate AVL node leftwards
     * @param pivot pivot node
     * @return ture/false true if done and false if otherwise
     */
    public boolean leftRotate(Node pivot) {
        System.out.println("LeftRotating: "+pivot.value);
        Node temp = pivot.right.left;

        pivot.right.ancestor = pivot.ancestor;
        if (pivot.ancestor != null) {
            if (pivot.ancestor.right == pivot) {
                pivot.ancestor.right = pivot.right;
            } else {
                pivot.ancestor.left = pivot.right;
            }
        }

        pivot.right.left = pivot;
        pivot.ancestor = pivot.right;
        
        pivot.right = temp;
        if(temp!=null){
            temp.ancestor = pivot;
        }else{
            pivot.noOfRightSubTrees=0;
        }
        if(pivot.ancestor.ancestor==null){
            root = pivot.ancestor;
        }
        
        return true;
    }
    
     /**
     * Rotate AVL node rightwards
     * @param pivot pivot node
     * @return ture/false true if done and false if otherwise
     */
    public boolean rightRotate(Node pivot) {
        System.out.println("Right Rotating: "+pivot.value);
        Node temp = pivot.left.right;

        //root check
        pivot.left.ancestor = pivot.ancestor;
        if (pivot.ancestor != null) {
            if (pivot.ancestor.right == pivot) {
                pivot.ancestor.right = pivot.left;
            } else {
                pivot.ancestor.left = pivot.left;
            }
        }

        pivot.left.right = pivot;
        pivot.ancestor = pivot.left;

        pivot.left = temp;
        if(temp!=null){
        temp.ancestor = pivot;
        }else{
            pivot.noOfLeftSubTrees=0;
        }
           if(pivot.ancestor.ancestor==null){
            root = pivot.ancestor;
        }
        return true;
    }
    
    /**
     * Check if tree is balanced. Rotate if need be
     * @param startFrom node to start balance check from
     * @return true/false true if done and false if otherwise
     */
    public boolean checkAndBalance(Node startFrom) {
        if (root == null) {
            System.out.println("Empty AVL");
            return false;
        }
        Node current = startFrom;
        Node previous = null;
        while (current != null) {

            if (current.balanceFactor == -2) { //move forward one more step to be able to understand the situation beter
                if(previous.balanceFactor==-1){
                     leftRotate(current);
                     updateBalanceAfterLeftRotate(current);
                }else if(previous.balanceFactor==1){
                    rightRotate(previous);
                    updateBalanceAfterRightRotate(previous);
                    leftRotate(current);
                    updateBalanceAfterLeftRotate(current);
                }
                
            } else if (current.balanceFactor == 2) {
                  if(previous.balanceFactor==-1){
                     leftRotate(previous);
                     updateBalanceAfterLeftRotate(previous);
                     rightRotate(current);
                     updateBalanceAfterRightRotate(current);
                }else if(previous.balanceFactor==1){
                    rightRotate(current);
                    updateBalanceAfterRightRotate(current);;
                }
         
            } 
              previous=current;
              current = current.ancestor;
        }

        return true;
    }
    
    /**
     * Update balance factors of nodes after new insertion
     * @param startFrom node to start update from
     * @return true/false true if done and false if otherwise
     */
    public boolean updateBalanceFactorAfterInsertion(Node startFrom) {
        if (startFrom == null) {
            return false;
        }

        Node current = startFrom;
        int maxLeftLength = 0;
        int maxRightLength = 0;

        if (current.ancestor != null) {

            if (current.ancestor.noOfLeftSubTrees == 0 && current.ancestor.noOfRightSubTrees == 0) {//new child in a new level
                while (current != null) { //update height all the way up
                    maxLeftLength = 0;
                    maxRightLength = 0;
                    if (current.ancestor!= null && current.ancestor.left == current) {
                        current.ancestor.noOfLeftSubTrees = current.ancestor.noOfLeftSubTrees + 1;
                    } else if (current.ancestor != null && current.ancestor.right == current) {
                        current.ancestor.noOfRightSubTrees = current.ancestor.noOfRightSubTrees + 1;
                    }

                    //update balance factor here
                    if (current.ancestor != null && current.ancestor.left!=null) {
                        maxLeftLength = chooseFactor(current.ancestor.left.noOfLeftSubTrees, current.ancestor.left.noOfRightSubTrees) + 1;
                        //current.ancestor.noOfLeftSubTrees = maxLeftLength;
                    }
                    if (current.ancestor != null && current.ancestor.right!=null) {
                        maxRightLength = chooseFactor(current.ancestor.right.noOfLeftSubTrees, current.ancestor.right.noOfRightSubTrees) + 1;
                        //current.ancestor.noOfRightSubTrees = maxRightLength;
                    }
                    if(current.ancestor!=null){current.ancestor.balanceFactor = maxLeftLength - maxRightLength;}
                    current = current.ancestor;
                }
            } else { //update just parent
                if (current.ancestor.left != null && current.ancestor.left == current) {
                    current.ancestor.noOfLeftSubTrees = current.ancestor.noOfLeftSubTrees + 1;
                } else if (current.ancestor.right != null && current.ancestor.right == current) {
                    current.ancestor.noOfRightSubTrees = current.ancestor.noOfRightSubTrees + 1;
                }
                //update balance factor here
                if (current.ancestor.left != null) {
                    maxLeftLength = chooseFactor(current.ancestor.left.noOfLeftSubTrees, current.ancestor.left.noOfRightSubTrees) + 1;
                }
                if (current.ancestor.right != null) {
                    maxRightLength = chooseFactor(current.ancestor.right.noOfLeftSubTrees, current.ancestor.right.noOfRightSubTrees) + 1;
                }
                current.ancestor.balanceFactor = maxLeftLength - maxRightLength;
            }
        }

        return true;
    }
    
    /**
     * Update balance factors of nodes post rotation
     * @param startFrom node to start update from
     * @return true/false true if done and false if otherwise
     */
    public boolean updateBalanceFactorPostRotation(Node startFrom) {
        if (startFrom == null) {
            return false;
        }

        Node current = startFrom;
        int maxLeftLength = 0;
        int maxRightLength = 0;
        if (current.ancestor != null) {

            while (current != null) {
                //update balance factor here
                if (current.ancestor!= null && current.ancestor.left!=null) {
                    maxLeftLength = chooseFactor(current.ancestor.left.noOfLeftSubTrees, current.ancestor.left.noOfRightSubTrees) + 1;
                    current.ancestor.noOfLeftSubTrees = maxLeftLength;
                }
                if (current.ancestor!=null && current.ancestor.right != null) {
                    maxRightLength = chooseFactor(current.ancestor.right.noOfLeftSubTrees, current.ancestor.right.noOfRightSubTrees) + 1;
                    current.ancestor.noOfRightSubTrees = maxRightLength;
                }
                if(current.ancestor!=null){current.ancestor.balanceFactor = maxLeftLength - maxRightLength;}
                current = current.ancestor;  
            }

        }
        return true;
    }
    
    /**
     * Update balance factors of nodes after right rotation
     * @param pivot node to start update from
     * @return true/false true if done and false if otherwise
     */
    public boolean updateBalanceAfterRightRotate(Node pivot) {
        int maxLeftLength = 0;
        int maxRightLength = 0;
        //update balance factor here -PIVOT
        if (pivot.left != null) {
            maxLeftLength = chooseFactor(pivot.left.noOfLeftSubTrees, pivot.left.noOfRightSubTrees) + 1;
            pivot.noOfLeftSubTrees = maxLeftLength;
        }
        if (pivot.right != null) {
            maxRightLength = chooseFactor(pivot.right.noOfLeftSubTrees, pivot.right.noOfRightSubTrees) + 1;
            pivot.noOfRightSubTrees = maxRightLength;
        }
        pivot.balanceFactor = maxLeftLength - maxRightLength;
        
        //update balance factor here -PIVOT ANCESTOR
        maxLeftLength = 0;
        maxRightLength = 0;
        if (pivot.ancestor.left != null) {
            maxLeftLength = chooseFactor(pivot.ancestor.left.noOfLeftSubTrees, pivot.ancestor.left.noOfRightSubTrees) + 1;
            pivot.ancestor.noOfLeftSubTrees = maxLeftLength;
        }
        maxRightLength = chooseFactor(pivot.noOfLeftSubTrees, pivot.noOfRightSubTrees) + 1;
        pivot.ancestor.noOfRightSubTrees = maxRightLength;
        pivot.ancestor.balanceFactor = maxLeftLength - maxRightLength;
        updateBalanceFactorPostRotation(pivot.ancestor);
        return true;
    }
    
    /**
     * Update balance factors of nodes after left rotation
     * @param pivot node to start update from
     * @return true/false true if done and false if otherwise
     */
    public boolean updateBalanceAfterLeftRotate(Node pivot) {
        int maxLeftLength = 0;
        int maxRightLength = 0;
        //update balance factor here -PIVOT
        if(pivot.left!=null){
            maxLeftLength = chooseFactor(pivot.left.noOfLeftSubTrees, pivot.left.noOfRightSubTrees) + 1;
            pivot.noOfLeftSubTrees = maxLeftLength;
        }
        if(pivot.right!=null){
        maxRightLength = chooseFactor(pivot.right.noOfLeftSubTrees, pivot.right.noOfRightSubTrees) + 1;
            pivot.noOfRightSubTrees = maxRightLength;    
        }
         pivot.balanceFactor = maxLeftLength - maxRightLength;
         
          //update balance factor here -PIVOT ANCESTOR
         maxLeftLength = 0;
        maxRightLength = 0;
        if (pivot.ancestor.right != null) {
            maxRightLength = chooseFactor(pivot.ancestor.right.noOfLeftSubTrees, pivot.ancestor.right.noOfRightSubTrees) + 1;
            pivot.ancestor.noOfRightSubTrees = maxRightLength;
        }
        maxLeftLength = chooseFactor(pivot.noOfLeftSubTrees, pivot.noOfRightSubTrees) + 1;
        pivot.ancestor.noOfLeftSubTrees = maxLeftLength;
        pivot.ancestor.balanceFactor = maxLeftLength - maxRightLength; 
        updateBalanceFactorPostRotation(pivot.ancestor);
        return true;
    }
    
    /**
     * Decide node height
     * @param left left height
     * @param right right height
     * @return diff return difference
     */
    public int chooseFactor(int left, int right) {
        return (left > right) ? left : right;
    }
    
    /**
     * In order traversal of BSt
     * @param root root to start traversal from
     */
    public void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value + " , ");
        inOrderTraversal(root.right);
    }
    
    /**
     * Pre order traversal of BSt
     * @param root root to start traversal from
     */
        public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ,");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    
    /**
     * Node class
     */
    class Node implements Comparable {

        int value;
        int noOfLeftSubTrees;
        int noOfRightSubTrees;
        int balanceFactor;
        Node left;
        Node right;
        Node ancestor;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
            ancestor = null;
            noOfLeftSubTrees = 0;
            noOfRightSubTrees = 0;
            balanceFactor = 0;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getAncestor() {
            return ancestor;
        }

        public void setAncestor(Node ancestor) {
            this.ancestor = ancestor;
        }

        //check
        @Override
        public int compareTo(Object obj) {
            if (obj == null) {
                return 1;
            } else if (getClass() != obj.getClass()) {
                return 1;
            }
            Node toCompare = (Node) obj;
            if (this.value == toCompare.value) {
                return 0;
            } else {
                return this.value - toCompare.value;
            }
        }
    }

    /**
     * Avl Tree main/test method....uncomment to run
     *
     * @param args command line arguments
     * @throws TreeException Tree Exception
     */
    public static void main(String[] args) throws TreeException {
        //LOAD AVL TREE BST
        AvlTree avl = new AvlTree(7);
        for (int i = 0; i < 8; i++) {
            avl.add(i * 2);
            System.out.println("Root "+avl.root.value+" Balance factor: "+avl.root.balanceFactor+"\n");
        }
        avl.add(9);
        avl.add(11);
        avl.add(1);

        System.out.println("Pre Order Traversal: ");
        avl.preOrderTraversal(avl.root);
        System.out.println("\nAVL Root: " + avl.root.value+", Balance factor: "+avl.root.balanceFactor);
        System.out.println("AVL Root Left: " + avl.root.left.value);
        System.out.println("AVL Root Right: " + avl.root.right.value);
    }  
}
