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

import java.util.ArrayList;

/**
 * <b>Longest Common Subsequence</b> Find the longest common subsequence between
 * two strings
 *
 * @author Oluwole Oyetoke oluwoleoyetoke@gmail.com
 */
public class Subsequence {

    /**
     * Find longest common subsequence between in1 and in2. Note, this is not
     * the best approach. The dynamic programming appraoch is the fastest and
     * least computationally intensive.
     *
     * @param in1 input string 1
     * @param in2 input string 2
     * @return longest longest common sequence 
     */
    public String naiveLongestCommonSubSequence(String in1, String in2) {
        ArrayList<String> commonStore = new ArrayList<>();
        findCommonSubsequence(in1, in2, commonStore);
        int max = 0;
        String maxStr = "";
        for (int i = 0; i < commonStore.size(); i++) {
            if (commonStore.get(i).length() >= max) {
                max = commonStore.get(i).length();
                maxStr = commonStore.get(i);
            }
        }
        return maxStr;
    }

    /**
     * Using dynamic programing to solve longest common subsequence
     *
     * @param in1 input string 1
     * @param in2 in[ut string 2
     * @return lcs longest common subsequence
     */
    public String dynamicLCS(String in1, String in2) {
        int[][] platter = new int[in1.length() + 1][in2.length() + 1];
        int max = 0;
        int maxI = 0;
        int maxJ = 0;
        for (int i = 0; i < in1.length(); i++) {
            for (int j = 0; j < in2.length(); j++) {
                if (in1.charAt(i) == in2.charAt(j)) {
                    platter[i + 1][j + 1] = platter[i][j] + 1;
                    if (platter[i + 1][j + 1] > max) {
                        max = platter[i + 1][j + 1];
                        maxI = i + 1;
                        maxJ = j + 1;
                    }
                } else {
                    platter[i + 1][j + 1] = Integer.max(platter[i + 1][j], platter[i][j + 1]);
                }
            }
        }
        String longestSubSeq = traceRoute(platter, maxI, maxJ, in1, in2);
        return longestSubSeq;
    }

    /**
     *
     * @param platter work space
     * @param posI psotion x to start from
     * @param posJ position y to start from
     * @param in1 input string 1
     * @param in2 in[ut string 2
     * @return lcs longest common sunsequence string
     */
    private String traceRoute(int[][] platter, int posI, int posJ, String in1, String in2) {
        StringBuilder route = new StringBuilder();
        while (posI >= 1 && posJ >= 1) {
            if (platter[posI][posJ] > platter[posI - 1][posJ] && platter[posI][posJ] > platter[posI][posJ - 1]) {
                route.append(in2.charAt(posJ - 1));
                posJ = posJ - 1;
                posI = posI - 1;
            } else if (platter[posI - 1][posJ] >= platter[posI][posJ] && platter[posI][posJ - 1] < platter[posI][posJ]) {
                posJ = posJ;
                posI = posI - 1;
            } else if (platter[posI - 1][posJ] < platter[posI][posJ] && platter[posI][posJ - 1] >= platter[posI][posJ]) {
                posJ = posJ - 1;
                posI = posI;
            } else if (platter[posI - 1][posJ] == platter[posI][posJ - 1]) {
                route.append(in2.charAt(posJ - 1));
                break;
            }
        }

        return route.reverse().toString();
    }

    /**
     * Finds all common subsequence between in1 and in2 using a recursive
     * approach. Note, this is not the best method
     *
     * @param in1 input string 1
     * @param in2 input string 2
     * @param commonStore saves common subsequences here
     */
    public void findCommonSubsequence(String in1, String in2, ArrayList<String> commonStore) {
        int maxLength = Integer.max(in1.length(), in2.length());
        int index1 = 0;
        int index2 = 0;
        String sub = "";
        String common = "";

        while (index1 < in1.length()) {
            if (in1.charAt(index1) == in2.charAt(index2)) {
                common = common + " " + in1.charAt(index1);
                index1++;
                index2++;
            } else {
                index2++;
            }
            if (index2 >= in2.length()) {
                break;
            }
        }

        if (!commonStore.contains("\n" + common) && !common.equals("")) {
            //System.out.println(common);
            commonStore.add("\n" + common);
        }

        for (int i = 0; i < in1.length(); i++) {
            sub = in1.substring(0, i) + "" + in1.substring(i + 1, in1.length());
            findCommonSubsequence(sub, in2, commonStore);
        }
    }

    /**
     * Main/Test method
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        Subsequence sub = new Subsequence();
        String in1 = "abcdaf";
        String in2 = "acbcf";
        ArrayList<String> commonStore = new ArrayList<>();
        System.out.println("Longest Common Subsequence Between " + in1 + " and " + in2 + " is: " + sub.naiveLongestCommonSubSequence(in1, in2));
        System.out.println("Longest Common Subsequence Between " + in1 + " and " + in2 + " is: " + sub.dynamicLCS(in1, in2));
    }*/
}
