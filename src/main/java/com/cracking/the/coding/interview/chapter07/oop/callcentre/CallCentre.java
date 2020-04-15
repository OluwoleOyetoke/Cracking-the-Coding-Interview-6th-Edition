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
package com.cracking.the.coding.interview.chapter07.oop.callcentre;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Level Enum
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
enum Level {
    Respondent(0),
    Manager(1),
    Director(2);

    int numVal;

    Level(int val) {
        this.numVal = val;
    }

    public int getNumVal() {
        return this.numVal;
    }
}

/**
 * Call Centre Class..Employe, a composite member of this class
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class CallCentre implements CallListener {

    static Queue<Employee>[] employeeBank;

    /**
     * Constructor
     */
    CallCentre() {
        int length = Level.values().length;
        employeeBank = new Queue[length];
        for (int i = 0; i < length; i++) {
            Queue<Employee> employeQueue = new LinkedList<>();
            employeeBank[i] = employeQueue;
        }
    }

    /**
     * Used to make an employee
     *
     * @param level level of the employee
     * @param name name of the employee
     * @return employee made employee
     */
    public Employee makeEmployee(Level level, String name) {
        Employee employee = new Employee(level, name);
        employeeBank[level.getNumVal()].add(employee);
        return employee;
    }

    /**
     * To be fired when call is received
     */
    @Override
    public void dispatchCall() {
        boolean check = true;
        boolean done = false;
        for (int i = 0; i < Level.values().length; i++) {
            if (employeeBank[i] != null && employeeBank[i].size() != 0) {
                check = employeeBank[i].peek().isIsBusy();
            }
            if (!check) {
                Employee employee = employeeBank[i].poll();
                employee.pick(); //fowardCalls here
                done = true;
                break;
            } else {
                System.out.println(Level.values()[i].name() + " is busy");
            }
        }

        if (done == false) {
            System.out.println("No Employee Available to Forward Call to");
        }
    }

    /**
     * Employee Class
     */
    public class Employee {

        String name;
        Level level;
        boolean isBusy;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Level getLevel() {
            return level;
        }

        public void setLevel(Level level) {
            this.level = level;
        }

        public boolean isIsBusy() {
            return isBusy;
        }

        public void setIsBusy(boolean isBusy) {
            this.isBusy = isBusy;
        }

        /**
         * Employee constructor
         *
         * @param level level
         * @param name name
         */
        Employee(Level level, String name) {
            this.level = level;
            this.isBusy = false;
            this.name = name;
        }

        /**
         * Pick call
         */
        public void pick() {
            this.isBusy = true;
            System.out.println(this.level.name() + " " + this.getName() + " Picked");
        }

        /**
         * Drop call
         */
        public void drop() {
            System.out.println("\n" + this.level.name() + " " + this.getName() + " Dropped");
            CallCentre.employeeBank[this.level.getNumVal()].add(this);
            this.isBusy = false;
        }

    }

}
