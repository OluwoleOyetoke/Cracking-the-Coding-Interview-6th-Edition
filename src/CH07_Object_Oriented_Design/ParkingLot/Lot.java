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
package CH07_Object_Oriented_Design.ParkingLot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Parking Lot class
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Lot {

    String name;
    int maxParking;
    HashMap<String, Lot.Space> spaces;
    Queue<String> spaceLocations;
    HashMap<String, Vehicle> vehicles;

    Lot(String name, int maxParking) {
        this.name = name;
        this.maxParking = maxParking;
        spaces = new HashMap<>();
        vehicles = new HashMap<>();
        spaceLocations = new LinkedList();
        //carete spaces
        String tempName = "space-";
        String toPlaceIn = "";
        for (int i = 0; i < maxParking; i++) {
            toPlaceIn = tempName + "" + i;
            Lot.Space space = new Lot.Space(toPlaceIn);
            spaceLocations.add(toPlaceIn);
            spaces.put(toPlaceIn, space);
        }

    }

    public boolean isSpaceAvailable() {
        return true;
    }

    public boolean parkIn(Vehicle car) {
        if ((spaceLocations.size() <= 0)) {
            System.out.println("Car Park is full");
            return false;
        } else if (car.isParked()) {
            System.out.println(car.getName() + " is already parked");
            return false;
        } else if (car.getDriver() == null) {
            System.out.println(car.getName() + " has no driver. Put a driver in to move it");
            return false;
        } else if (vehicles.containsValue(car)) {
            System.out.println(car.getName() + " is already parked");
            return false;
        }
        //here, we could implement a logic to park car at a specific spot/level within the car park
        String loc = spaceLocations.poll();
        spaces.get(loc).filled = true;
        car.setParked(true);
        car.setParkLocation(loc);
        vehicles.put(car.getName(), car);
        System.out.println(car.getDriver().getName()+" Parked " + car.getName() + " into: " + loc+" in the "+this.name+" parking space");
        return true;
    }

    public void driveOut(Vehicle car) {
        if (!car.isParked()) {
            System.out.println("Car not parked before");
            return;
        } else if (car.getDriver() == null) {
            System.out.println("Vehicle has no driver. Put a driver in to move it");
            return;
        }

        String loc = car.getParkLocation();
        car.setParked(false);
        spaces.get(loc).filled = false;
        spaceLocations.add(loc);
        vehicles.remove(car.getName());
        System.out.println(car.getDriver().getName()+" Drove " + car.getName() + " Out of: " + loc+" in the "+this.name+" parking space");
    }

    private class Space {

        Space(String name) {
            this.name = name;
            filled = false;
        }

        private boolean filled;
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
        }

        public boolean getFilled() {
            return filled;
        }

        public boolean isFilled() {
            return filled;
        }
    }
}
