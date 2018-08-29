/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * <b>Dijkstra: </b> Find shortest path between two cities
 *
 * @author Oluwole Oyetoke oluwoleoyetoke@gmail.com
 */
public class Dijkstra {
    
    /**
     * City class
     */
    static class City {

        String name;
        ArrayList<City> connectedTo;
        HashMap<String, Integer> connectionDistance;
        boolean isVisited;
        City from;
        int myCost = 0;

        City(String name, int myCost) {
            this.name = name;
            connectionDistance = new HashMap<>();
            connectedTo = new ArrayList();
            isVisited = false;
            from = null;
            this.myCost = myCost;
        }

        public void addConnection(City city, int distance) {
            this.connectedTo.add(city);
            connectionDistance.put(city.name, distance);
        }

        public boolean isIsVisited() {
            return isVisited;
        }

        public void setIsVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }

        public City getFrom() {
            return from;
        }

        public void setFrom(City from) {
            this.from = from;
        }
    }
    
    /**
     * Use Dijstra Algorithm to find shortest path between city start and city stop
     * @param start start city
     * @param stop stop city
     */
    public void dijkstra(City start, City stop) {
        Comparator<City> comparator = new Comparator<City>() {
            @Override
            public int compare(City lhs, City rhs) {
                return lhs.myCost - rhs.myCost;
            }
        };
        PriorityQueue<City> priority = new PriorityQueue<>(comparator);
        findPath(start, start, stop, 0, priority);
    }
    
    /**
     * Uses Dijstra
     * @param start start city
     * @param current current city
     * @param stop stop city
     * @param myOwnCost cost of current city
     * @param priority priority queue 
     */
    private void findPath(City start, City current, City stop, int myOwnCost, PriorityQueue<City> priority) {
        if (start.equals(stop)) {
            System.out.println("Route From " + start.name + " to " + stop.name + ": " + stop); 
            return;
        }else if(current.equals(stop)){
            Stack<String> route = new Stack<>();
            route.add(stop.name);
            City temp=current;
            while (!temp.equals(start)) {
                temp = temp.from;
                route.add(temp.name);
            }

            Iterator<String> it2 = route.iterator();
            StringBuilder sb = new StringBuilder();
            while (it2.hasNext()) {
                sb.append(route.pop() + " ");
            }
            System.out.println("Route From " + start.name + " to " + stop.name + ": " + sb.toString());
            priority.clear();
            return;
        }

        if (current.isVisited == true) {
            return;
        }

        City connectedCity;
        int newCost = 0;
        Iterator<City> it;
        City customPriority = null;
        int minimalest = Integer.MAX_VALUE;
        if (current.isVisited == false) {
            if (!current.connectedTo.isEmpty()) {
                it = current.connectedTo.iterator();
                while (it.hasNext()) {
                    connectedCity = it.next();
                    newCost = myOwnCost + current.connectionDistance.get(connectedCity.name);
                    if (newCost <= connectedCity.myCost) {
                        connectedCity.myCost = newCost;
                        connectedCity.setFrom(current);
                        
                        priority.add(connectedCity);
                        if (newCost < minimalest) {
                            customPriority = connectedCity;
                        }
                    }
                }
            }
        }
        current.setIsVisited(true);
  
        City next;
        while (!priority.isEmpty()) {
            next = priority.poll();
            findPath(start, next, stop, next.myCost, priority);

        }
    }

    /**
     * Test Method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        //cities
        City london = new City("London", Integer.MAX_VALUE);
        City lagos = new City("Lagos", Integer.MAX_VALUE);
        City dubai = new City("Dubai", Integer.MAX_VALUE);
        City newyork = new City("New York", Integer.MAX_VALUE);
        City toronto = new City("Toronto", Integer.MAX_VALUE);

        //connect cities
        london.addConnection(newyork, 100);
        london.addConnection(toronto, 20);
        toronto.addConnection(lagos, 23);
        toronto.addConnection(dubai, 3);
        dubai.addConnection(lagos, 2);
        lagos.addConnection(newyork, 17);

        //Find path between London and NewYork
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra(london, newyork);
    }*/
}
