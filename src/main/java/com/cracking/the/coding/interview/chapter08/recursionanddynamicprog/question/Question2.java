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
package com.cracking.the.coding.interview.chapter08.recursionanddynamicprog.question;

/**
 * <b>Robot in a Grid:</b> Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question2 {

    //1 = unreachable spot in the grid
    int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0}};

    int[][] grid2 = {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 1, 1},
        {0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0}};
    
    /**
     * Check if position x,y exists and is reachable on the grid
     * @param x x
     * @param y y
     * @param grid grid
     * @return true/false true if exist and is reachable. False if otherwise
     */
    public boolean isReachable(int x, int y, int[][] grid) {
        if (x > grid.length - 1 || y > grid[0].length - 1) {
            return false;
        } else if (grid[x][y] == 1) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Recursively find route from position top left corner of grid to down right corner
     * @param x x
     * @param y y
     * @param route route tracker 
     */
    public void findRoute(int x, int y, String route) {
        if(grid==null || grid[0][0]==1){
            return;
        }
        if(x==grid.length-1 && y==grid[0].length-1){
            System.out.println(route);
            return;
        }
        
        
        //move right
        if (isReachable(x, y + 1, grid)) {
            int newY = y + 1;
            findRoute(x, newY, route + " --> [" + x + "," + newY + "]");
        }
        //move down
        if (isReachable(x + 1, y, grid)) {
            int newX = x + 1;
            findRoute(newX, y, route + " --> [" + newX + "," + y + "]");
        }

    }
    
     /**
     * Recursion/Dynamic Programming question 2 main method....uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        Question_2 q2 = new Question_2();
        q2.findRoute(0, 0, "[0,0]");
    }*/
}
