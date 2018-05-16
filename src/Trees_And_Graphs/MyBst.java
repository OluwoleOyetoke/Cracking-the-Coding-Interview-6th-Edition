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
 *<b>BST Implementation:</b> Add, Delete, Search & Traverse (pre-order, in order and post order) 
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * @param <Type>
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
    }
    
    /**
     * Add node to BST
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
        Node left = root.left;
        Node right = root.right;
        Node anscestor = root.anscestor;

        while (current != null) {
           
            if(toAdd.compareTo(current)<=0 && left==null){
                current.left=toAdd;
                return true;
            }else if(toAdd.compareTo(current)>0 && right==null){
                current.right=toAdd;
                return true;
            }else if(toAdd.compareTo(current)<=0 && left!=null){
                anscestor = current;
                current = left;
                left = current.left;
                right = current.right;
            }else if(toAdd.compareTo(current)>0 && right!=null){
                anscestor = current;
                current = right;
                left = current.left;
                right = current.right;
            }
        }

        return true;
    }
    
    /**
     * Search if BST contains a particular node
     * @param value value in node to be searched for
     * @return ture/false returns true if node exists and false if otherwise
     */
    public boolean search(Type value){
         if (root == null) {
            return false;
        }
        Node toSearchFor= new Node(value);
        Node current = root;
        Node left = root.left;
        Node right = root.right;
        Node anscestor = root.anscestor;

        while (current != null) { 
            if(current.value==value){
                return true;
            }else if(toSearchFor.compareTo(current)<0 && left!=null){
                anscestor = current;
                current = left;
                left = current.left;
                right = current.right;
            }else if(toSearchFor.compareTo(current)>0 && right!=null){
                anscestor = current;
                current = right;
                left = current.left;
                right = current.right;
            }else if(toSearchFor.compareTo(current)<0 && left==null){
                return false;
            }else if(toSearchFor.compareTo(current)>0 && right==null){
                return false;
            }  
        }
        return false;
    }
    
    /**
     * Delete nide from BST
     * @param value value to delete
     * @return value in deleted node. Null if value does not exist
     * @throws TreeException 
     */
    public Type delete(Type value) throws TreeException{

         if (root == null) {
            return null;
        }
         
        Node toDelete = new Node(value);
        Node current = root;
        Node left = root.left;
        Node right = root.right;
        Node anscestor = root.anscestor;

        while (current != null) {
            
            if(toDelete.value==current.value){
                
                if(isLeaf(current)){
                    if(current.anscestor.left.value==current.value){
                        current.anscestor.left=null;
                        return current.value;
                    }else{
                        current.anscestor.right=null;
                        return current.value;
                    }
                }else if(current.right==null && current.left!=null){
                    current.left.anscestor = current.anscestor;
                    current = current.left;
                    return current.value;
                }else if(current.right!=null && current.left==null){
                    current.right.anscestor = current.anscestor;
                    current = current.right;
                    return current.value;
                }
                
                if(current.right!=null && current.left!=null){
                    removeAlgorithm(current);  //run remove algorithm 
                    return current.value;
                }
            }else{
                if(toDelete.compareTo(current)<0 && left!=null){
                anscestor = current;
                current = left;
                left = current.left;
                right = current.right;
            }else if(toDelete.compareTo(current)>0 && right!=null){
                anscestor = current;
                current = right;
                left = current.left;
                right = current.right;
            }
            }
    }
        return null;
    }
    
    /**
     * Used to remove a node which has root children (left  and/or right)
     * @param node Node to start removal search operation from
     */
    private void removeAlgorithm(Node node){
        //replace to remove node with the minimum element in the right subtree
        Node minimum = findMinimumFrom(node.right);
        node.value = minimum.value;
        try{
        removeFrom(node.right, node);
        }catch(TreeException ex){
            System.out.println("Tree exception: "+ex);
        }
    }
    
    private Node findMinimumFrom(Node node){
        Node current = node;
        Node minimum = current;
        while(current!=null){
            if(current.compareTo(minimum)<=0){
                minimum=current;
                current = current.left;
            }else if(current.compareTo(minimum)>0){
                current = current.left;
            }
        }
        return minimum;
    }
    
    private boolean removeFrom(Node root, Node toDelete) throws TreeException{
           if (root == null) {
             throw new TreeException("Null node recived for isLeaf");
        }
         
        Node current = root;
        Node left = root.left;
        Node right = root.right;
        Node anscestor = root.anscestor;

        while (current != null) {
            
            if(toDelete.value==current.value){
                
                if(isLeaf(current)){
                    if(current.anscestor.left.value==current.value){
                        current.anscestor.left=null;
                    }else{
                        current.anscestor.right=null;
                    }
                }else if(current.right==null && current.left!=null){
                    current.left.anscestor = current.anscestor;
                    current = current.left;
                }else if(current.right!=null && current.left==null){
                    current.right.anscestor = current.anscestor;
                    current = current.right;
                }  else if(current.right!=null && current.left!=null){
                    removeAlgorithm(current);    //run remove algorithm 
                    return true;
                }
            } else{
                if(toDelete.compareTo(current)<0 && left!=null){
                anscestor = current;
                current = left;
                left = current.left;
                right = current.right;
            }else if(toDelete.compareTo(current)>0 && right!=null){
                anscestor = current;
                current = right;
                left = current.left;
                right = current.right;
            }
            }
    }
        
        return false;
    }
        
       public boolean isLeaf(Node node) throws TreeException{
           if(node==null){
              throw new TreeException("Null node recived for isLeaf");
           }
           if(node.left==null && node.right==null){
               return true;
           }else{
               return false;
           }
       }

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
            } else if (obj == null) {
                return 1;
            } else if (getClass() != obj.getClass()) {
                return 1;
            }

            Node toCompare = (Node) obj;
            if (this.value.getClass().getName().equals("java.lang.String")) {
                return ((String) this.value).compareTo((String) toCompare.value);
            } else {
                return (int) ((Long)this.value - (Long)toCompare.value); //Any other type apart from int, float, double and long will break this
            }
        }

    }
    //7:25

    public static void main(String[] args) {
        Integer l = 1;
        System.out.println(l.getClass().getName());
    }
}
