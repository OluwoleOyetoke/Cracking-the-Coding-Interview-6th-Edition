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
package Others;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class LruCache {

    class Node {

        Node left;
        Node right;
        String content;
        String key;

        Node(String con) {
            content = con;
        }
    }

    int CACHE_SIZE;
    HashMap<String, Node> lookupMap = new HashMap<>();
    Queue<Node> cache = new LinkedList<>();
    Node head;
    Node tail;

    LruCache(int size) {
        CACHE_SIZE = size;
    }

    public Node UseNode(String key) {
        if (lookupMap.containsKey(key)) {
            Node node = lookupMap.get(key);
            removeNode(node);
            addToTop(node);
            return node;
        } else {
            return null;
        }
    }

    public void putNode(String key, String value) {
        if (lookupMap.containsKey(key)) {
            Node present = lookupMap.get(value);
            present.content = value;
            removeNode(present);
            addToTop(present);
        } else {
            Node newNode = new Node(value);
            newNode.key = key;
            if (cache.size() >= CACHE_SIZE) {
                lookupMap.remove(tail.key);
                removeNode(tail);
                addToTop(newNode);
            } else {
                addToTop(newNode);
            }
        }

    }

    public void removeNode(Node node) {
        if (node.left != null) {
            node.left.right = node.right;
        } else {
            head = node.right;
        }
        if (node.right != null) {
            node.right.left = node.left;
        } else {
            tail = node.left;
        }
    }

    public void addToTop(Node node) {
        node.right = head;
        node.left = null;
        if (head != null) {
            head.left = node;
            head = node;
        }
        if (tail == null) {
            tail = head;
        }
    }
}
