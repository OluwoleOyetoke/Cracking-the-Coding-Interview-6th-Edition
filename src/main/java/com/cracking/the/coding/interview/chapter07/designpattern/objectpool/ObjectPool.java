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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class to be used for resticting object creation of a class to a pool
 * of objects only
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}gmail.com{@literal >}
 */
public abstract class ObjectPool<T> {

    HashMap<T, Long> locked, unlocked;
    private final long EXPIRY_TIME;
    private long MAX_POOL_SIZE;
    private long MAX_CHECK_OUT_TIME;
    private long numberOutThere = 0;

    ObjectPool(long maxPoolSize, long expiryTime, long maxCheckOutTime) {
        EXPIRY_TIME = expiryTime; //in miliseconds
        MAX_POOL_SIZE = maxPoolSize;
        MAX_CHECK_OUT_TIME = maxCheckOutTime;
        numberOutThere = 0;
        locked = new HashMap<>();
        unlocked = new HashMap<>();
    }

    /**
     * Expire an object after removal from locked
     *
     * @param o object to expire
     * @return ture/false true if expired and false if otherwise
     */
    public abstract boolean expire(T o);

    /**
     * Create a new object
     *
     * @return object return created object
     */
    protected abstract T create();

    /**
     * Check in object into the pool
     *
     * @param o object to check in
     * @return true/false return true if successful and false if otherwise
     */
    public abstract boolean validate(T o);

    /**
     * Check out object from the pool
     *
     * @return T object
     */
    public synchronized T checkOut() {
        long currentTime = System.currentTimeMillis();
        if (unlocked.isEmpty()) {
            System.out.println("\n\nUnlocked object pool is empty");
            if (numberOutThere >= MAX_POOL_SIZE) {
                System.out.println("Number of objects out there greater than max pool size");
                /*keep waiting till number out there is decremented
                this will require more logic, since its also possible for all
                our object to be checked out and not checked back in appropriately, e.g
                because user lost connection...Ways to go about this include:
                
                1. Scan through checked out objects to see if any exist that has been
                check out for over MAX_CHECK_OUT_TIME  without being returned
                 */
                System.out.println("Starting to iterate through locked objects to find object to free");
                boolean foundSpace = false;
                while (foundSpace != true) {
                     currentTime = System.currentTimeMillis();
                    Set<T> keys = locked.keySet();
                    Iterator<T> it = keys.iterator();
                    T retreived;
                    long timeCheckedOut;
                    while (it.hasNext()) {
                        retreived = it.next();
                        timeCheckedOut = locked.get(retreived);
                        if ((currentTime - timeCheckedOut) > MAX_CHECK_OUT_TIME) {
                            System.out.println("Found checked out object which has exceeded MAX_CHECK_OUT_TIME");
                            /*2. Invalidate it and return it back to pool*/
                            locked.remove(retreived);
                            retreived = null; //make available for GC to clear
                            T newObject = create();
                            checkIn(newObject);
                            /*3. This new requester picks it up and uses it*/
                            locked.put(newObject, currentTime);
                            foundSpace = true;
                            System.out.println("Found object re-allocated to new requester");
                            return retreived;
                        }
                    }
                    /*wait a littlebit before starting the next set of checks again itteration*/
                    try {
                        System.out.println("No checked out object found with check out time > MAX_CHECK_OUT_TIME...");
                        Thread.sleep((long) (MAX_CHECK_OUT_TIME)); //if the max xhexk out time is 1 second, sleep for 250 milli second before check again
                        System.out.println("Waited for some time.... About to check again if free object can be found");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ObjectPool.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    /*check if space is already available now before starting to check through the locks again*/
                    if (numberOutThere < MAX_POOL_SIZE) {
                        System.out.println("While waiting, one of the actors has checked back in an object...So we will just use that, right away");
                        Set<T> keys2 = unlocked.keySet();
                        Iterator<T> it2 = keys2.iterator();
                        T retreived2;
                        long timeCheckedIn;
                        while (it2.hasNext()) {
                            retreived2 = it2.next();
                            timeCheckedIn = unlocked.remove(retreived2);
                            if (currentTime - timeCheckedIn > EXPIRY_TIME) { //check if this object is not expired yet
                                expire(retreived2);
                                unlocked.remove(retreived2);
                                retreived2 = null; //put in the position for GC to clear off ASAP
                                System.out.println("Some expired object found in the process of trying to allocate");
                            } else if (!validate(retreived2)) { //validate object
                                expire(retreived2);
                                unlocked.remove(retreived2);
                                retreived2 = null; //put in the position for GC to clear off ASAP
                                System.out.println("Some invalid objects found in the process of trying to allocate");
                            } else { //
                                unlocked.remove(retreived2);
                                locked.put(retreived2, currentTime);
                                numberOutThere++;
                                System.out.println("Proper/Free object found in pool....Now allocated");
                                return retreived2;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Space exist to create new object and checkout");
                T newObject = create();
                locked.put(newObject, currentTime);
                numberOutThere++;
                System.out.println(numberOutThere+" Object created and checked out");
                return newObject;
            }
        } else {
            System.out.println("\n\nFree/Unlocked object available in pool");
            Set<T> keys = unlocked.keySet();
            Iterator<T> it = keys.iterator();
            T retreived;
            long timeCheckedIn;
            while (it.hasNext()) {
                retreived = it.next();
                timeCheckedIn = unlocked.remove(retreived);
                if (currentTime - timeCheckedIn > EXPIRY_TIME) { //check if this object is not expired yet
                    expire(retreived);
                    unlocked.remove(retreived);
                    retreived = null; //put in the position for GC to clear off ASAP
                    System.out.println("Some expired object found in the process of trying to allocate");
                } else if (!validate(retreived)) { //validate object
                    expire(retreived);
                    unlocked.remove(retreived);
                    retreived = null; //put in the position for GC to clear off ASAP
                    System.out.println("Some invalid objects found in the process of trying to allocate");
                } else { //
                    unlocked.remove(retreived);
                    locked.put(retreived, currentTime);
                    numberOutThere++;
                    System.out.println("Proper/Free object found in pool....Now allocated");
                    return retreived;
                }
            }
        }
        return null;
    }

    public synchronized boolean checkIn(T o) {
        if (o != null) {
            locked.remove(o);
            unlocked.put(o, System.currentTimeMillis());
            numberOutThere--;
            System.out.println("Object Checked in");
        }else{
            System.out.println("Attempt to check back in a null object");
        }
        return true;
    }

}
