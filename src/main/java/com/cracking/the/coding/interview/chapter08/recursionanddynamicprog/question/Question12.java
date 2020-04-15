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

import java.util.ArrayList;

/**
 * <b>Eight Queens</b>:Write an algorithm to print all ways of arranging eight
 * queens on an 8x8 chess board so that none of them share the same row, column,
 * or diagonal. In this case, "diagonal" means all diagonals, not just the two
 * that bisect the board.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question12 {

    int GRID_SIZE = 8;
    int count = 0;

    /**
     * Runs the eight queen algorithm
     */
    public void eightQueens() {
        place(0, new ArrayList<>(), new ArrayList<>(), "");
        System.out.println(count + " Ways");
    }
    
    /**
     * Recursively check for suitable place to place queen
     * @param row current trow
     * @param rowsUsed list of already used rows
     * @param columnsUsed list of already used columns
     * @param tracker to note down the selected indexes for queen placement
     */
    private void place(int row, ArrayList<Integer> rowsUsed, ArrayList<Integer> columnsUsed, String tracker) {
        if (row > 7) {
            if (columnsUsed.size() == GRID_SIZE) {
                System.out.println(tracker);
                count++;
                return;
            }
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            if (isValid(row, i, rowsUsed, columnsUsed)) {
                ArrayList<Integer> newRows = copyList(rowsUsed);
                ArrayList<Integer> newColumns = copyList(columnsUsed);
                newRows.add(row);
                newColumns.add(i);
                place(row + 1, newRows, newColumns, tracker + "{" + row + ", " + i + "}, ");
            }
        }
    }
    
    /**
     * Check if it is okay to place queen in current spot. 
     * @param row current row
     * @param column current column
     * @param rowsUsed already used rows
     * @param columnsUsed already used columns
     * @return true/false true if valid and false if not valid
     */
    private boolean isValid(int row, int column, ArrayList<Integer> rowsUsed, ArrayList<Integer> columnsUsed) {
        //cehck if in same column
        if (columnsUsed.contains(column)) {
            return false;
        }

        int testRow;
        int testColumn;
        int columnDistance;
        int rowDistance;
        /* Check diagonals: if the distance between the columns equals the distance
         between the rows, then they're in the same diagonal. */
        for (int i = 0; i < rowsUsed.size(); i++) {
            testRow = rowsUsed.get(i);
            testColumn = columnsUsed.get(i);
            columnDistance = Math.abs(testColumn - column);
            rowDistance = row - testRow;
            if (rowDistance == columnDistance) {
                //System.out.println(testRow+","+testColumn+" is diagonal to "+row+","+column);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Perform deep copy of an array list
     * @param list array list
     * @return newList new array list
     */
    private ArrayList<Integer> copyList(ArrayList<Integer> list){
        ArrayList<Integer> newList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            newList.add(list.get(i));
        }
        return newList;
    }
    
    /**
     * Recursion and Dynamic Programming Question 12 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_12 q12 = new Question_12();
        q12.eightQueens();
    }*/
}
