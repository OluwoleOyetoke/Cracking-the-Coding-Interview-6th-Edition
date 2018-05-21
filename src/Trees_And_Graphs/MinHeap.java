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

import java.util.ArrayList;

/**
 * <b>MinHeap Implementation</b>... Insert, Delete and findMin
 *Rough implementation
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class MinHeap {

    HeapNode root;
    int level;
    int levelQuota;
    boolean found;
    HeapNode foundNode;
    ArrayList<HeapNode> list = new ArrayList();

    /**
     * Default constructor
     *
     * @param value value to be added as root
     */
    MinHeap(int value) {
        this.root = new HeapNode(value);
        level = 0;
        levelQuota = 0;
    }

    /**
     * Used to add nodes into the BST
     *
     * @param value value to be added to BST
     * @return true/false true if value was successfully added
     */
    public boolean add(int value) {
        if (root == null) {
            root.value = value;
            level = 0;
            return true;
        }
        HeapNode toAdd = new HeapNode(value);
        //get level-before the last
        ArrayList<HeapNode> list = new ArrayList();
        adderHelper(root, level, list);
        HeapNode node = null;
        for (int i = 0; i < list.size(); i++) {
            node = list.get(i);
            if (node.left == null && node.right == null) {
                node.left = toAdd;
                toAdd.ancestor = node;
                levelQuota++;
                break;
            } else if (node.left != null && node.right == null) {
                node.right = toAdd;
                toAdd.ancestor = node;
                levelQuota++;
                break;
            }
        }
        int lvl = level + 1;
        if (levelQuota == Math.pow(2, lvl)) {
            level++;
            levelQuota = 0;
        }

        minHeapify(toAdd);
        //maxHeapify(toAdd);

        return false;
    }

    /**
     * Delete from Heap
     *
     * @param root node to start deletion search from
     * @param value value to delete
     * @return true/false true if deletion was successful and false if otherwise
     */
    public boolean delete(HeapNode root, int value) {
        if (this.root == null) {
            return false;
        } else if (this.root.value == value && root.left == null && root.right == null) {
            this.root = null;
            level = -1;
            return true;
        }

        HeapNode lastNode = getLast();
        found = false;
        for (int i = 0; i < level + 2; i++) {
            findAndReplace(root, value, i); //search per level
            if (found == true) {
                foundNode.value = lastNode.value;
                if (lastNode.ancestor.left.value == lastNode.value) {
                    lastNode.ancestor.left = null;
                } else {
                    lastNode.ancestor.right = null;
                }
                break;
            }
        }
        if (found == false) {
            System.out.println("Deletion Point Not Found");
            return false;
        }

        //decide if to heapify Up or Down
        if (foundNode.ancestor != null && foundNode.value < foundNode.ancestor.value) {
            minHeapify(foundNode);
        } else {
            minHeapifyDown(foundNode);
        }

        //make level count Ok
        ArrayList<HeapNode> list = new ArrayList();
        adderHelper(root, level + 1, list);
        if (list.isEmpty()) {
            System.out.println("Level Changed");
            levelQuota = (int) Math.pow(2, level);
            level--;
        } else {
            levelQuota--;
        }

        return false;
    }

    public boolean minHeapifyDown(HeapNode startFrom) {
        if (startFrom == null) {
            return false;
        }

        HeapNode current = startFrom;
        int temp;
        while (current != null) {

            if ((current.left != null && current.value > current.left.value) && (current.right != null && current.value > current.right.value)) {

                if (current.right.value >= current.left.value) {
                    temp = current.value;
                    current.value = current.left.value;
                    current.left.value = temp;
                    current = current.left;

                } else if (current.left.value > current.right.value) {
                    temp = current.value;
                    current.value = current.right.value;
                    current.right.value = temp;
                    current = current.right;
                }

            } else if (current.left != null && current.value > current.left.value) {
                temp = current.value;
                current.value = current.left.value;
                current.left.value = temp;
                current = current.left;
            } else if (current.right != null && current.value > current.right.value) {
                temp = current.value;
                current.value = current.right.value;
                current.right.value = temp;
                current = current.right;
            } else {
                break;
            }

            if (current.ancestor == null) {
                root = current;
            }

        }

        return true;
    }

    public HeapNode getLast() {
        list = new ArrayList();
        adderHelper(root, level, list);
        HeapNode node = null;
        HeapNode last = null;
        for (int i = 0; i < list.size(); i++) {
            node = list.get(i);
            if (node.left == null && node.right != null) {
                last = node.right;
            } else if (node.left != null && node.right == null) {
                last = node.left;
            } else if (node.left != null && node.right != null) {
                last = node.right;
            }
        }
        return last;
    }

    /**
     * Replace node during deletion. use node object due to the possibility of
     * duplicate elements in the heap
     *
     * @param root node to start search from
     * @param toBeReplaced node value to be replace
     * @param level tree level
     */
    public void findAndReplace(HeapNode root, int toBeReplaced, int level) {
        if (root == null) {
            return;
        }

        if (level == 0) {
            if (root.value == toBeReplaced) {
                found = true;
                foundNode = root;
            }
        } else {
            findAndReplace(root.left, toBeReplaced, level - 1);
            findAndReplace(root.right, toBeReplaced, level - 1);
        }
    }

    public boolean minHeapify(HeapNode startFrom) {
        if (startFrom == null) {
            return false;
        } else if (startFrom == root) {
            return true;
        }

        HeapNode current = startFrom;
        while (current != null) {
            if (current.ancestor != null) {

                if (current.value <= current.ancestor.value) {   //swap
                    current.value = current.ancestor.value + current.value;
                    current.ancestor.value = current.value - current.ancestor.value;
                    current.value = current.value - current.ancestor.value;
                    if (current.ancestor == null) {
                        root = current;
                    }
                } else {
                    break;
                }
            }
            current = current.ancestor;

        }
        return true;
    }

    /**
     * Get min value in heap
     *
     * @return min min value in heap
     * @throws TreeException
     */
    public int getMin() throws TreeException {
        if (root == null) {
            throw new TreeException("Heap is empty");
        }
        return root.value;
    }

    public void adderHelper(HeapNode root, int level, ArrayList<HeapNode> storage) {
        if (root == null) {
            return;
        }

        if (level == 0) {
            storage.add(root);
        } else {
            adderHelper(root.left, level - 1, storage);
            adderHelper(root.right, level - 1, storage);
        }
    }

    /**
     * Level order traversal of Heap
     *
     * @param root root to start traversal from
     */
    public void levelOrderTraversal(HeapNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 0) {
            System.out.print(root.value + " ,");
        } else {
            levelOrderTraversal(root.left, level - 1);
            levelOrderTraversal(root.right, level - 1);
        }
    }

    /**
     * Min Heap main/test method....uncomment to run
     *
     * @param args command line arguments
     * @throws TreeException Tree Exception
     *//*
    public static void main(String[] args) throws TreeException {
        //LOAD AND LOAD BST
        MinHeap minHeap = new MinHeap(7);
        System.out.println("After adding Root: 7, Min= " + minHeap.getMin());
        for (int i = 0; i < 8; i++) {
            Integer toAdd = i * 2;
            minHeap.add(toAdd);
            System.out.println("After adding: " + toAdd + ", Min= " + minHeap.getMin());
        }
        minHeap.add(9);
        System.out.println("After adding: 9, Min= " + minHeap.getMin());
        minHeap.add(11);
        System.out.println("After adding: 11, Min= " + minHeap.getMin());
        minHeap.add(1);
        System.out.println("After adding: 1, Min= " + minHeap.getMin());

        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            minHeap.levelOrderTraversal(minHeap.root, i);
            System.out.println("\n");
        }

        System.out.println("Delete 1: ");
        minHeap.delete(minHeap.root, 1);
        System.out.println("Delete 4: ");
        minHeap.delete(minHeap.root, 4);
        System.out.println("Delete 7: ");
        minHeap.delete(minHeap.root, 7);
        System.out.println("Delete 6: ");
        minHeap.delete(minHeap.root, 6);

        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            minHeap.levelOrderTraversal(minHeap.root, i);
            System.out.println("\n");
        }

        System.out.println("Delete 12: ");
        minHeap.delete(minHeap.root, 12);
        System.out.println("Delete 10: ");
        minHeap.delete(minHeap.root, 10);
        System.out.println("Delete 4: ");
        minHeap.delete(minHeap.root, 4);
        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            minHeap.levelOrderTraversal(minHeap.root, i);
            System.out.println("\n");
        }

        System.out.println("Delete 0: ");
        minHeap.delete(minHeap.root, 0);
        //TRAVERSE HEAP - LEVEL ORDER
        System.out.println("\nLEVEL ORDER TRAVERSAL: ");
        for (int i = 0; i < 5; i++) {
            minHeap.levelOrderTraversal(minHeap.root, i);
            System.out.println("\n");
        }

    }
*/
}
