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

import java.util.LinkedList;
import java.util.Queue;

/**
 * <b>Route Between Nodes:</b> Given a directed graph, design an algorithm to
 * find out whether there is a route between two nodes.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @} gmail.com{@literal >}
 */
public class Question_1 {

    /**
     * Adjacency list
     */
    int[][] adjacencyList = {
        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};

    /**
     * Used to track visited nodes in the graph
     */
    int[][] visited = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
    
    boolean exist = false;
    /**
     * Using breadth first search
     *
     * @param start start vertice
     * @param stop stop vertice
     * @param verticeQueue vertice queue
     * @param prefixQueue prefix queue
     * @param prefix prefix string
     */
    public void bfs(int start, int stop, Queue<Integer> verticeQueue, Queue<String> prefixQueue, String prefix) {
        if (start == stop) {
            System.out.println(prefix);
            exist=true;
            return;
        }

        int found;

        for (int j = 0; j < adjacencyList[0].length; j++) {

            found = adjacencyList[start][j];
            if (visited[start][j] == 1) {
                return;
            }

            visited[start][j] = 1;
            char label = (char) (j + 'a');

            if (found == 1 && j == stop) {
                System.out.println(prefix + ", " + label);
                exist=true;
            } else if (found == 1) {
                verticeQueue.add(j);
                prefixQueue.add(prefix + ", " + label);
            }
        }

        String tempString = "";
        int tempInt = 0;
        while (!verticeQueue.isEmpty()) {
            tempInt = verticeQueue.poll();
            tempString = prefixQueue.poll();
            bfs(tempInt, stop, verticeQueue, prefixQueue, tempString);
        }
    }

    /**
     * Using depth first search
     *
     * @param start start vertice
     * @param stop stop vertice
     * @param prefix prefix string
     */
    public void dfs(int start, int stop, String prefix) {
        if (start == stop) {
            System.out.println(prefix);
            exist=true;
        }
        int found;

        for (int j = 0; j < adjacencyList[0].length; j++) {
            if (visited[start][j] == 0) {
                visited[start][j] = 1;
                char label = (char) (j + 'a');
                found = adjacencyList[start][j];

                if (found == 1 && j == stop) {
                    prefix = prefix + ", " + label;
                    System.out.println(prefix);
                    exist=true;
                    return;
                } else if (found == 1) {
                    dfs(j, stop, prefix + ", " + label);
                }
            }
        }
    }

    /**
     * Main method for trees and graph question 1
     *
     * @param args Command line arguments
     *//*
    public static void main(String[] args) {

        //Use depth first search
        Question_1 test = new Question_1();
        test.dfs(6, 5, "" + (char) (6 + 'a'));
        if(test.exist==true){
            System.out.println("Route exists");
        }

        //use breadth first search
        Question_1 test2 = new Question_1();
        Queue verticeQueue = new LinkedList();
        Queue prefixQueue = new LinkedList();
        test2.bfs(6, 5, verticeQueue, prefixQueue, "" + (char) (6 + 'a'));
         if(test2.exist==true){
            System.out.println("Route exists");
        }  
    }*/
}
