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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Queue;


/**
 * <b>Graph Implementation 2</b>
 * <ol>
 * <li>Add vertices</li>
 * <li>Depth First Search</li>
 * <li>Breadth First Search</li>
 * <li>Dijkstra</li>
 * <li>Topological Sort</li>
 * </ol>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Graph<T> {

    Hashtable<String, Vertice> graphPlain;

    /**
     * Graph default constructor
     *
     * @param begining
     */
    Graph() {
        graphPlain = new Hashtable<>();
    }
    
    /**
     * Add vertices to graph
     * @param name name of vertice
     * @param content content of vertice
     * @return true/false true of successful and false if otherwise
     */
    public boolean addVertice(String name, T content) {
        Vertice vertice = new Vertice(name, content);
        graphPlain.put(name, vertice);
        return true;
    }
    
    /**
     * Create a vertice
     * @param name name of vertice
     * @param content content of vertice
     * @return cretedVertice created vertice
     */
    public Vertice createVertice(String name, T content) {
        Vertice vertice = new Vertice(name, content);
        return vertice;
    }
    
    /**
     * Make graph edge
     * @param a vertice a
     * @param b vertice b
     * @param weight weight of edge
     * @param direction direction of edge
     */
    public void makeEdge(Vertice a, Vertice b, int weight, int direction) {
        Edge newEdge;

        if (direction == 0) { //into b
            newEdge = new Edge(a, b, weight);
            newEdge.weight = weight;
            a.edges.add(newEdge);
            b.edges.add(newEdge);
            b.froms.add(a);
        } else if (direction == 1) {//into a
            newEdge = new Edge(b, a, weight);
            newEdge.weight = weight;
            a.edges.add(newEdge);
            b.edges.add(newEdge);
            a.froms.add(b);
        } else {//both ways
            Edge dir1 = new Edge(a, b, weight);
            dir1.weight = weight;
            Edge dir2 = new Edge(b, a, weight);
            dir2.weight = weight;
            a.edges.add(dir1);
            b.edges.add(dir1);
            a.edges.add(dir2);
            b.edges.add(dir2);
            a.froms.add(b);
            b.froms.add(a);
        }
    }
    
    /**
     * Depth first search of graph
     * @param start start node
     * @param stop stop node
     * @param prefix prefix
     */
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
    
    /**
     * Breadth first search through a graph
     * @param start start vertice
     * @param stop stop vertice
     * @param prefix prefix queue
     * @param queue vertice queue
     * @param prefString prefix string
     */
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
        while (queue.size() != 0) {
            Vertice next = queue.poll();
            String pre = prefix.poll();
            prefString = pre + ", " + next.name;
            breadthFirstSearch(next, stop, prefix, queue, prefString);
        }

    }
    
    /**
     * Find shortest path between two vertices
     * @param start start vertice
     * @param end end vertice
     */
    public void dijkstra2(Vertice start, Vertice end) {
        if (start == null || start.visited == true) {
            System.out.println("Visited");
            return;
        }

        if (start != null && start == end) {
            ArrayList list = new ArrayList();
            list.add(start.name);
            Vertice printer = end;
            while (printer != null && printer.from != null) {
                list.add(printer.from.name);
                printer = printer.from;
                if (printer == start) {
                    break;
                }
            }

            System.out.println("");
            for (int i = list.size() - 1; i >= 0; i--) {
                if (i != 0) {
                    System.out.print(list.get(i) + ", ");
                } else {
                    System.out.print(list.get(i));
                }
            }
            System.out.println("");
            return;
        }

        Vertice current = start;
        Vertice next = null;

        Iterator<Edge> it;
        int myCost = current.costToMe;
        int journeyCost = 0;
        int leastPath = Integer.MAX_VALUE;
        Edge currentEdge;
        Vertice destinationNode;
        current.visited = true;
        it = current.edges.iterator();
        while (it.hasNext()) {
            currentEdge = it.next();
            destinationNode = currentEdge.destination;
            if (destinationNode.visited == true) {
                continue;
            }

            destinationNode.from = current;

            journeyCost = myCost + currentEdge.weight;
            //System.out.println("Journey to: "+destinationNode.name+" is: "+journeyCost);

            if (journeyCost <= destinationNode.costToMe) {
                destinationNode.costToMe = journeyCost;
                destinationNode.from = current;
            }

            if (journeyCost <= leastPath) {
                next = destinationNode;
                leastPath = journeyCost;

            }
        }

        dijkstra2(next, end);
    }
    
    /**
     * Topological sort of graph
     * @param graph  graph to sort
     */
    static public void topologicalSort(Graph graph) {
        Iterator<Graph.Vertice> it = graph.graphPlain.values().iterator();
        Graph.Vertice temp = null;
        //System.out.println("recall");
        while (it.hasNext()) {
            temp = it.next();
            if (temp.froms.isEmpty()) {
                System.out.print(temp.name + " ");
                break;
            }
        }

        if (temp != null) {
            Iterator<Graph.Edge> it2 = temp.edges.iterator();
            while (it2.hasNext()) {
                it2.next().destination.froms.remove(temp);
            }
            graph.graphPlain.remove(temp.name);

        }

        if (!graph.graphPlain.isEmpty()) {
            topologicalSort(graph);
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
        int costToMe;
        Vertice from; //used by dijstra
        ArrayList<Vertice> froms;

        Vertice(String name, T content) {
            this.name = name;
            this.content = content;
            edges = new ArrayList<>();
            visited = false;
            costToMe = Integer.MAX_VALUE;
            from = null;
            froms = new ArrayList<>();
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
     */
    //public static void main(String[] args) {
/*        Graph<String> graph = new Graph<>();
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

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Ibadan"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Benin Republic"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Ondo"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Ibadan"), graph.graphPlain.get("Ondo"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Ondo"), graph.graphPlain.get("Port-Harcourt"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Port-Harcourt"), graph.graphPlain.get("Delta"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Abuja"), 30, 0);
        graph.makeEdge(graph.graphPlain.get("Ibadan"), graph.graphPlain.get("Abuja"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Ibadan"), graph.graphPlain.get("Kwara"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Kano"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Abuja"), graph.graphPlain.get("Kano"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Abuja"), graph.graphPlain.get("Kaduna"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Kano"), graph.graphPlain.get("Kaduna"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Kaduna"), graph.graphPlain.get("Kebbi"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Kebbi"), graph.graphPlain.get("Sokoto"), 20, 0);

        graph.makeEdge(graph.graphPlain.get("Sokoto"), graph.graphPlain.get("Illela"), 20, 0);

        graph.addVertice("Niamey", location[5]);
        graph.addVertice("Rabatt", location[5]);

        graph.makeEdge(graph.graphPlain.get("Illela"), graph.graphPlain.get("Niamey"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Niamey"), graph.graphPlain.get("Rabatt"), 20, 0);
        */
        /*
        graph.addVertice("Barcelona", location[5]);
        graph.addVertice("Rome", location[5]);
        graph.addVertice("Brussels", location[5]);

        graph.makeEdge(graph.graphPlain.get("Rabatt"), graph.graphPlain.get("Barcelona"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("Rome"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("Brussels"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Rome"), graph.graphPlain.get("Brussels"), 20, 2);

        graph.addVertice("London", location[0]);
        graph.addVertice("Leeds", location[0]);
        graph.addVertice("Manchester", location[0]);
        graph.addVertice("Edingbrough", location[0]);
        graph.addVertice("Cambridge", location[0]);
        graph.addVertice("Watford", location[0]);

        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("London"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Barcelona"), graph.graphPlain.get("Manchester"), 20, 2);

        graph.makeEdge(graph.graphPlain.get("Rome"), graph.graphPlain.get("London"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Rome"), graph.graphPlain.get("Manchester"), 20, 2);

        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Manchester"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Leeds"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Watford"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Cambridge"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Leeds"), graph.graphPlain.get("Edingbrough"), 20, 2);

        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Lagos"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Lagos"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("London"), graph.graphPlain.get("Rabatt"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Rabatt"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Manchester"), graph.graphPlain.get("Abuja"), 20, 2);

        graph.addVertice("New York", location[1]);
        graph.addVertice("Seattle", location[1]);
        graph.addVertice("Carlifornia", location[1]);
        graph.addVertice("Pensylvania", location[1]);

        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("London"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Lagos"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Abuja"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Brussels"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("New York"), graph.graphPlain.get("Seattle"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Seattle"), graph.graphPlain.get("Carlifornia"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Carlifornia"), graph.graphPlain.get("Pensylvania"), 20, 2);

        graph.addVertice("Toronto", location[2]);
        graph.addVertice("Montreal", location[2]);
        graph.addVertice("British Columbia", location[2]);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("New York"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("London"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Lagos"), 20, 1);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Montreal"), 20, 0);
        graph.makeEdge(graph.graphPlain.get("Toronto"), graph.graphPlain.get("British Columbia"), 20, 0);

        graph.addVertice("Cape Town", location[4]);
        graph.addVertice("Pretoria", location[4]);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("New York"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("Brussels"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("Pretoria"), 20, 2);
        graph.makeEdge(graph.graphPlain.get("Cape Town"), graph.graphPlain.get("Abuja"), 20, 2);
         */
        /*
        Queue queue = new LinkedList<>();
        Queue<String> prefixQueue = new LinkedList<>();

        //Test Depth first search
        //graph.depthFirstSearch(graph.graphPlain.get("Toronto"), graph.graphPlain.get("Sokoto"), "Toronto");
        //Tests breadth first search
        //graph.breadthFirstSearch(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Cape Town"), prefixQueue, queue, "Lagos");
        //Test Dijsktra....Make sure all cycles in the graph are gotten rid of, else dijstra would get stuck up
        //graph.graphPlain.get("Lagos").costToMe = 0;
        //graph.dijkstra2(graph.graphPlain.get("Lagos"), graph.graphPlain.get("Sokoto"));
        //Test Topological Sort
        //Graph.topologicalSort(graph);
    */
//    }

}
