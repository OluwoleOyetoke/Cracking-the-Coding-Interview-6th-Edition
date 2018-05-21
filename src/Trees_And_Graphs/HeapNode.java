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
 *<b>Bst Node Class</b>
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 */
public class HeapNode implements Comparable {
    
      int value;
        HeapNode left;
        HeapNode right;
        HeapNode ancestor;
        int weight=0;

        HeapNode(int value) {
            this.value = value;
            left = null;
            right = null;
            ancestor = null;
            weight =0;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public HeapNode getLeft() {
            return left;
        }

        public void setLeft(HeapNode left) {
            this.left = left;
        }

        public HeapNode getRight() {
            return right;
        }

        public void setRight(HeapNode right) {
            this.right = right;
        }

        public HeapNode getAncestor() {
            return ancestor;
        }

        public void setAncestor(HeapNode ancestor) {
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
            HeapNode toCompare = (HeapNode) obj;
            if (this.value == toCompare.value) {
                return 0;
            } else {
                return this.value - toCompare.value;
            }
        }

}
