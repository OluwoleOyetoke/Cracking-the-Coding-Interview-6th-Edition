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
 * <b>BST Implementation:</b> Add, Delete, Search and Traverse (pre-order, in
 * order and post order)
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 * @param <Type> type
 */
public class MyBst<Type> {

    private Node root;

    /**
     * Default constructor. Takes in root node
     *
     * @param root root node
     */
    MyBst(Type rootValue) {
        root = new Node(rootValue);
        System.out.println("Root: " + rootValue);
    }

    /**
     * Add node to BST
     *
     * @param value value to be placed in node
     * @return ture/false ture if addition was successful
     */
    public boolean add(Type value) {

        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node toAdd = new Node(value);
        Node current = root;
        while (current != null) {

            int decision = decide(current, toAdd);
            if(decision==0){
                System.out.println("Duplicates not allowed");
                return false;
            }
            else if (decision <= 0 && current.left == null) {
                System.out.println("Add " + toAdd.value + " to left of " + current.value);
                current.left = toAdd;
                current.left.anscestor = current;
                return true;
            } else if (decision > 0 && current.right == null) {
                System.out.println("Add " + toAdd.value + " to right of " + current.value);

                current.right = toAdd;
                current.right.anscestor = current;
                return true;
            } else if (decision <= 0 && current.left != null) {
                current = current.left;
            } else if (decision > 0 && current.right != null) {
                current = current.right;
            }
        }

        return false;
    }

    /**
     * Search if BST contains a particular node
     *
     * @param value value in node to be searched for
     * @return ture/false returns true if node exists and false if otherwise
     */
    public boolean search(Type value) {
        if (root == null) {
            return false;
        }
        Node toSearchFor = new Node(value);
        Node current = root;
        Node left = root.left;
        Node right = root.right;
        Node anscestor = root.anscestor;

        while (current != null) {
            int decision = decide(current, toSearchFor);
            if (current.value == value) {
                System.out.println(value + " Found in BST");
                return true;
            } else if (decision < 0 && left != null) {
                anscestor = current;
                current = left;
                left = current.left;
                right = current.right;
            } else if (decision < 0 && left == null) {
                return false;
            } else if (decision > 0 && right != null) {
                anscestor = current;
                current = right;
                left = current.left;
                right = current.right;
            } else if (decision > 0 && right == null) {
                System.out.println(value + " Not Found In BST");
                return false;
            }
        }
        System.out.println(value + " Not Found In BST");
        return false;
    }

    /**
     * Delete node from BST
     *
     * @param root node to start deletion search from
     * @param value value to delete
     * @return value in deleted node. Null if value does not exist
     * @throws TreeException tree exception
     */
    public Type delete(Node root, Type value) throws TreeException {
        if (root == null) {
            return null;
        }else if(this.root.value==value){
            System.out.println("Attempting to delete root node");
            return null;
        }

        Node toDelete = new Node(value);
        Node current = root;
        Node left = root.left;
        Node right = root.right;
        Node anscestor = root.anscestor;

        while (current != null) {

            if (toDelete.value == current.value) {

                if (isLeaf(current)) {
                    if (current.anscestor.left != null && current.anscestor.left.value == toDelete.value) {
                        current.anscestor.left = null;
                        return current.value;
                    } else {
                        current.anscestor.right = null;
                        return current.value;
                    }

                } else if (current.right == null && current.left != null) {

                    if (current.anscestor.left == current) {
                        current.anscestor.left = current.left;
                        current.left.anscestor = current.anscestor.left;
                    } else if (current.anscestor.right == current) {
                        current.anscestor.right = current.left;
                        current.left.anscestor = current.anscestor.right;
                    }
                    return current.value;

                } else if (current.right != null && current.left == null) {

                    if (current.anscestor.left == current) {
                        current.anscestor.left = current.right;
                        current.right.anscestor = current.anscestor.left;
                    } else if (current.anscestor.right == current) {
                        current.anscestor.right = current.right;
                        current.right.anscestor = current.anscestor.right;
                    }
                    return current.value;

                } else if (current.right != null && current.left != null) {
                    Type toBeDeleted = current.value;
                    removeAlgorithm(current);
                    return toBeDeleted;
                }

            } else {
                int decision = decide(current, toDelete);
                if (decision < 0 && left != null) {
                    anscestor = current;
                    current = left;
                    left = current.left;
                    right = current.right;
                } else if (decision < 0 && left == null) {
                    System.out.println("Node doesnt exist in tree");
                    return null;
                } else if (decision > 0 && right != null) {
                    anscestor = current;
                    current = right;
                    left = current.left;
                    right = current.right;
                } else if (decision > 0 && right == null) {
                    System.out.println("Node doesnt exist in tree");
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Pre order traversal of the BST
     *
     * @param root node to start from
     */
    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + ", ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * In order traversal of the BST
     *
     * @param root node to start from
     */
    public void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.value + ", ");
        inOrderTraversal(root.right);

    }

    /**
     * Post order traversal of the BST
     *
     * @param root node to start from
     */
    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + ", ");

    }

    private int decide(Node node, Node toCompare) {
        if (node.compareTo(toCompare) < 0) {
            return 1;
        } else if (node.compareTo(toCompare) == 0) {
            return 0;
        } else if (node.compareTo(toCompare) > 0) {
            return -1;
        }
        return -2;
    }

    /**
     * Used to remove a node which has root children (left and/or right)
     *
     * @param node Node to start removal search operation from
     */
    private void removeAlgorithm(Node node) {
        //replace to remove node with the minimum element in the right subtree
        Node minimum = findMinimumFrom(node.right);
        node.value = minimum.value;
        try {
            delete(node.right, minimum.value);
        } catch (TreeException ex) {
            System.out.println("Tree exception: " + ex);
        }
    }

    private Node findMinimumFrom(Node node) {
        Node current = node;
        Node minimum = current;
        while (current != null) {
            if (current.compareTo(minimum) <= 0) {
                minimum = current;
                current = current.left;
            } else if (current.compareTo(minimum) > 0) {
                current = current.left;
            }
        }
        return minimum;
    }

    public boolean isLeaf(Node node) throws TreeException {
        if (node == null) {
            throw new TreeException("Null node recived for isLeaf");
        }
        if (node.left == null && node.right == null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Node class. Implements comparable to help with the BST compare operations
     */
    class Node implements Comparable {

        private Type value;
        private Node left;
        private Node right;
        private Node anscestor;

        /**
         * Default constructor
         *
         * @param value value of node
         */
        Node(Type value) {
            this.value = value;
            left = null;
            right = null;
            anscestor = null;
        }

        public Type getValue() {
            return value;
        }

        public void setValue(Type value) {
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

        public Node getAnscestor() {
            return anscestor;
        }

        public void setAnscestor(Node anscestor) {
            this.anscestor = anscestor;
        }

        @Override
        public int compareTo(Object obj) {
            if (this == obj) {
                return 0;
            }

            Node toCompare = (Node) obj;
            if (this.value.getClass().getName().equals("java.lang.String")) {
                return ((String) this.value).compareTo((String) toCompare.value);
            } else {
                return (int) ((Integer) this.value - (Integer) toCompare.value); //Any other type apart from int, float, double and long will break this
            }
        }

    }
    
    /**
     * MyBst main/test method....uncomment to run
     * @param args command line arguments
     * @throws TreeException Tree Exception
     *//*
    public static void main(String[] args) throws TreeException {
        //LOAD AND LOAD BST
        MyBst<Integer> bst = new MyBst<>(7);
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            bst.add(toAdd);
        }
        bst.add(9);
        bst.add(11);
        bst.add(1);
        
        
        //TRAVERSE BST
        System.out.println("\nPRE ORDER TRAVERSAL: ");
        bst.preOrderTraversal(bst.root);
        System.out.println("\nIN ORDER TRAVERSAL: ");
        bst.inOrderTraversal(bst.root);  
        System.out.println("\nPOST ORDER TRAVERSAL: ");
        bst.postOrderTraversal(bst.root);
        
        //SEARCH, DELETE AND SEARCH
        System.out.println("\nSEARCH FOR 10");
        bst.search(10);
        System.out.println("Deleted: " + bst.delete(bst.root, 10));
        System.out.println("SEARCH FOR 10 AFTER DELETION");
        bst.search(10);     
    }*/
}
