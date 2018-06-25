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
package Bit_Manipulation;

/**
 * <b>Binary to String:</b> Given a real number between O and 1 (e.g., 0.72)
 * that is passed in as a double, print the binary representation. If the number
 * cannot be represented accurately in binary with at most 32 characters, print
 * "ERROR:'
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Question_2 {

    
    public void solve(){
        
    }
    
    
    
    
    /**
     * Bit Manipulation Question_2 main/test method....uncomment to run
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Bits bits = new Bits();
        Question_1 q1 = new Question_1();
        String N = "10000000000";
        String M = "10011";
        int i = 2;
        int j = 6;
        q1.replace(N, M, i, j);
        q1.replace2(N, M, i, j);
    }

}
