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
package com.cracking.the.coding.interview.chapter02.linkedlist.datastructure;

import java.util.Comparator;

/**
 * Singly linked list implementation
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
@SuppressWarnings("unused")
public class MySinglyLinkedList<Type> implements Comparable<Type> {

    public Node<Type> head;
    public Node<Type> tail;
    public int size;

    public MySinglyLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Add content to linked list
     *
     * @param content content to add
     * @return true/false return true if successful and false if otherwise
     */
    public boolean add(Type content) {
        Node<Type> newNode = new Node<Type>(content);
        if (head == null) {
            head = newNode;
            tail = head;
            size++;
        } else {
            Node<Type> cursor = head;
            while (cursor != null) {
                if (cursor.nextNode == null) {
                    cursor.nextNode = newNode;
                    tail = cursor.nextNode;
                    size++;
                    break;
                }
                cursor = cursor.nextNode;
            }
        }
        return true;
    }

    /**
     * Add content in specified index
     *
     * @param index index to add content to
     * @param content content to add
     * @return true/false true if addition was successful
     */
    public boolean add(int index, Type content) {
        if (index >= size) {
            System.out.println("Index out of bound");
            return false;
        }
        Node<Type> newNode = new Node(content);
        if (head == null && index == 0) {
            head = newNode;
            tail = head;
            size++;
            return true;
        } else if (index == 0 && head != null) {
            newNode.nextNode = head;
            head = newNode;
            size++;
            return true;
        }

        Node<Type> currentNode = head;
        Node<Type> previousNode = head;
        int count = 0;

        while (currentNode != null) {
            if (count == index) {
                previousNode.nextNode = newNode;
                newNode.nextNode = currentNode;
                size++;
                break;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
            count++;
        }
        return true;
    }

    /**
     * Add content as the head of the linked list
     *
     * @param content content to add
     * @return true/false returns true if add was successful and false if
     * otherwise
     */
    public boolean addFirst(Type content) {
        if (content == null) {
            System.out.println("Null content received");
            return false;
        }
        Node<Type> newNode = new Node(content);
        if (head == null) {
            head = newNode;
            tail = head;
            size++;
            return true;
        } else {
            newNode.nextNode = head;
            head = newNode;
            size++;
            return true;
        }
    }

    /**
     * Add content as the tail of the linked list
     *
     * @param content content to add
     * @return true/false returns true if add was successful and false if
     * otherwise
     */
    public boolean addLast(Type content) {
        if (content == null) {
            System.out.println("Null content received");
            return false;
        }
        Node<Type> newNode = new Node(content);
        if (head == null) {
            head = newNode;
            tail = head;
            size++;
            return true;
        } else {
            Node<Type> cursor = head;
            while (cursor != null) {
                if (cursor.nextNode == null) {
                    cursor.nextNode = newNode;
                    tail = cursor.nextNode;
                    size++;
                    return true;
                }
                cursor = cursor.nextNode;
            }
        }
        return true;
    }

    /**
     * Clears the linked list. Linked list becomes null
     */
    public void clear() {
        head = null;
        tail = null;
        //garbage collector will clear the  rest of the inner objects between head and tail
    }

    /**
     * Clone the current linked list object
     *
     * @return singlyLinkedList shallow copy of the current linked list
     */
    public MySinglyLinkedList<Type> cloneThis() {
        MySinglyLinkedList<Type> singlyLinkedList = this;
        return singlyLinkedList;
    }

    /**
     * Clone the current linked list object
     *
     * @return singlyLinkedList shallow copy of the current linked list
     */
    public MySinglyLinkedList<Type> deepCopy() {
        MySinglyLinkedList<Type> singlyLinkedList = new MySinglyLinkedList<>();
        Node<Type> cursor = head;
        while (cursor != null) {
            singlyLinkedList.add(cursor.content);
            cursor = cursor.nextNode;
        }
        return singlyLinkedList;
    }

    /**
     * Converts the linked list into an array
     *
     * @return array linked list in array format
     */
    public Type[] toArray() {
        if (head == null) {
            return null;
        }
        Type[] toReturn = (Type[]) new Object[size];
        Node<Type> cursor = head;
        int count = 0;
        while (cursor != null) {
            toReturn[count] = cursor.content;
            cursor = cursor.nextNode;
            count++;
        }
        return toReturn;
    }

    /**
     * Converts the linked list into string
     *
     * @return array linked list as a String
     */
    public String thisToString() {
        if (head == null) {
            return "Linked List is empty";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Node<Type> cursor = head;
        // stringBuilder.append(String.valueOf(head.content));
        while (cursor != null) {
            stringBuilder.append(String.valueOf(cursor.content));
            cursor = cursor.nextNode;
            if (cursor != null) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the head node of this list. But does not remove it
     *
     * @return head the head node of this list
     */
    public Type element() {
        return head.content;
    }

    /**
     * Returns element in a particular index (if index exists)
     *
     * @param index index to search in
     * @return element content of the node in that index
     */
    public Type get(int index) {
        if (head == null) {
            // System.out.println("List is empty");
            return null;
        } else if (index >= size) {
            // System.out.println("Index out of bound");
            return null;
        } else if (index == 0 && head != null) {
            return head.content;
        }
        Node<Type> cursor = head;
        int count = 0;
        while (cursor != null) {
            if (count == index) {
                return cursor.content;
            }
            cursor = cursor.nextNode;
            count++;
        }
        return null;
    }

    /**
     * Returns node in a particular index (if index exists)
     *
     * @param index index to search in
     * @return element content of the node in that index
     */
    public Node<Type> getNode(int index) {
        if (head == null) {
            // System.out.println("List is empty");
            return null;
        } else if (index >= size) {
            // System.out.println("Index out of bound");
            return null;
        } else if (index == 0 && head != null) {
            return head;
        }
        Node<Type> cursor = head;
        int count = 0;
        while (cursor != null) {
            if (count == index) {
                return cursor;
            }
            cursor = cursor.nextNode;
            count++;
        }
        return null;
    }

    /**
     * Get first element in the linked list
     *
     * @return head returns content of head (if head is not null)
     */
    public Type getFirst() {
        return head.content;
    }

    /**
     * Returns the last element in the linked list
     *
     * @return last last element in the list
     */
    public Type getLast() {
        return tail.content;
    }

    /**
     * Return the first index where the element with value 'content' exists (if
     * any)
     *
     * @param content content to search for
     * @return index first index with content being searched for. Return -1 is
     * content does not exist
     */
    public int indexOf(Type content) {
        if (head == null) {
            return -1;
        }
        Node<Type> cursor = head;
        int counter = 0;
        while (cursor != null) {
            if (cursor.content == content) {
                return counter;
            }
            cursor = cursor.nextNode;
            counter++;
        }
        return -1;
    }

    /**
     * Return the last index where the element with value 'content' exists (if
     * any)
     *
     * @param content content to search for
     * @return index first index with content being searched for
     */
    public int lastIndexOf(Type content) {
        if (head == null) {
            return -1;
        }
        Node<Type> cursor = head;
        int counter = 0;
        int lastIndex = -1;
        while (cursor != null) {
            if (cursor.content == content) {
                lastIndex = counter;
            }
            cursor = cursor.nextNode;
            counter++;
        }
        return lastIndex;
    }

    /**
     * Retrieve and remove the first element of the linked list
     *
     * @return element element removed
     */
    public Type remove() {
        if (head == null) {
            return null;
        }
        Type toReturn;
        toReturn = head.content;
        head = head.nextNode;
        size--;
        return toReturn;
    }

    /**
     * Remove first occurrence of content from list (if exist)
     *
     * @param content content to search for
     * @return true/false true if content exists and has been removed. False if
     * otherwise
     */
    public boolean remove(Type content) {
        if (head == null) {
            return false;
        }
        Node<Type> current = head;
        Node<Type> previous = head;
        while (current != null) {
            if (current.content == content) {
                previous.nextNode = current.nextNode;
                size--;
                return true;
            }
            previous = current;
            current = current.nextNode;

        }
        return false;
    }

    /**
     * Remove content at index
     *
     * @param index index whose content should be removed
     * @return true/false true if index exists and has been removed and false if
     * otherwise
     */
    public boolean remove(int index) {
        if (head == null) {
            return false;
        }
        Node<Type> current = head;
        Node<Type> previous = head;
        int counter = 0;
        while (current != null) {
            if (counter == index) {
                previous.nextNode = current.nextNode;
                size--;
                return true;
            }
            previous = current;
            current = current.nextNode;
            counter++;
        }

        return false;
    }

    /**
     * Remove content last element of the LinekdList
     *
     * @return true/false true if index exists and has been removed and false if
     * otherwise
     */
    public boolean removeLast() {
        if (head == null) {
            return false;
        } else if (head.nextNode == null) {
            head = null;
            tail = null;
            size--;
            return true;
        }
        Node<Type> current = head;
        Node<Type> previous = head;
        while (current != null) {
            if (current.nextNode == null) {
                previous.nextNode = current.nextNode;
                size--;
                return true;
            }
            previous = current;
            current = current.nextNode;
        }
        return false;
    }

    /**
     * Get size of linked list
     *
     * @return size integer value representing the number of nodes in the linked
     * list
     */
    public int size() {
        int count = 0;
        Node<Type> current = this.head;
        while (current != null) {
            count++;
            current = current.nextNode;
        }
        this.size = count;
        return this.size;
    }

    /**
     * Sorts the content of the list (based on its content) and return a sorted
     * version of th linked list. Merge sort used. Note: Content of list should
     * be integer
     *
     * @return singlyLinkedList sorted list
     */
    public MySinglyLinkedList<Type> sort() {
        if (this == null) {
            return null;
        } else {
            return sort(this, 0, size - 1);
        }
    }

    private MySinglyLinkedList<Type> sort(MySinglyLinkedList<Type> list, int start, int stop) {
        MySinglyLinkedList<Type> newList = new MySinglyLinkedList<>();

        if (stop == start) {
            newList.add(list.get(start));
            return newList;
        }
        
        int pivot = (int) Math.floor(((double) (start + stop) / 2));

        MySinglyLinkedList<Type> left = sort(list, start, pivot);
        MySinglyLinkedList<Type> right = sort(list, pivot + 1, stop);

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size || rightIndex < right.size) {
            if (leftIndex >= left.size) {
                newList.add(right.get(rightIndex));
                rightIndex++;
            } else if (rightIndex >= right.size) {
                newList.add(left.get(leftIndex));
                leftIndex++;
            } else if ((Integer) left.get(leftIndex) <= (Integer) right.get(rightIndex)) {
                newList.add(left.get(leftIndex));
                leftIndex++;
            } else {
                newList.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        return newList;
    }

    /**
     * Checks if list contains specified element
     *
     * @param content content to check for
     * @return true/false true if node with such content exists and false if
     * otherwise
     */
    public boolean contains(Type content) {
        if (head == null) {
            return false;
        }
        Node<Type> cursor = head;
        while (cursor != null) {
            if (cursor.content == content) {
                return true;
            }
            cursor = cursor.nextNode;
        }
        return false;
    }

    /**
     * Print out a sublist of the actual linked list
     *
     * @param start start index
     * @param end end index
     * @return sublist sublist from index start to end (if available)
     */
    public MySinglyLinkedList<Type> subList(int start, int end) {
        if (start < 0 || end >= size) {
            return null;
        } else if (head == null) {
            return null;
        }

        MySinglyLinkedList<Type> subList = new MySinglyLinkedList<>();
        Node<Type> cursor = head;
        int count = 0;
        while (cursor != null) {

            if (count >= start && count <= end) {
                subList.add(cursor.content);
            }
            if (count > end) {
                break;
            }
            cursor = cursor.nextNode;
            count++;
        }

        return subList;
    }

    @Override
    /**
     * Uses the value of the content of the head of the linked list to sort a
     * lists of linked lists. this comparator will only work if content of the
     * linked list is an integer
     */
    public int compareTo(Object obj) {
        MySinglyLinkedList<Type> list = (MySinglyLinkedList) obj;
        if (this.head.content.equals(list)) {
            return 0;
        } else if ((Integer) this.head.content < (Integer) list.head.content) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Comparator class to sort list of linked list. It uses the value of the
     * head of each of the list to determine sort. Note that this sort will not
     * work if content type is not an Integer
     */
    public class compareHeads implements Comparator<MySinglyLinkedList> {

        @Override
        public int compare(MySinglyLinkedList lhs, MySinglyLinkedList rhs) {
            if (lhs.head.equals(rhs.head)) {
                return 0;
            } else if ((Integer) lhs.head.content < (Integer) rhs.head.content) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /**
     * Main method to to test Singly Linked List implementation....uncomment to
     * run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        MySinglyLinkedList<Integer> linkedList = new MySinglyLinkedList<>();
        for (int i = 0; i < 7; i++) {
            linkedList.add(i * 7);
        }
        System.out.println("LinkedList Test: \n");
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Original Size: " + linkedList.size());
        System.out.println("---------------------------------------------");

        MySinglyLinkedList<Integer> clonedList = linkedList.cloneThis();
        System.out.println("Cloned Content: " + clonedList.thisToString());
        System.out.println("Cloned Size: " + clonedList.size());
        System.out.println("Edit clone copy by adding 11");
        clonedList.add(11);
        System.out.println("Edited cloned copy: " + clonedList.thisToString() + " vs Original: " + linkedList.thisToString());
        System.out.println("New cloned Size: " + clonedList.size() + " vs New original size: " + linkedList.size());
        System.out.println("---------------------------------------------");

        MySinglyLinkedList<Integer> deepCopiedList = linkedList.deepCopy();
        System.out.println("Deep Copied Content: " + deepCopiedList.thisToString());
        System.out.println("Deep Copied Size: " + deepCopiedList.size());
        System.out.println("Edit deep copy by adding 77");
        deepCopiedList.add(77);
        System.out.println("Edited deep copy: " + deepCopiedList.thisToString() + " vs Original: " + linkedList.thisToString());
        System.out.println("New deep copy Size: " + deepCopiedList.size() + " vs New original size: " + linkedList.size());
        System.out.println("---------------------------------------------");

        System.out.println("Add 44 to index 0 of original: ");
        linkedList.add(0, 44);
        System.out.println("Original Content: " + linkedList.thisToString());

        System.out.println("Add 33 to index 2 original: ");
        linkedList.add(2, 33);
        System.out.println("Original Content: " + linkedList.thisToString());

        System.out.println("Add 698 to index 700 of original: ");
        linkedList.add(700, 698);
        System.out.println("---------------------------------------------");

        System.out.println("AddFirst 131 to original: ");
        linkedList.addFirst(131);
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Original Size: " + linkedList.size());
        System.out.println("---------------------------------------------");

        System.out.println("Element original: " + linkedList.element());
        System.out.println("---------------------------------------------");

        System.out.println("Get content of index 0 of original: " + linkedList.get(0));
        System.out.println("Get content of index 4 of original: " + linkedList.get(4));
        System.out.println("Get content of index 10 of original: " + linkedList.get(10));
        System.out.println("Get content of index 598 of original: " + linkedList.get(598));
        System.out.println("---------------------------------------------");

        System.out.println("Add 87 to last of original: " + linkedList.addLast(87));
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Add 16 to last of original: " + linkedList.addLast(16));
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("---------------------------------------------");

        System.out.println("Add 44 to original: " + linkedList.add(44));
        System.out.println("Add 81 to original: " + linkedList.add(81));
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Index of 87 in original: " + linkedList.indexOf(87));
        System.out.println("Index of 33 in original: " + linkedList.indexOf(33));
        System.out.println("Index of 15 in original: " + linkedList.indexOf(15));
        System.out.println("Index of 44 in original: " + linkedList.indexOf(44));
        System.out.println("Last index of 44 in original: " + linkedList.lastIndexOf(44));
        System.out.println("---------------------------------------------");

        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Remove first element from original: " + linkedList.remove());
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Remove first element from original: " + linkedList.remove());
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Remove 16 from original: " + linkedList.remove((Integer) 16));
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Remove index 3 from original: " + linkedList.remove(3));
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Remove last from original: " + linkedList.removeLast());
        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("---------------------------------------------");

        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Does original contain 21: " + linkedList.contains(21));
        System.out.println("Does original contain 82: " + linkedList.contains(82));
        System.out.println("---------------------------------------------");

        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Convert original to array");
        System.out.println("Array content: " + Arrays.toString(linkedList.toArray()));
        System.out.println("---------------------------------------------");

        System.out.println("Original Content: " + linkedList.thisToString());
        System.out.println("Sublist of original from index 2 to index 5: " + linkedList.subList(2, 5).thisToString());
        System.out.println("---------------------------------------------");

        System.out.println("Sort a colection of linkedlist using the content of their head");
        MySinglyLinkedList<Integer> anotherLinkedList = new MySinglyLinkedList<>();
        anotherLinkedList.add(400);
        anotherLinkedList.add(6);
        anotherLinkedList.add(1020);
        for (int i = 0; i < 7; i++) {
            anotherLinkedList.add(i * 13);
        }
        ArrayList<MySinglyLinkedList> listOfLinkedLists = new ArrayList<>();
        listOfLinkedLists.add(anotherLinkedList); //Head has 400 in it
        System.out.println("First placed in the list: " + anotherLinkedList.thisToString());
        listOfLinkedLists.add(linkedList); //Head has 0 in it
        System.out.println("Second placed in the list: " + linkedList.thisToString());
        Collections.sort(listOfLinkedLists); //Collections.sort(listOfLinkedLists, linkedList.new compareHeads());
        System.out.println("AFTER SORT: ");
        System.out.println("First in the list: " + listOfLinkedLists.get(0).thisToString());
        System.out.println("Second in the list: " + listOfLinkedLists.get(1).thisToString());
        System.out.println("---------------------------------------------");
    }*/
}
