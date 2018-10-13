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
package CH08_Recusion_And_Dynamic_Programming;

import java.util.Stack;

/**
 * <b>Towers of Hanoi:</b> In the classic problem of the Towers of Hanoi, you
 * have 3 towers and N disks of different sizes which can slide onto any tower.
 * The puzzle starts with disks sorted in ascending order of size from top to
 * bottom (i.e., each disk sits on top of an even larger one).You have the
 * following
 *
 * constraints:
 *
 * (1) Only one disk can be moved at a time. (2) A disk is slid off the top of
 * one tower onto another tower. (3) A disk cannot be placed on top of a smaller
 * disk.
 *
 * Write a program to move the disks from the first tower to the last using
 * stacks.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_6 {
    
    /**
     * Do tower of hanoi movement
     * @param tower1 tower 1
     * @param tower2 tower 2
     * @param tower3 tower 3
     */
    public void moveToTower3(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3) {
        if (tower1 == null) {
            System.out.println("Tower 1 is empty");
            return;
        }

        move(tower1, tower2, tower3);
    }
    
    /**
     * Slides the disk while maintaining their ascending order arrangements
     * @param tower1 rower 1
     * @param tower2 tower 2
     * @param tower3 tower 3
     */
    public void move(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3) {
        if (tower1 != null && !tower1.isEmpty()) {

            if (tower2.isEmpty()) { //tower two is just a transit...It will always be empty after every operation
                tower2.push(tower1.pop());
                if (tower3.isEmpty()) {
                    tower3.push(tower2.pop());
                } else {
                    int temp = tower2.pop();
                    int tower3Size = tower3.size();
                    for (int i = 0; i < tower3Size; i++) {
                        tower2.push(tower3.pop());
                    }
                    tower3.push(temp);
                    int tower2Size = tower2.size();
                    for (int i = 0; i < tower2Size; i++) {
                        tower3.push(tower2.pop());
                    }
                }
            }
            move(tower1, tower2, tower3);
        } else {
            System.out.println("\nAFTER MOVEMENT:");
            System.out.println("Tower 1: " + tower1.toString());
            System.out.println("Tower 2: " + tower2.toString());
            System.out.println("Tower 3: " + tower3.toString());
            return;
        }

    }

    /**
     * Recursion and Dynamic Programing Question 6 main/test method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {

        Stack<Integer> tower1 = new Stack<>();
        Stack<Integer> tower2 = new Stack<>();
        Stack<Integer> tower3 = new Stack<>();

        for (int i = 20; i >= 0; i--) {
            tower1.push(i);
        }
        System.out.println("BEFORE MOVEMENT:");
        System.out.println("Tower 1: " + tower1.toString());
        System.out.println("Tower 2: " + tower2.toString());
        System.out.println("Tower 3: " + tower3.toString());
        Question_6 toa = new Question_6();
        toa.moveToTower3(tower1, tower2, tower3);
    }*/
}
