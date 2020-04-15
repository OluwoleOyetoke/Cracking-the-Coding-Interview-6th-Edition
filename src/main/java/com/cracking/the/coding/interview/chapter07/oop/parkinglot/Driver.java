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
package com.cracking.the.coding.interview.chapter07.oop.parkinglot;

/**
 * Driver Class
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Driver {

    String name;
    Vehicle car;

    Driver(String name) {
        this.name = name;
    }

    Driver(String name, Vehicle car) {
        this.name = name;
        this.car = car;
        car.setDriver(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getCar() {
        return car;
    }

    public void setCar(Vehicle car) {
        this.car = car;
    }

    public void parkIn(Lot parkingLot) {
        if (this.car == null) {
            System.out.println("This driver has no car");
        }
        parkingLot.parkIn(this.car);
    }

    public void driveOut(Lot parkingLot) {
        if (this.car == null) {
            System.out.println("This driver has no car");
        }
        parkingLot.driveOut(this.car);
    }
    
    public void attachToVehicle(Vehicle vehicle){
        this.car = vehicle;
        this.car.setDriver(this);
    }

}
