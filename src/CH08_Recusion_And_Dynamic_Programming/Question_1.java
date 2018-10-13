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

import java.util.ArrayList;

/**
 * <b>Triple Step: </b> A child is running up a staircase with n steps and can
 * hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count
 * how many possible ways the child can run up the stairs.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_1 {

    int counter;

    Question_1() {
        counter = 0;
    }

    /**
     * solve n steps problem
     *
     * @param stepLength stop position
     */
    public void tripleStep(int stepLength) {
        ArrayList tracker = new ArrayList();
        move(0, 0, "", stepLength);
        System.out.println("Number of Possibe Ways: " + counter);
    }

    /**
     *
     * @param currentPosition current position
     * @param step step to take now
     * @param tracker string tracking steps
     * @param stopPosition stop position
     */
    public void move(int currentPosition, int step, String tracker, int stopPosition) {
        if (currentPosition > stopPosition) {
            return;
        }

        currentPosition = currentPosition + step;

        if (currentPosition == stopPosition) {
            tracker = tracker + ", " + currentPosition;
            counter++;
            System.out.println(tracker.substring(2, tracker.length()));
            return;
        }

        move(currentPosition, 1, tracker + ", " + currentPosition, stopPosition);
        move(currentPosition, 2, tracker + ", " + currentPosition, stopPosition);
        move(currentPosition, 3, tracker + ", " + currentPosition, stopPosition);
    }

    /**
     * Recursion/Dynamic Programming question 1 main method....uncomment to run
     *
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        Question_1 q1 = new Question_1();
        q1.tripleStep(6);
    }*/
}
