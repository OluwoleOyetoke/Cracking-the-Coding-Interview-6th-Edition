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

/**
 * <b>String Rotation:</b> Assume you have a method isSubstringwhich checks if
 * oneword is a substring of another. Given two strings, sl and s2, write code
 * to check if s2 is a rotation of sl using only one call to isSubstring (e.g.,
 * "waterbottle" is a rotation of"erbottlewat").
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 */
public class Question_9 {

    enum RotationType {
        LEFT, RIGHT;
    }

    /**
     * Cheks if s2 is a rotation of s1
     *
     * @param s1 string 1
     * @param s2 string 2
     * @return ture/false true if it is a rotation of it, and false if otherwise
     */
    public boolean isSubstring(String s1, String s2) {
        String rotated = "";
        for (int i = 0; i < s1.length() - 1; i++) {
            rotated = rotateString(s1, i + 1, RotationType.LEFT);
            if (rotated.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Rotates string either left or right, 'times' number of times
     *
     * @param input input string to rotate
     * @param times number of times to rotate
     * @param type RotationType.LEFT or RotationType.RIGHT rotation
     * @return rotated rotated string
     */
    public String rotateString(String input, int times, RotationType type) {
        if (times < 0) {
            System.out.println("Invalid number of rotation times specified");
            return null;
        } else if (input == null || input.length() == 0) {
            System.out.println("Invalid input received");
            return null;
        }
        char[] inputChar = input.toCharArray();
        int len = input.length() - 1;
        if (type == RotationType.LEFT) {
            reverse(inputChar, 0, len);
            reverse(inputChar, 0, len - times);
            reverse(inputChar, len - times + 1, len);
            //System.out.println(new String(inputChar));
            return new String(inputChar);
        } else {
            reverse(inputChar, 0, len);
            reverse(inputChar, 0, times - 1);
            reverse(inputChar, times, len);
            //System.out.println(new String(inputChar));
            return new String(inputChar);
        }
    }

    /**
     * USed to reverse a char array
     *
     * @param input char array to reverse
     * @param start start position
     * @param stop stop position
     */
    public void reverse(char[] input, int start, int stop) {
        if (input == null || input.length == 0) {
            System.out.println("Input is not Valid");
            return;
        } else if (stop > (input.length - 1) || stop < 0) {
            System.out.println("Stop Index of: " + stop + " No Valid");
            return;
        } else if (start < 0 || start > stop) {
            System.out.println("Start Index of: " + start + " No Valid");
            return;
        }

        char temp;
        while (start < stop) {
            //swap
            temp = input[start];
            input[start] = input[stop];
            input[stop] = temp;
            start++;
            stop--;
        }
    }

    /**
     * Question_9 main.....
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Question_9 q9 = new Question_9();
        String s1 = "erbottlewat";
        String s2 = "waterbottle";
        boolean answer = q9.isSubstring(s1, s2);
        System.out.println("Is " + s1 + " a rotation of " + s2 + " = " + answer);
    }*/
}
