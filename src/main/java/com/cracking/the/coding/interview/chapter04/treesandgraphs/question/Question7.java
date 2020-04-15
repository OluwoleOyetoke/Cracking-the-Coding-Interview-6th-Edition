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
package com.cracking.the.coding.interview.chapter04.treesandgraphs.question;

import java.util.ArrayList;

/**
 * Build Order: You are given a list of projects and a list of dependencies
 * (which is a list of pairs of projects, where the second project is dependent
 * on the first project). All of a project's dependencies must be built before
 * the project is. Find a build order that will allow the projects to be built.
 * If there is no valid build order, return an error. EXAMPLE Input: projects:
 * a, b, c, d, e, f dependencies: (a, d), (f, b), (b, d), (f, a), (d, c) Output:
 * f, e, a, b, d, c
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question7 {

    /**
     * Uses topological sort to sort the build order
     *
     * @param dependency     dependencies
     * @param numberOfBuilds number of builds with/without dependency
     */
    public void solve(char[][] dependency, int numberOfBuilds) {
        //make adjaceny matrix
        int[][] adjacency = new int[numberOfBuilds][numberOfBuilds];
        int x = 0;
        int y = 0;
        for (int i = 0; i < dependency.length; i++) {
            x = ((int) dependency[i][0]) - 97;
            y = ((int) dependency[i][1]) - 97;
            adjacency[x][y] = 1;
        }
        //topological sort
        ArrayList executed = new ArrayList();
        boolean check = false;

        //look through the columns to find node with no dependency
        for (int i = 0; i < numberOfBuilds; i++) {

            check = true;
            //stop if executed = number of builds in adjacency list
            if (executed.size() == numberOfBuilds) {
                break;
            }
            if (executed.contains(i)) {
                continue;
            }

            for (int j = 0; j < numberOfBuilds; j++) {
                if (adjacency[j][i] == 1) {
                    check = false;
                    break;
                }
            }

            //column without dependency found
            if (check == true) {
                //remove
                executed.add(i);
                //update any other node it was pointing to
                for (int k = 0; k < numberOfBuilds; k++) {
                    if (adjacency[i][k] == 1) {
                        adjacency[i][k] = 0;
                    }
                }
                //start search again
                i = -1;
            }
        }

        //print out execution order
        System.out.print("Build order: ");
        for (int i = 0; i < executed.size(); i++) {
            int in = (int) executed.get(i);
            char read = (char) (in + 'a');
            if (i == executed.size() - 1) {
                System.out.print(read + "\n");
            } else {
                System.out.print(read + ", ");
            }
        }

    }

    /**
     * Trees and graphs question_7 main method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        char[][] dependency = {
            {'a', 'd'},
            {'f', 'b'},
            {'b', 'd'},
            {'f', 'a'},
            {'d', 'c'}
        };
        int numberOfBuilds = 6;
        Question_7 q7 = new Question_7();
        q7.solve(dependency, numberOfBuilds);
    }*/
}
