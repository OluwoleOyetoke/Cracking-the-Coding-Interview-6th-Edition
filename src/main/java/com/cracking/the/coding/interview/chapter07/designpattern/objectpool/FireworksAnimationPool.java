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

/**
 * Fireworks pool
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}gmail.com{@literal >}
 */
public class FireworksAnimationPool extends ObjectPool<FireworksAnimation> {
    public static FireworksAnimationPool fireworksAnimationPool = null; 
    @Override
    public boolean expire(FireworksAnimation o) {
       //do things that have to do with expiry here
        System.out.println("Fireworks expired");
       return true;
    }

    @Override
    protected FireworksAnimation create() {
        System.out.println("Fireworks created/recreated");
        return new FireworksAnimation();
    }

    @Override
    public boolean validate(FireworksAnimation o) {
       //do validation here
        System.out.println("Fireworks validated");
       return true;
    }
    
    /**
     * Make singleton
     */
    private FireworksAnimationPool(long maxPoolSize, long expiryTime, long maxChekOutTime){
        super(maxPoolSize, expiryTime, maxChekOutTime);
    }
    
    public static synchronized FireworksAnimationPool getInstance(){
        if(fireworksAnimationPool==null){
            fireworksAnimationPool = new FireworksAnimationPool(5, 10000, 2000);
            return fireworksAnimationPool;
        }else{
            return fireworksAnimationPool;
        }
    }
}
