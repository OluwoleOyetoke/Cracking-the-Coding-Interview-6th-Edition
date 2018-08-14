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
package Object_Oriented_Design.ObjectPool;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Abstract class to be used for resticting object creation of a class to a pool
 * of objects only
 *
 * @author Oluwole Oyetoke oluwoleoyetoke at gmail.com
 */
public abstract class ObjectPool<T> {

    HashMap<T, Long> locked, unlocked;
    private final long EXPIRY_TIME;
    private long MAX_POOL_SIZE= 30;
    private long numberOutThere=0;

    ObjectPool(long maxPoolSize, long expiryTime) {
        EXPIRY_TIME = expiryTime; //in miliseconds
        MAX_POOL_SIZE = maxPoolSize;
        numberOutThere=0;
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
     * @return
     */
    public synchronized T checkOut() {
        long currentTime = System.currentTimeMillis();
        if (unlocked.isEmpty()) {
            if(numberOutThere>=MAX_POOL_SIZE){
                //keep waiting till number out there is decremented
                //this will require more logic, since its also possible for all
                //our object to be checked out and not checked back in appropriately, e.g
                //because user lost connection;
            }
            
            T newObject = create();
            locked.put(newObject, currentTime);
            numberOutThere++;
            return newObject;
        } else {
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
                } else if (!validate(retreived)) { //validate object
                    expire(retreived);
                    unlocked.remove(retreived);
                    retreived = null; //put in the position for GC to clear off ASAP
                } else { //
                    unlocked.remove(retreived);
                    locked.put(retreived, currentTime);
                    numberOutThere++;
                    return retreived;
                }
            }
        }
        return null;
    }

    public synchronized boolean checkIn(T o) {
           locked.remove(o);
           unlocked.put(o, System.currentTimeMillis());
           numberOutThere--;
           return true;
    }

}
