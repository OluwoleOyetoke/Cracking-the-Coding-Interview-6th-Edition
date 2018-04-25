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
package Arrays_And_Strings;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * <h1> Custom HasMap Implementation</h1>
 * <p>
 * Page.90 CtCi: A good exercise to practice strings, arrays, and general data
 * structures is to implement your own version of StringBuilder, HashTable and
 * Array List.
 * </p>
 *
 * @author Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 * @param <ValueType>
 * @param <KeyType>
 */
public class MyMiniHashMap<KeyType, ValueType> {

    private final LinkedList<MapDatum>[] bucket; //Binary search tree can also be used
    int size;

    /**
     * Default constructor
     */
    public MyMiniHashMap() {
        this.size = 15;
        bucket = new LinkedList[size];

        //initialize linked list
        for (int i = 0; i < 15; i++) {
            bucket[i] = new LinkedList<>();
        }
    }

    /**
     * Parameterized constructor
     *
     * @param size Size of the hash map bucket
     */
    public MyMiniHashMap(int size) {
        this.size = size;
        bucket = new LinkedList[size];

        //initialize linked list
        for (int i = 0; i < size; i++) {
            bucket[i] = new LinkedList<>();
        }
    }

    /**
     * Put datum in hash map
     *
     * @param key Key
     * @param value Value
     * @return true/false returns true if insertion happened correctly
     */
    public boolean put(KeyType key, ValueType value) {
        //hash map allows null values/keys so, no null checks
        int bucketIndex = getBucket(key);
        MapDatum datum = new MapDatum(key, value);
        this.bucket[bucketIndex].add(datum);
        return true;
    }

    public ValueType get(KeyType key) {
        //hash map allows null values/keys so, no null checks
        int bucketIndex = getBucket(key);
        Iterator<MapDatum> it = bucket[bucketIndex].iterator();
        MapDatum datum;
        while (it.hasNext()) {
            datum = it.next();
            if (((KeyType) datum.key).equals(key)) {
                return (ValueType) datum.getValue();
            }
        }
        return null;
    }

    /**
     * Uses hashing to get the bucket index where new key,value will be placed
     *
     * @param key
     * @return whatBucketIndex
     */
    private int getBucket(KeyType key) {
        int keyHash = key.hashCode();
        int whatBucketIndex = keyHash % size;
        return whatBucketIndex;
    }

    /**
     * Clears and reinitializes the Hash map
     *
     * @return true
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            bucket[i] = new LinkedList<>();
        }
    }

    /**
     * Returns a shallow copy of this map
     *
     * @return newMap shallow copy of the previous object
     */
    public MyMiniHashMap cloneThis() {
        MyMiniHashMap newMap = this;
        return newMap;
    }

    /**
     * Checks if map contains key
     *
     * @param key
     * @return
     */
    public boolean containsKey(KeyType key) {
        Iterator<MapDatum> it;
        int bucketIndex = getBucket(key); //optimize by getting the (possible) bucket index first
        it = bucket[bucketIndex].iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search map for s specific value
     *
     * @param value value in search for
     * @return true/false true if map contains value
     */
    public boolean containsValue(ValueType value) {
        Iterator<MapDatum> it;
        for (int i = 0; i < size; i++) {
            it = bucket[i].iterator();
            while (it.hasNext()) {
                if (it.next().getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks to know if map is empty
     *
     * @return true/false true if map is empty
     */
    public boolean isEmpty() {
        if (bucket == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if key is present in map before inserting. If key is associated
     * with null, it will overwrite current value
     *
     * @param key key to check for
     * @param value value to put
     * @return toReturn value placed in the map
     */
    public ValueType putIfAbsent(KeyType key, ValueType value) {
        ValueType toReturn = null;
        boolean success = false;
        boolean isPresent = containsKey(key);
        if (isPresent == false) {
            success = put(key, value);
            if (success) {
                toReturn = null;
            }
        } else if (isPresent == true) {
            ValueType val = get(key);
            if (val == null) {
                success = put(key, value);
                if (success) {
                    toReturn = null;
                }
            } else {
                return val;
            }
        }
        return toReturn;
    }

    /**
     * Gives the int value of the map size i.e the number of key-value mapping
     * in this map
     *
     * @return size
     */
    public int size() {
        int size = 0;
        for (int i = 0; i < this.size; i++) {
            size = size + bucket[i].size();
        }
        return size;
    }

    /**
     *
     * @param key key to search for before removal
     * @return value Returns value removed. Return null if key not present
     * successful
     */
    public ValueType removeInMap(KeyType key) {
        boolean containsKey = containsKey(key);
        if (containsKey == false) {
            return null;
        }

        Iterator<MapDatum> it;
        MapDatum datum;
        //optimize by getting the (possible) bucket index first
        int bucketIndex = getBucket(key);
        it = this.bucket[bucketIndex].iterator();
        while (it.hasNext()) {
            datum = it.next();
            if (datum.getKey().equals(key)) {
                ValueType val = (ValueType) datum.getValue();
                bucket[bucketIndex].remove(datum);
                return val;
            }
        }
        return null;
    }

    /**
     * Removes entry where key is mapped to this value (if any)
     *
     * @param key key to search for
     * @param value value to search for
     * @return true/false true if key value map exists
     */
    public boolean removeInMap(KeyType key, ValueType value) {
        boolean toReturn = false;
        Iterator<MapDatum> it;
        MapDatum datum;
        //optimize by getting the (possible) bucket index first
        int bucketIndex = getBucket(key);
        it = this.bucket[bucketIndex].iterator();
        while (it.hasNext()) {
            datum = it.next();
            if (datum.getKey().equals(key) && datum.getValue().equals(value)) {
                this.bucket[bucketIndex].remove(datum);
                toReturn = true;
            }
        }
        return toReturn;
    }

    /**
     * Replaces value where currentKey=key
     *
     * @param key key to search for
     * @param value new value to place in
     * @return toReturn return value replaced or null if value is not present in
     * map
     */
    public ValueType replace(KeyType key, ValueType value) {
        ValueType toReturn = null;
        Iterator<MapDatum> it;
        MapDatum datum;
        //optimize by getting the (possible) bucket index first
        int bucketIndex = getBucket(key);
        it = this.bucket[bucketIndex].iterator();
        while (it.hasNext()) {
            datum = it.next();
            if (datum.getKey().equals(key)) {
                toReturn = (ValueType) datum.value;
                datum.value = value;
            }
        }
        return toReturn;
    }

    /**
     * Replaces value where currentKey=key, curentValue=value
     *
     * @param key key to search for
     * @param value value to search for
     * @param newValue value to place in
     * @return true/false return true if operation is successfull
     */
    public boolean replace(KeyType key, ValueType value, ValueType newValue) {
        boolean toReturn = false;
        Iterator<MapDatum> it;
        MapDatum datum;
        //optimize by getting the (possible) bucket index first
        int bucketIndex = getBucket(key);
        it = this.bucket[bucketIndex].iterator();
        while (it.hasNext()) {
            datum = it.next();
            if (datum.getKey().equals(key) && datum.getValue().equals(value)) {
                toReturn = true;
                datum.value = newValue;
            }
        }
        return toReturn;
    }

    /**
     * USE HASHMAP
     *
     * @param args the command line arguments
     *//*
    public static void main() {
        MyMiniHashMap<Integer, Integer> myMap = new MyMiniHashMap<>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i + 5);
            myMap.put(i, i + 5);
        }
    }*/

}

/**
 *
 * @param <KeyType>
 * @param <ValueType>
 */
class MapDatum<KeyType, ValueType> {

    KeyType key;
    ValueType value;

    /**
     * Default MapDatum constructor
     *
     * @param key Key
     * @param value Value
     */
    MapDatum(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Used to get key
     *
     * @return key
     */
    public KeyType getKey() {
        return key;
    }

    /**
     * Used to set key
     *
     * @param key
     */
    public void setKey(KeyType key) {
        this.key = key;
    }

    /**
     * Used to get value
     *
     * @return value
     */
    public ValueType getValue() {
        return value;
    }

    /**
     * Used to set value
     *
     * @param value
     */
    public void setValue(ValueType value) {
        this.value = value;
    }

}
/*
class InvalidKeyException extends Exception{
    public InvalidKeyException(){
        super();
    }
    public InvalidKeyException(String message){
        super(message);
    }
}
 */
