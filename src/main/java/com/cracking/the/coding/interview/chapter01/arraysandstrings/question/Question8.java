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
package com.cracking.the.coding.interview.chapter01.arraysandstrings.question;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <b>Zero Matrix:</b> Write an algorithm such that if an element in an MxN
 * matrix is 0, its entire row and column are set to 0.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question8 {

    /**
     * Keep track of columns and rows with zeros, switch all their previous
     * indexes to zeros immediately and continue with the iteration. Once those
     * rows/columns are encountered again, turn (just that spot) to zero
     * immediately. As more spots are encountered, keep turning them to zeros
     *
     * @param matrix matrix to make (some of its columns,rows) zeros
     * @param M m dimension
     * @param N n dimension
     * @return matrix final result
     */
    public static int[][] solve(int[][] matrix, int M, int N) {
        ArrayList columsForZero = new ArrayList();
        ArrayList rowsForZero = new ArrayList();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0 && !(rowsForZero.contains(i) || columsForZero.contains(j))) {
                    rowsForZero.add(i);
                    columsForZero.add(j);
                    matrix = setPreviousToZero(matrix, i, j);
                } else if (rowsForZero.contains(i) || columsForZero.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /**
     * Use bits to keep track of the presence of zeros rather than an array list i.e if size of matrix will not be greater than 32x32
     *
     * @param matrix matrix to make (some of its columns,rows) zeros
     * @param M m dimension
     * @param N n dimension
     * @return matrix final result
     */
    public static int[][] solve2(int[][] matrix, int M, int N) {
        int rowsWithZeros = 0x00000000;
        int columnsWithZeros = 0x00000000;
        int xCheck=0;
        int yCheck=0;
        for (int i = 0; i < M; i++) {
            
            for (int j = 0; j < N; j++) {
                xCheck = rowsWithZeros & (1<<i);
                yCheck = columnsWithZeros & (1<<j);
                if ((matrix[i][j] == 0) && (xCheck==0 || yCheck==0)) {
                   rowsWithZeros =  rowsWithZeros | (1<<i);
                   columnsWithZeros =  columnsWithZeros | (1<<j);
                   System.out.println(rowsWithZeros);
                   System.out.println(columnsWithZeros);
                    matrix = setPreviousToZero(matrix, i, j);
                } else if (xCheck>0 || yCheck>0) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }
    
    /**
     * Set previous rows (positionX) and columns (positionY) of the matrix to zero 
     * @param matrix matrix to be zeroed
     * @param positionX row to zero
     * @param positionY column to zero
     * @return matrix zeroed matrix
     */
    public static int[][] setPreviousToZero(int[][] matrix, int positionX, int positionY) {
        for (int i = 0; i <= positionX; i++) {
            matrix[i][positionY] = 0;
        }
        for (int j = 0; j < positionY; j++) {
            matrix[positionX][j] = 0;
        }
        return matrix;
    }

    /**
     * Prints out a 2D array
     *
     * @param toPrint array to print
     */
    public static void print2D(int[][] toPrint) {
        if (toPrint == null) {
            return;
        }
        for (int i = 0; i < toPrint[0].length; i++) {
            System.out.println(Arrays.toString(toPrint[i]));
        }
    }
    
     /**
     * Question_8 main method....uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        
        int answer[][];
        int[][] image = {{2, 4, 8, 6, 9}, {9, 4, 2, 7, 9}, {9, 0, 3, 8, 9}, {6, 8, 3, 1, 9}, {3, 1, 6, 9, 8}};
        int[][] image2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] image3 = {{3, 6, 9, 9, 2}, {1, 8, 0, 4, 4}, {6, 3, 3, 2, 8}, {9, 1, 8, 7, 6}, {8, 0, 0, 0, 9}};

        System.out.println("THE MATRIX:");
        print2D(image);
        System.out.println("HAS BEEN ZEROD TO: ");
        answer = solve2(image, 5, 5);
        print2D(answer);
        
    }*/
}
