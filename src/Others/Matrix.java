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

import java.util.Arrays;

/**
 * <b>Matrix Operations</b>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Matrix {

    /**
     * Rotates an nxn matrix (90 degrees clockwise, in place)
     *
     * @param matrix matrix to rotate
     * @return rotatedMAtrix rotated matrix
     */
    public int[][] rotateMatrixNxN(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Null matrix received by rotateMatrix");
            return null;
        }
        if (matrix.length != matrix[0].length) {
            System.out.println("Matrix has to be N by N");
            return null;
        }
        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 0;
        int temp4 = 0;

        int stopY = (int) Math.ceil(matrix.length / 2);
        int startY = 0;
        int startX = 0;
        int stopX = 0;
        int counter = 0;

        for (int y = 0; y < stopY; y++) {
            counter = 0;
            startX = y;
            stopX = matrix.length - startX - 1;
            /* * */
            for (int x = startX; x < stopX; x++) {
                temp1 = matrix[y][startX + counter];
                temp2 = matrix[y + counter][stopX];
                temp3 = matrix[stopX][stopX - counter];
                temp4 = matrix[stopX - counter][startX];

                matrix[y][startX + counter] = temp4;
                matrix[y + counter][stopX] = temp1;
                matrix[stopX][stopX - counter] = temp2;
                matrix[stopX - counter][startX] = temp3;

                counter++;
            }

        }
        return matrix;
    }
    
    /**
     * Print out matrix normally
     * @param matrix matrix to print
     */
    public void printMatrix(int[][] matrix) {
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
     * Print matrix diagonally
     * @param matrix matrix to print
     */
     public void diagonalPrint(int[][] matrix) {
        if (matrix == null) {
            return;
        }
          //System.out.println("\n");
        int x = 0;
        int y = 0;
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("");
            x = 0;
            y = i;
            System.out.print(matrix[x][y] + ", ");
            for (int k = 0; k < i; k++) {
                x = x + 1;
                y = y - 1;
                System.out.print(matrix[x][y] + ", ");
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            System.out.println("");
            x = j;
            y = matrix[0].length-1;
            System.out.print(matrix[x][y] + ", ");
            for (int k = 0; k < matrix[0].length-j-1; k++) {
                x = x + 1;
                y = y - 1;
                System.out.print(matrix[x][y] + ", ");
            }
        }
          System.out.println("\n");
    }


    /**
     * Print matrix in spiral order
     * @param matrix matrix to print
     */
    public void printInSpiral(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Null matrix received by printInSpiral");
            return;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            System.out.println(Arrays.toString(matrix[0]));
        }

        int width = matrix[0].length;
        int height = matrix.length;

        int lengthA = width-1;
        int lengthB = height - 2;
        int lengthC = width - 2;
        int lengthD = height - 3;
        int start=0, stop=0,same=0,round=0,cnt=0, lastStop = 0, count=0, direction=0;
        while (cnt != (width * height)) {
            
            switch (direction) {
                case 0:
                    start = round;
                    stop = start+lengthA;
                    same = round;
                    for (int i = start; i <= stop; i++) {
                        System.out.print(matrix[same][i]+", ");
                        count++;
                    } 
                    lastStop = stop; 
                    lengthA = lengthA-2;
                    direction=1;
                    break;
                case 1:
                    start = round+1;
                    stop = start+lengthB;
                    same = lastStop;
                    for (int i = start; i <= stop; i++) {
                              System.out.print(matrix[i][same]+", ");
                              count++;
                    }  
                    lastStop = stop;
                    lengthB = lengthB-2;
                    direction=2;
                    break;
                case 2:
                    start = width-round-2;
                    stop = start-lengthC;
                    same = lastStop;
                    for (int i = start; i >= stop; i--) {
                          System.out.print(matrix[same][i]+", ");
                          count++;
                    }
                    lastStop = stop;
                    lengthC = lengthC-2;
                    direction=3;
                    break;
                case 3:
                    start = height-round-2;
                    stop = start-lengthD;
                    same = lastStop;
                    for (int i = start; i >= stop; i--) {
                        System.out.print(matrix[i][same]+", ");
                        count++;   
                    }  
                    lengthD = lengthD-2;
                    direction=0;
                    break;
                default:
                    count++;
                    break;
            }
            cnt++;
            if(cnt%4==0){
              round++;   
            }
        }
    }
    
    /**
     * Main Method....uncomment to run
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 5},
        {6, 7, 8, 9, 10},
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25}};
        Matrix mat = new Matrix();
        System.out.println("Initial Matrix");
        mat.printMatrix(matrix);
            System.out.println("Diagonal Print\n");
        mat.diagonalPrint(matrix);
          System.out.println("Spiral Print\n");
        mat.printInSpiral(matrix);
        //int[][] x = {{2,5}, {6,7}};
        int[][] rotated = mat.rotateMatrixNxN(matrix);
        System.out.println("Rotated Matrix");
        mat.printMatrix(rotated);
    }
*/
}
