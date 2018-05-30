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
package Trees_And_Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <b>Graph Implementation</b>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Graph<T> {

    HashMap<String, Vertice> graphPlain;

    /**
     * Graph default constructor
     *
     * @param begining
     */
    Graph() {
        graphPlain = new HashMap<>();
    }

    public boolean addVertice(String name, T content) {
        Vertice vertice = new Vertice(name, content);
        graphPlain.put(name, vertice);
        return true;
    }

    public boolean removeVertice(Vertice vertice) {
        graphPlain.remove(vertice.name);
        return true;
    }

    public Vertice createVertice(String name, T content) {
        Vertice vertice = new Vertice(name, content);
        return vertice;
    }

    public void makeEdge(Vertice a, Vertice b, int weight, int direction) {
        Edge newEdge;

        if (direction == 0) { //into b
            newEdge = new Edge(a, b, weight);
            a.edges.add(newEdge);
            b.edges.add(newEdge);
        } else if (direction == 1) {//into a
            newEdge = new Edge(b, a, weight);
            a.edges.add(newEdge);
            b.edges.add(newEdge);
        } else {//both ways
            Edge dir1 = new Edge(a, b, weight);
            Edge dir2 = new Edge(b, a, weight);
            a.edges.add(dir1);
            b.edges.add(dir1);
            a.edges.add(dir2);
            b.edges.add(dir2);
        }
    }

    public void depthFirstSearch(Vertice start, Vertice stop, String prefix) {
        if (start == null || stop == null) {
            System.out.println("One of the requester vertices is not in the graph");
            return;
        }

        Vertice current = start;
        current.visited = true;
        Iterator<Edge> it = current.edges.iterator();
        Edge edge;
        String localPrefix = prefix;
        while (it.hasNext()) {
            edge = it.next();
            if (edge.destination.visited == false) {
                edge.destination.visited = true;
                prefix = localPrefix + ", " + edge.destination.name;
                if (edge.destination == stop) {
                    System.out.println("\n" + prefix + "\n");
                    return;
                } else {
                    depthFirstSearch(edge.destination, stop, prefix);
                }
            }
        }
    }

    public void breadthFirstSearch(Vertice start, Vertice stop, Queue<String> prefix, Queue<Vertice> queue, String prefString) {
        if (start == null || stop == null) {
            System.out.println("One of the requester vertices is not in the graph");
            return;
        }

        Vertice current = start;
        String currentPrefix; 
        current.visited = true;
        Iterator<Edge> it = current.edges.iterator();
        Edge edge;
        while (it.hasNext()) {
            edge = it.next();
            if (edge.destination.visited == false) {
                edge.destination.visited = true;

                if (edge.destination == stop) {
                    prefString = prefString + ", " + edge.destination.name;
                    System.out.println("\n-" + prefString + "-\n");
                    return;
                } else {
                    queue.add(edge.destination);
                    prefix.add(prefString);
                }
            }
            
        }
        while(queue.size()!=0){
            Vertice next = queue.poll();
            String pre = prefix.poll();
            prefString = pre+", "+next.name;
            breadthFirstSearch(next, stop, prefix, queue, prefString);
        }

    }

    /**
     * Class representing line joining two vertices
     */
    public class Edge {

        int weight; //may contain weight
        Vertice source;
        Vertice destination;

        /**
         * Default constructor for an edge
         */
        Edge(Vertice source, Vertice destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * GEt weight of edge
         *
         * @return weight weight
         */
        public int getWeight() {
            return weight;
        }

        /**
         * Set weight of edge, if any
         *
         * @param weight weight
         */
        public void setWeight(int weight) {
            this.weight = weight;
        }

        /**
         * Get source vertice
         *
         * @return source source vertice
         */
        public Vertice getSource() {
            return source;
        }

        /**
         * Set source vertice
         *
         * @param source source vertice
         */
        public void setSource(Vertice source) {
            this.source = source;
        }

        /**
         * Get destination vertice
         *
         * @return destination destination vertice
         */
        public Vertice getDestination() {
            return destination;
        }

        /**
         * Set destination vertice for the edge
         *
         * @param destination destination vertice
         */
        public void setDestination(Vertice destination) {
            this.destination = destination;
        }

    }

    public class Vertice {

        T content;
        String name;
        ArrayList<Edge> edges;
        boolean visited;

        Vertice(String name, T content) {
            this.name = name;
            this.content = content;
            edges = new ArrayList<>();
            visited = false;
        }

        /**
         * Get content of vertice
         *
         * @return content content
         */
        public T getContent() {
            return content;
        }

        /**
         * Set content of vertice
         *
         * @param content content to put
         */
        public void setContent(T content) {
            this.content = content;
        }

        /**
         * Get label of vertice
         *
         * @return name label
         */
        public String getName() {
            return name;
        }

        /**
         * Set name of vertice
         *
         * @param name name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Get edges associated with this vertice
         *
         * @return edges edges
         */
        public ArrayList<Edge> getEdges() {
            return edges;
        }

        /**
         * Set edges connected to this node
         *
         * @param edges edges to set
         */
        public void setEdges(ArrayList<Edge> edges) {
            this.edges = edges;
        }
    }

    /**
     * Graph main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        String[] location = {"United Kingdon", "United States", "Canada", "Nigeria", "South Africa", "Rest of the World"};

        //MAKE GRAPH
        //Links within Nigeria
        graph.addVertice("Lagos", location[3]);
        graph.addVertice("Ibadan", location[3]);
        graph.addVertice("Benin Republic", location[5]);
        graph.addVertice("Ondo", location[3]);
        graph.addVertice("Port-Harcourt", location[3]);
        graph.addVertice("Delta", location[3]);
        graph.addVertice("Abuja", location[3]);
        graph.addVertice("Kwara", location[3]);
        graph.addVertice("Kano", location[3]);
        graph.addVertice("Kaduna", location[3]);
        graph.addVertice("Kebbi", location[3]);
        graph.addVertice("Sokoto", location[3]);
        graph.addVertice("Illela", location[3]);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Ibadan"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Benin Republic"), 0, 2);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Ondo"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Ibadan"), graph.graphPlain.get("Ondo"), 0, 2);

        graph.makeEdge(graph.graphPlain.get("Ondo"), graph.graphPlain.get("Port-Harcourt"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Port-Harcourt"), graph.graphPlain.get("Delta"), 0, 0);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Abuja"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Ibadan"), graph.graphPlain.get("Abuja"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Ibadan"), graph.graphPlain.get("Kwara"), 0, 0);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Kano"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Abuja"), graph.graphPlain.get("Kano"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Abuja"), graph.graphPlain.get("Kaduna"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Kano"), graph.graphPlain.get("Kaduna"), 0, 0);

        graph.makeEdge(graph.graphPlain.get("Kaduna"), graph.graphPlain.get("Kebbi"), 0, 0);

        graph.makeEdge(graph.graphPlain.get("Kebbi"), graph.graphPlain.get("Sokoto"), 0, 0);

        graph.makeEdge(graph.graphPlain.get("Sokoto"), graph.graphPlain.get("Illela"), 0, 2);

        graph.addVertice("Niamey", location[5]);
        graph.addVertice("Rabatt", location[5]);

        graph.makeEdge(graph.graphPlain.get("Illela"), graph.graphPlain.get("Niamey"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Niamey"), graph.graphPlain.get("Rabatt"), 0, 0);

        graph.addVertice("Barcelona", location[5]);
        graph.addVertice("Rome", location[5]);
        graph.addVertice("Brussels", location[5]);

        
        graph.makeEdge(graph.graphPlain.get("Rabatt"), graph.graphPlain.get("Barcelona"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("Rome"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("Brussels"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Rome"), graph.graphPlain.get("Brussels"), 0, 2);
         
        graph.addVertice("London", location[0]);
        graph.addVertice("Leeds", location[0]);
        graph.addVertice("Manchester", location[0]);
        graph.addVertice("Edingbrough", location[0]);
        graph.addVertice("Cambridge", location[0]);
        graph.addVertice("Watford", location[0]);
        
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("London"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("Manchester"), 0, 2);

        graph.makeEdge(graph.graphPlain.get("Rome"), graph.graphPlain.get("London"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Rome"), graph.graphPlain.get("Manchester"), 0, 2);

        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Manchester"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Leeds"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Watford"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Cambridge"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Leeds"), graph.graphPlain.get("Edingbrough"), 0, 2);

        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Lagos"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Lagos"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Rabatt"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Rabatt"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Abuja"), 0, 2);
         
        graph.addVertice("New York", location[1]);
        graph.addVertice("Seattle", location[1]);
        graph.addVertice("Carlifornia", location[1]);
        graph.addVertice("Pensylvania", location[1]);
        
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("London"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Lagos"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Abuja"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Brussels"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Seattle"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Seattle"), graph.graphPlain.get("Carlifornia"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Carlifornia"), graph.graphPlain.get("Pensylvania"), 0, 2);
         
        graph.addVertice("Toronto", location[2]);
        graph.addVertice("Montreal", location[2]);
        graph.addVertice("British Columbia", location[2]);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("New York"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("London"), 0, 1);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Lagos"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Montreal"), 0, 0);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("British Columbia"), 0, 0);
     
        graph.addVertice("Cape Town", location[4]);
        graph.addVertice("Pretoria", location[4]);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("New York"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("Brussels"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("Pretoria"), 0, 2);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("Abuja"), 0, 2);
        
        Queue queue = new LinkedList<>();
        Queue<String> prefixQueue = new LinkedList<>();
        //graph.depthFirstSearch(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Sokoto"), "Toronto");
        graph.breadthFirstSearch(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Delta"), prefixQueue, queue, "Toronto");
    }
*/
}
