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
package Object_Oriented_Design.ParkingLot;

/**
 * Vehicle Class
 * 
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Vehicle {

    private String name;
    private Driver driver;
    private boolean parked;
    
      Vehicle(String name) {
        this.name = name;
        parked = false;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(String parkLocation) {
        this.parkLocation = parkLocation;
    }
    String parkLocation;

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }
}
