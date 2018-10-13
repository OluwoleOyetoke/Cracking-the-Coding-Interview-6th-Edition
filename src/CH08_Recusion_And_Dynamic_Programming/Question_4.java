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


/**
 * <b>Power Set:</b> Write a method to return all subsets of a set.
 * 
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_4 {

    
    /**
     * Generates and prints the power set
     * @param val value whose ON bit values will be checked
     * @param stopAt stop bit index...to avoid having to go all the way to bit 32 with the sifts
     * @param list set
     */
    private void generate(int val, int stopAt, int[] list) {
        int mask = 1;
        int result = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stopAt; i++) {
            mask = 1 << i;
            result = val & mask;
            if (result > 0) {
                sb.append(list[stopAt - i - 1] + ", ");
            }
        }

        if (sb.length() != 0) {
            String toPrint = sb.toString();
            System.out.println("{" + toPrint.substring(0,  toPrint.length() - 2) + "}");
        } else {
            System.out.println("{ }");
        }
    }
    
    /**
     * Generate power set
     * @param list set
     */
    public void powerSet(int[] list) {
        if (list == null) {
            System.out.println("List is null");
            return;
        }
        int length = list.length;
        int setFloor = (int) (Math.pow(2, length) - 1);
        for (int i = 0; i <= setFloor; i++) {
            generate(i, length, list);
        }
    }
    
    
    /**
     * Recursion and Dynamic Programing PowerSet main/test method
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4};
        Question_4 q4 = new Question_4();
        q4.powerSet(list);
    }
*/
}
