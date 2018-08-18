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

import java.util.Collections;
import java.util.Comparator;
import java.util.*;

/**
 * Form the largest possible number from the array of number.
 *
 * @author Oluwole Oyetoke {@literal <}
 * oluwoleoyetoke{@literal @}gmail.com{@literal >}
 */
public class RearrangeArray {

    public void rearrange(ArrayList<String> list) {
        System.out.println("We can re-arrange the contents of " + list.toString());
        Collections.sort(list, new CustomCompare());
        Iterator it = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        System.out.println("To form a large number: " + sb.toString());
    }

    /**
     * Main method...uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("34");
        list.add("3");
        list.add("98");
        list.add("9");
        list.add("76");
        list.add("45");
        list.add("4");
        RearrangeArray nonRepeat = new RearrangeArray();
        nonRepeat.rearrange(list);
    }*/
}

class CustomCompare implements Comparator<String> {

    @Override
    public int compare(String lhs, String rhs) {
        String case1 = lhs + "" + rhs;
        String case2 = rhs + "" + lhs;
        int result = case1.compareTo(case2);
        if (result < 0) {
            return 1;
        } else if (result == 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
