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
package com.cracking.the.coding.interview.chapter07.concurrency;

import java.util.Random;

/**
 * Task concrete class
 * @author Oluwole Oyetoke - oluwoleoyetoke at gmail.com 
 */
public class Task implements ITask {
    
    String id;
    Task(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + '}';
    }
    
    @Override
    public void run() {
        Random rand = new Random();
        //System.out.println("Generated Random Number: "+rand.nextInt(20));
    }

    @Override
    public String getId() {
       return this.id;
    }
    
}
