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
 *<b>AVL Tree implementation</b> Insertion, Deletion, Traversal.
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class AvlTree {

    Node root;

    /**
     * Default constructor
     *
     * @param value value to be added in root node
     */
    AvlTree(int value) {
        root = new Node(value);
        System.out.println("Root: " + value);
        System.out.println("Balance factor: " + root.balanceFactor + "\n");
    }

    /**
     * Used to add nodes into the AVL
     *
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
            if (dec == 0) {
                System.out.println("Duplicates not allowed");
                return false;
            } else if (dec < 0) {//move rightwards
                if (current.right == null) {
                    System.out.println("Add " + value + " to right of " + current.value);
                    current.right = toAdd;
                    toAdd.ancestor = current;
                    break;
                } else {
                    current = current.right;
                    continue;
                }
            } else {//move leftwards
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
    
    /**
     * Delete from AVL tree
     * @param root node to start deletion search from
     * @param value value to delete
     * @return true/false true if deletion was successful and false if otherwise
     */
    public boolean delete(Node root, int value) {
        if (this.root == null) {
            return false;
        } else if (this.root.value == value) {
            this.root = null;
            return true;
        }

        Node current = root;
        Node ancestorOfDeleted = null;
        int dec = 0;
        while (current != null) {

            dec = decision(current.value, value);
            if (dec == 0) {
                //deletion point
                System.out.println("Deletion Point Found");
                //if node is leaf
                if (current.left == null && current.right == null) {
                    if (current.ancestor.right == current) {
                        current.ancestor.right = null;
                    } else {
                        current.ancestor.left = null;
                    }
                  ancestorOfDeleted = current.ancestor;
                  updateBalanceFactorAfterInsertion(ancestorOfDeleted);
                  if(ancestorOfDeleted.left!=null){checkAndBalance(ancestorOfDeleted.left);}
                  else if(ancestorOfDeleted.right!=null){checkAndBalance(ancestorOfDeleted.right);}
                  else{checkAndBalance(ancestorOfDeleted);}
                 return true;
                    
                } //Node has one right node attached to it
                else if (current.left == null && current.right != null) {
                    if (current.ancestor.right == current) {
                        current.ancestor.right = current.right;
                    } else {
                        current.ancestor.left = current.right;
                    }
                    ancestorOfDeleted = current.ancestor;
                 updateBalanceFactorAfterInsertion(ancestorOfDeleted);
                 if(ancestorOfDeleted.left!=null){checkAndBalance(ancestorOfDeleted.left);}
                  else if(ancestorOfDeleted.right!=null){checkAndBalance(ancestorOfDeleted.right);}
                  else{checkAndBalance(ancestorOfDeleted);}
                 return true;
                } //Node has one left node attached to it
                else if (current.left != null && current.right == null) {
                    if (current.ancestor.right == current) {
                        current.ancestor.right = current.left;
                    } else {
                        current.ancestor.left = current.left;
                    }
                 ancestorOfDeleted = current.ancestor;
                 updateBalanceFactorAfterInsertion(ancestorOfDeleted);
                 if(ancestorOfDeleted.left!=null){checkAndBalance(ancestorOfDeleted.left);}
                  else if(ancestorOfDeleted.right!=null){checkAndBalance(ancestorOfDeleted.right);}
                  else{checkAndBalance(ancestorOfDeleted);}
                 return true;
                } //Node is balanced
                else if (current.left != null && current.right != null) {
                    //replace node with minimum in the right sub-tree
                    current.value = findMinimum(current.right);
                    delete(current.right, current.value); //now delete the min
                    return true;
                }
            } else if (dec < 0) {//move rightwards
                current = current.right;
            } else if (dec >= 0) {//move leftwards
                current = current.left;
            }
        }
         System.out.println("Deletion Point Not Found"); //to get here means node was not found in tree
        return false;
    }

    /**
     * Use either in order traversal to find the minimum... O(N) or customary
     * while loop...O(log(N))
     *
     * @param root where to start search from
     * @return min minimum value in tree (search starts from provided root) 
     */
    public int findMinimum(Node root) {
        int minimum = root.value;
        Node current = root;
        while (current != null) {
            if (current.value <= minimum) {
                minimum = current.value;
            }
            current = current.left;
        }
        
        return minimum;
    }
    
      /**
     * Make decision whether to go left or right
     * @param current current node value
     * @param toAdd incoming node value
     * @return decision left or right
     */
    public int decision(int current, int toAdd) {
        return current - toAdd;
    }
    
    /**
     * To get height
     * @param left left node height
     * @param right right node height
     * @return highestHeight highest height
     */
    public int getMax(int left, int right) {
        return (left < right) ? right : left;
    }
    
       /**
     * Update balance factors of nodes after new insertion
     * @param startFrom node to start update from
     * @return true/false true if done and false if otherwise
     */
    public boolean updateBalanceFactorAfterInsertion(Node startFrom) {
        Node current = startFrom;
        while (current != null) {
            if (current.left != null && current.right != null) {
                current.height = getMax(current.left.height, current.right.height) + 1;
                current.balanceFactor = current.left.height - current.right.height;
            } else if (current.left != null && current.right == null) {
                current.height = current.left.height + 1;
                current.balanceFactor = current.left.height + 1;
            } else if (current.left == null && current.right != null) {
                current.height = current.right.height + 1;
                current.balanceFactor = 0 - (current.right.height + 1);
            } else if (current.left == null && current.right == null) {
                current.height = 0;
                current.balanceFactor = 0;
            }
            current = current.ancestor;
        }
        return true;
    }
    
       /**
     * Check if tree is balanced. Rotate if need be
     * @param startFrom node to start balance check from
     * @return true/false true if done and false if otherwise
     */
    public boolean checkAndBalance(Node startFrom) {
        if (startFrom == null) {
            return false;
        }

        Node current = startFrom;
        Node previous = null;
        while (current != null) {

            if (current.balanceFactor == -2) {
                if (previous.balanceFactor == -1) {
                    rotateLeft(current);
                    updateBalanceFactorAfterInsertion(current);
                } else if (previous.balanceFactor == 1) {
                    rotateRight(previous);
                    updateBalanceFactorAfterInsertion(previous);
                    rotateLeft(current);
                    updateBalanceFactorAfterInsertion(current);
                }
            } else if (current.balanceFactor == 2) {
                if (previous.balanceFactor == -1) {
                    rotateLeft(previous);
                    updateBalanceFactorAfterInsertion(previous);
                    rotateRight(current);
                    updateBalanceFactorAfterInsertion(current);
                } else if (previous.balanceFactor == 1) {
                    rotateRight(current);
                    updateBalanceFactorAfterInsertion(current);
                }
            }
            previous = current;
            current = current.ancestor;
        }
        return true;
    }
    
        /**
     * Rotate AVL node leftwards
     * @param pivot pivot node
     * @return ture/false true if done and false if otherwise
     */
    public boolean rotateLeft(Node pivot) {
        if (pivot == null) {
            return false;
        }
        System.out.println("Left Rotating: " + pivot.value);
        Node temp = pivot.right.left;

        pivot.right.ancestor = pivot.ancestor;
        if (pivot.ancestor != null) {
            if (pivot.ancestor.left == pivot) {
                pivot.ancestor.left = pivot.right;
            } else if (pivot.ancestor.right == pivot) {
                pivot.ancestor.right = pivot.right;
            }
        } else {
            root = pivot.right;
        }
        pivot.right.left = pivot;
        pivot.ancestor = pivot.right;

        pivot.right = temp;
        if (temp != null) {
            temp.ancestor = pivot;
        }
        return true;
    }
    
     /**
     * Rotate AVL node rightwards
     * @param pivot pivot node
     * @return ture/false true if done and false if otherwise
     */
    public boolean rotateRight(Node pivot) {
        if (pivot == null) {
            return false;
        }
        System.out.println("Right Rotating: " + pivot.value);
        Node temp = pivot.left.right;

        pivot.left.ancestor = pivot.ancestor;
        if (pivot.ancestor != null) {
            if (pivot.ancestor.left == pivot) {
                pivot.ancestor.left = pivot.left;
            } else if (pivot.ancestor.right == pivot) {
                pivot.ancestor.right = pivot.left;
            }
        } else {
            root = pivot.left;
        }

        pivot.left.right = pivot;
        pivot.ancestor = pivot.left;

        pivot.left = temp;
        if (temp != null) {
            temp.ancestor = pivot;
        }

        return true;
    }

    /**
     * Pre order traversal of BSt
     *
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
     * In order traversal of BSt
     *
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
     * Node class
     */
    class Node implements Comparable {

        int value;
        int balanceFactor;
        int height;
        Node left;
        Node right;
        Node ancestor;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
            ancestor = null;
            balanceFactor = 0;
            height = 0;
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
     *//*
    public static void main(String[] args) throws TreeException {
        //LOAD AVL TREE BST
        AvlTree3 avl = new AvlTree3(7);
        for (int i = 0; i < 8; i++) {
            avl.add(i * 2);
            System.out.println("Root " + avl.root.value + " Balance factor: " + avl.root.balanceFactor + "\n");
        }
        avl.add(9);
        avl.add(11);
        avl.add(1);
        
        if(avl.root!=null){
        System.out.println("Pre Order Traversal: ");
        avl.preOrderTraversal(avl.root);
        System.out.println("\nAVL Root: " + avl.root.value + ", Balance factor: " + avl.root.balanceFactor);
        System.out.println("AVL Root Left: " + avl.root.left.value);
        System.out.println("AVL Root Right: " + avl.root.right.value);
        }
        
        System.out.println("Delete 4: ");
        avl.delete(avl.root, 4);
        

        if(avl.root!=null){
        System.out.println("Pre Order Traversal: ");
        avl.preOrderTraversal(avl.root);
        System.out.println("\nAVL Root: " + avl.root.value + ", Balance factor: " + avl.root.balanceFactor);
        System.out.println("AVL Root Left: " + avl.root.left.value);
        System.out.println("AVL Root Right: " + avl.root.right.value);
        }
    }
*/
}
