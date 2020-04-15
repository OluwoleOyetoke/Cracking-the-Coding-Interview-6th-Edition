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
package com.cracking.the.coding.interview.chapter07.designpattern.objectpool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Multi threaded actors
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}gmail.com{@literal >}
 */
public class Actor implements Runnable {

    @Override
    public void run() {

        FireworksAnimation fireworks ;
        FireworksAnimationPool pool;
        for (int i = 0; i < 6; i++) {
            pool = FireworksAnimationPool.getInstance();
            //check out
            fireworks = pool.checkOut();
            try {
                //use object
                Thread.sleep(3000); //attempting to use the object for longer than MAX_CHECK_OUT_TIME
            } catch (InterruptedException ex) {
                Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
            }

            //check back in
            pool.checkIn(fireworks);
        }
    }

}
