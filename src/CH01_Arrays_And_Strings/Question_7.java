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
package CH01_Arrays_And_Strings;

import java.util.Arrays;

/**
 * <b>Rotate Matrix:</b> Given an image represented by an NxN matrix, where each
 * pixel in the image is 4 bytes, write a method to rotate the image by 90
 * degrees. Can you do this in place?.....Page 91
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_7 {

    /**
     * Rotate using 2 for loops and a new image matrix...Not the best as it
     * makes use of another matrix array. No need to place much emphasis on the
     * 4 bytes mentioned in the question. After all, an int itself is 4 bytes
     *
     * @param image matrix to rotate
     * @param N matrix dimension NxN
     * @param direction 0=clockwise, 1=anticlockwise
     * @return newImage rotated image
     */
    public static int[][] solve(int[][] image, int N, int direction) {
        if (image == null) {
            return null;
        }
        int xTracker = 0;
        int[][] newImage = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (direction == 0) {
                    newImage[j][N - i - 1] = image[i][j];
                } else {
                    newImage[i][j] = image[j][N - i - 1];
                }
            }
        }
        return newImage;
    }

    /**
     * Rotate matrix without using a new array (in-place rotation). No need to
     * place much emphasis on the 4 bytes mentioned in the question. After all,
     * an int itself is 4 bytes. The main thing required is to be able to rotate
     * the matrix with 0(1) space.
     *
     * @param image matrix to rotate
     * @param N matrix dimension NxN
     * @param direction 0=clockwise, 1=anticlockwise
     * @return newImage rotated image
     */
    public static int[][] solve2(int[][] image, int N, int direction) {
        if (image == null) {
            return null;
        }
        int[][] newImage = new int[N][N];
        int holder1, holder2, holder3, holder4;
        for (int i = 0; i < (int) Math.ceil(N / 2); i++) {

            for (int j = i; j < N - i - 1; j++) {
                holder1 = image[i][j];
                holder2 = image[j][N - i - 1];
                holder3 = image[N - i - 1][N - j - 1];
                holder4 = image[N - j - 1][i];

                if (direction == 0) {
                    image[j][N - i - 1] = holder1;
                    image[N - i - 1][N - j - 1] = holder2;
                    image[N - j - 1][i] = holder3;
                    image[i][j] = holder4;
                } else {
                    image[j][N - i - 1] = holder3;
                    image[N - i - 1][N - j - 1] = holder4;
                    image[N - j - 1][i] = holder1;
                    image[i][j] = holder2;
                }
            }
        }
        return image;
    }
    
    /**
     * Prints out a 2D array
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
     * Question_7 main method....uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        int answer[][];
        int[][] image = {{2, 4, 8, 6, 9}, {9, 4, 2, 7, 9}, {9, 0, 3, 8, 9}, {6, 8, 3, 1, 9}, {3, 1, 6, 9, 8}};
        int[][] image2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] image3 = {{3, 6, 9, 9, 2}, {1, 8, 0, 4, 4}, {6, 3, 3, 2, 8}, {9, 1, 8, 7, 6}, {8, 0, 0, 0, 9}};
        
        System.out.println("THE IMAGE:");
        print2D(image);
        System.out.println("IS ROTATED CLOCKWISE TO: ");
        answer = solve(image, 5, 0);
        print2D(answer);
        System.out.println("AND ANTICLOCKWISE TO: ");
        answer = solve(image, 5, 1);
        print2D(answer);
        System.out.println("\n-------------------------------\n");
        System.out.println("THE IMAGE:");
        print2D(image2);
        System.out.println("IS ROTATED CLOCKWISE TO: ");
        answer = solve(image2, 4, 0);
        print2D(answer);
        System.out.println("AND ANTICLOCKWISE TO: ");
        answer = solve(image2, 4, 1);
        print2D(answer);
    }*/
}
