/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

/**
 * <b>Find Unique Islands</b>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Islands {

    int[][] graph = {{1, 1, 1, 0, 0},
    {1, 1, 1, 0, 1},
    {1, 1, 1, 0, 0},
    {0, 0, 0, 0, 1},
    {0, 1, 0, 1, 0}};

    int[][] visited = new int[graph.length][graph[0].length];

    /**
     * Checks if the selected index is safe or not
     *
     * @param x x coordinate to check
     * @param y y coordinate to check
     * @return true/false true if safe and false if otherwise
     */
    public boolean isSafeIndex(int x, int y) {
        if (x < 0 || x >= graph.length || y < 0 || y >= graph[0].length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check connected neighbours of x,y and mark them as visisted
     *
     * @param x x coordinate to start from
     * @param y y cooordinate to start from
     */
    public void checkConnectedNeig(int x, int y) {
        int a = 0;
        int b = 0;
        int[][] sideChecker = {{x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}, {x - 1, y}, {x + 1, y}, {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}};

        for (int i = 0; i < sideChecker.length; i++) {
            a = sideChecker[i][0];
            b = sideChecker[i][1];

            if (isSafeIndex(a, b)) {
                if (visited[a][b] == 0) {
                    visited[a][b] = 1;
                    if (graph[a][b] == 1) {
                        checkConnectedNeig(a, b);
                    }
                }
            }
        }
    }

    /**
     * Runs a kind of BFS on the graph to find number of connected components
     *
     */
    public void numberOfIslands() {
        if (graph == null) {
            return;
        }
        int islandCount = 0;
        //check all neighbours
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (visited[i][j] == 0) {
                    visited[i][j] = 1;
                    if (graph[i][j] == 1) {
                        checkConnectedNeig(i, j);
                        islandCount++;
                    }
                }
            }
        }
        System.out.println("Number of Islands is: " + islandCount);
    }

    /**
     * Find minimum in surrounding island
     *
     * @param auxGraph modified graph
     * @param x x coordinate to start from
     * @param y y coordinate to start from
     * @return minimum minimum value in auxiliary graph
     */
    public int findMin(int[][] auxGraph, int x, int y) {
        int[][] neighToCheck = {{x - 1, y - 1}, {x, y - 1}, {x - 1, y}};
        int a = 0;
        int b = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < neighToCheck.length; i++) {
            a = neighToCheck[i][0];
            b = neighToCheck[i][1];
            if (isSafeIndex(a, b)) {
                if (auxGraph[a][b] <= min) {
                    min = auxGraph[a][b];
                }
            }
        }

        return min;
    }

    /**
     * Find largest square island in graph
     */
    public void largestSquareIsland() {
        if (graph == null) {
            return;
        }
        if (graph.length < 2) {
            return;
        }

        //Make auxiliary graph and copy first row of graph into it
        int[][] auxGraph = new int[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            auxGraph[0][i] = graph[0][i];
        }

        //move through graph and track squar length (if any)
        int min = 0;
        for (int i = 1; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {

                if (j == 0) {//edge values remain the same
                    auxGraph[i][j] = graph[i][j];
                    continue;
                }
                if (graph[i][j] == 1) {
                    min = findMin(auxGraph, i, j);
                    auxGraph[i][j] = min + 1;
                } else {
                    auxGraph[i][j] = 0;
                }
            }
        }
        int largest = Integer.MIN_VALUE;
        for (int i = 1; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (auxGraph[i][j] > largest) {
                    largest = auxGraph[i][j];
                }
            }
        }
        System.out.println("Size of Largest Square Island is: " + largest + " by " + largest);
        //printMatrix(auxGraph);

    }

    /**
     * Print out matrix normally
     *
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
     * Main method...uncomment to run
     *
     * @param args command line arguments
 *//*
    public static void main(String[] args) {
        Islands islands = new Islands();
        islands.numberOfIslands();
        islands.largestSquareIsland();
    }
*/
}
