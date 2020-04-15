package com.cracking.the.coding.interview.chapter02.linkedlist.datastructure;

/**
 * Node object blueprint definition
 */
public class Node<Type> implements Comparable<Type> {

    public Node<Type> nextNode = null;
    public Type content = null;

    /**
     * Default constructor
     */
    Node() {
        content = null;
        nextNode = null;
    }

    /**
     * Constructor 2
     *
     * @param content content to be placed in the node
     */
    Node(Type content) {
        this.content = content;
        nextNode = null;
    }

    /**
     * Get node content
     *
     * @return content content in node
     */
    public Type getContent() {
        return content;
    }

    /**
     * Set node content
     *
     * @param content content in node
     */
    public void setContent(Type content) {
        this.content = content;
    }

    /**
     * Get current node's next node
     *
     * @return nextNode next node
     */
    public Node<Type> getNextNode() {
        return nextNode;
    }

    /**
     * Set current node's next node
     *
     * @param nextNode next node
     */
    public void setNextNode(Node<Type> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Check if current node is empty
     *
     * @return true/false returns true if node is empty
     */
    public boolean isEmpty() {
        return content == null;
    }


    /**
     * Comparator for the Node class. Assumes content of the Node is an
     * integer
     */
    @Override
    public int compareTo(Object obj) {
        Node<Type> node = (Node<Type>) obj;
        if (this.content == node.content) {
            return 0;
        } else if ((Integer) this.content < (Integer) node.content) {
            return -1;
        } else {
            return 1;
        }
    }
}
