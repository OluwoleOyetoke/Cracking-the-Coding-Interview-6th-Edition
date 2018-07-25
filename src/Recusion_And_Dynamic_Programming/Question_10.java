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
package Recusion_And_Dynamic_Programming;

/**
 * <b>Paint Fill:</b> Implement the "paint fill" function that one might see on
 * many image editing programs. That is, given a screen (represented by a
 * two-dimensional array of colors), a point, and a new color, fill in the
 * surrounding area until the color changes from the original color.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_10 {

    private int[][] picture;
    private int[][] visited;
    private int picLength;

    Question_10(int[][] picture) {
        this.picture = picture;
        this.visited = new int[picture.length][picture[0].length];
        this.picLength = picture.length;
    }

    /**
     * Fill picture and print
     * @param x x
     * @param y y
     */
    public void fillAndPrint(int x, int y) {
        System.out.println("BEFORE FILL");
        printMatrix(picture);
        fill(x, y);
        System.out.println("AFTER FILL");
        printMatrix(picture);
    }

    /**
     * Use breadth first search to increasingly fill the picture
     *
     * @param x x
     * @param y y
     */
    private void fill(int x, int y) {
        //neighbour indexes
        int[][] neighIndexes = {
            {x, y - 1}, {x, y + 1},
            {x - 1, y}, {x - 1, y - 1}, {x - 1, y + 1},
            {x + 1, y}, {x + 1, y - 1}, {x + 1, y + 1}};

        //fill spot with 1
        picture[x][y] = 1;

        //fill neighbours with 1
        int newX = 0;
        int newY = 0;
        boolean safe = false;
        for (int i = 0; i < neighIndexes.length; i++) {
            newX = neighIndexes[i][0];
            newY = neighIndexes[i][1];
            safe = isSafe(newX, newY);
            if (safe && visited[newX][newY] != 1 && picture[newX][newY] != 1) {
                visited[newX][newY] = 1;
                fill(newX, newY);
            }
        }
    }
    
    /**
     * Check if index is safe
     * @param x index location x
     * @param y index location y
     * @return true/false true if safe and false if otherwise
     */
    private boolean isSafe(int x, int y) {
        if (x < 0 || y < 0 || x > (picLength - 1) || y > (picLength - 1)) {
            return false;
        }
        return true;
    }

    /**
     * Print out matrix normally
     *
     * @param matrix matrix to print
     */
    private void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Null matrix received by printMatrix");
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("");
            for (int j = 0; j < matrix[0].length; j++) {
                if (j < matrix[0].length - 1) {
                    System.out.print(matrix[i][j] + ",");
                } else {
                    System.out.print(matrix[i][j]);
                }
            }
        }
        System.out.println("\n");
    }

    /**
     * Recursion and Dynamic Programming Question 11 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        int[][] picture = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1}};

        Question_10 q10 = new Question_10(picture);
        q10.fillAndPrint(4, 4);
    }*/
}
