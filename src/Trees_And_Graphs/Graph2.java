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

/**
 * <b>Graph Implementation 2</b>
 * <ol>
 * <li>Add vertices</li>
 * <li>Delete vertices</li>
 * <li>Depth First Search</li>
 * <li>Breadth First Search</li>
 * <li>Dijkstra</li>
 * <li>Topological Sort</li>
 * </ol>
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Graph2<T> {

    HashMap<String, Vertice> graphPlain;

    /**
     * Default constructor
     */
    Graph2() {
        graphPlain = new HashMap<>();
    }

    /**
     * Add vertice to graph plain
     *
     * @param name label of vertice to add
     * @param content content of vertice
     */
    public void addVertice(String name, T content) {
        if (content == null) {
            return;
        }
        Vertice newVertice = new Vertice(name, content);
        graphPlain.put(newVertice.name, newVertice); //Hash map, so same key-entry will be replaced
    }

    /**
     * Delete vertice from graph
     *
     * @param name label of vertice to delete
     * @return true/false true if successful and false if other wise
     */
    public boolean delete(String name) {
        //make sure vertic exist in graph
        if (graphPlain.containsKey(name) == false) {
            System.out.println("No such vertice with name: " + name);
            return false;
        }

        //get vertice
        Vertice toRemove = graphPlain.get(name);
        ArrayList<Edge> edgesToRemove = new ArrayList<Edge>();

        
        
        //Mak sure all vertices pointing to it are updated
        Iterator<Edge> it0 = toRemove.edges.iterator();
        Edge edge0=null;
        Edge edge1=null;
        while(it0.hasNext()){
            edge0 = it0.next();
            edge0.destination.froms.remove(toRemove); //delete from the froms list of all the node it points to
            Iterator<Edge> it1 = edge0.destination.edges.iterator();
            while (it1.hasNext()) { //iterate through its neighbours edges
                edge1 = it1.next();
                if (edge1.destination == toRemove) { //when you find one pointing to this, remove
                    edgesToRemove.add(edge1);
                }
            }
            edge0.destination.edges.removeAll(edgesToRemove);
        }

        //now remove the vertice
        graphPlain.remove(name);
        return true;
    }

    /**
     * Create edge
     *
     * @param nameOfA node on the edge
     * @param nameOfB second node attached to this edge
     * @param weight weight of the edge
     * @param direction direction (0=from a to b), (1=from b to a) and
     * (2=bi-directional)
     * @return true/false true if successful and false if other wise
     */
    public boolean connect(String nameOfA, String nameOfB, int weight, int direction) {
        if (nameOfA == null || nameOfB == null || direction > 2 || direction < 0) {
            System.out.println("Connection parameter not right. Please check");
            return false;
        }

        Vertice verticeA = graphPlain.get(nameOfA);
        Vertice verticeB = graphPlain.get(nameOfB);

        if (verticeA == null || verticeB == null) {
            System.out.println("One of the vertice required for connection is not in graph");
            return false;
        }

        Edge newEdgeToB = new Edge(verticeB, weight);
        Edge newEdgeToA = new Edge(verticeA, weight);
        if (direction == 0) {//going into b;
            verticeA.edges.add(newEdgeToB);
            verticeB.froms.add(verticeA);
        } else if (direction == 1) { //going into a;
            verticeB.edges.add(newEdgeToA);
            verticeA.froms.add(verticeB);
        } else if (direction == 2) {//going both ways
            verticeA.edges.add(newEdgeToB);
            verticeB.edges.add(newEdgeToA);
            verticeA.froms.add(verticeB);
            verticeB.froms.add(verticeA);
        }
        return true;
    }

    /**
     * Depth first search of graph
     *
     * @param start start node
     * @param stop stop node
     * @param prefix prefix
     */
    public void depthFirstSearch(Vertice start, Vertice stop, String prefix) {
        if (start == null || stop == null) {
            return;
        }

        if (start == stop) {
            System.out.println(prefix);
            return;
        }

        ArrayList<Edge> edges = new ArrayList<Edge>();
        Iterator<Edge> it;
        Edge edge;
        edges = start.edges;
        it = edges.iterator();
        while (it.hasNext()) {
            edge = it.next();
            if (edge.destination.visited != true) {
                edge.destination.visited = true;
                depthFirstSearch(edge.destination, stop, prefix + ", " + edge.destination.name);
            }
        }
    }

    /**
     * Breadth first search through a graph
     *
     * @param start start vertice
     * @param stop stop vertice
     * @param prefixQueue prefix queue
     * @param verticeQueue vertice queue
     * @param prefix prefix string
     */
    public void breadthFirstSearch(Vertice start, Vertice stop, Queue<Vertice> verticeQueue, Queue<String> prefixQueue, String prefix) {
        if (start == null || stop == null) {
            return;
        }

        if (start == stop) {
            System.out.println(prefix);
            return;
        }

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges = start.edges;

        start.visited = true;

        Iterator<Edge> it;
        it = edges.iterator();

        Edge edge;
        while (it.hasNext()) {
            edge = it.next();

            if (edge.destination.visited == false) { //has edge been visited before
                edge.destination.visited = true;
                if (edge.destination == stop) {
                    System.out.println(prefix + ", " + stop.name);
                    return;
                } else {
                    verticeQueue.add(edge.destination);
                    prefixQueue.add(prefix);
                }
            }
        }

        int size = verticeQueue.size();
        Vertice tempVertice;
        String tempPrefix;
        for (int i = 0; i < size; i++) {
            tempVertice = verticeQueue.poll();
            tempPrefix = prefixQueue.poll();
            if (tempVertice != null) {
                breadthFirstSearch(tempVertice, stop, verticeQueue, prefixQueue, tempPrefix + ", " + tempVertice.name);
            }
        }
    }

    /**
     * Find shortest path between two vertices
     *
     * @param start start vertice
     * @param stop end vertice
     */
    public void dijkstra(Vertice start, Vertice stop) {
        if (start == null || stop == null) {
            return;
        }

        if (start == stop) {
            ArrayList names = new ArrayList();
            names.add(stop.name);
            Vertice current = stop.from;
            while (current != null) {
                names.add(current.name);
                current = current.from;
            }

            //track route backwards
            int size = names.size();
            System.out.println("\n");
            for (int j = size - 1; j >= 0; j--) {
                if (j != 0) {
                    System.out.print(names.get(j) + ", ");
                } else {
                    System.out.print(names.get(j));
                }
            }
            System.out.println("\n");
        }

        //check neighbours of vertice
        Iterator<Edge> it = start.edges.iterator();
        start.visited = true;
        Edge edge;
        int neighbourNewCost = 0;
        int closestNeighbourCost = 0;
        Vertice closestNeighbour = null;
        int cnt = 0;
        while (it.hasNext()) {
            edge = it.next();
            if (edge.destination.visited == false) {
                //first neighbour will start as being the closes neigbour. Change as the nex() iteration continues
                if (cnt == 0) {
                    closestNeighbourCost = edge.destination.myCost + edge.weight;
                    closestNeighbour = edge.destination;
                    cnt++;
                }
                //compute cost to them
                neighbourNewCost = edge.weight + start.myCost; //edge weight  + my cost
                //update their costs
                if (neighbourNewCost <= edge.destination.myCost) {
                    edge.destination.myCost = neighbourNewCost;
                    edge.destination.from = start;
                }
                if (neighbourNewCost <= closestNeighbourCost) {
                    closestNeighbourCost = neighbourNewCost;
                    closestNeighbour = edge.destination;
                }
            }
        }

        //go to the vertice with the least cost
        //repeat till end
        dijkstra(closestNeighbour, stop);
    }

    public void topologicalSort() {
        if (graphPlain.isEmpty()) {
            //System.out.println("Graph is empty");
            return;
        }
        //find vertice in graph that has no node pointing to it
        Iterator<Vertice> it = graphPlain.values().iterator();
        ArrayList<Vertice> froms;
        Vertice current = null;
        boolean done=false;
        while (it.hasNext()) {
            current = it.next();
            froms = current.froms;
            if (froms.isEmpty()) {
                done=true;
                break;
            }
        }

        if (current != null && done ==true) {
            System.out.print(current.name+", ");
            delete(current.name);
            topologicalSort();
        }
    }

    /**
     * Vertice class
     */
    public class Vertice {

        String name;
        T content;
        int myCost;
        ArrayList edges;
        ArrayList<Vertice> froms;
        Vertice from;
        boolean visited;

        public Vertice(String name, T content) {
            this.name = name;
            this.content = content;
            edges = new ArrayList();
            froms = new ArrayList();
            visited = false;
            from = null;
            myCost = Integer.MAX_VALUE;
        }
    }

    /**
     * Edge class
     */
    public class Edge {

        //Vertice source;
        Vertice destination;
        int weight;

        Edge(Vertice destination, int weight) {
            // this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    /**
     * Graph main/test method....uncomment to run
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        Graph2<String> graph = new Graph2<>();
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

        graph.connect("Lagos", "Ibadan", 4, 0);
        graph.connect("Lagos", "Benin Republic", 3, 0);
        graph.connect("Lagos", "Ondo", 8, 0);
        graph.connect("Ibadan", "Ondo", 6, 0);
        graph.connect("Ondo", "Port-Harcourt", 7, 0);
        graph.connect("Port-Harcourt", "Delta", 3, 0);
        graph.connect("Delta", "Kaduna", 13, 0);
        graph.connect("Lagos", "Abuja", 25, 0);
        graph.connect("Ibadan", "Abuja", 22, 0);
        graph.connect("Ibadan", "Kwara", 3, 0);
        graph.connect("Kwara", "Kaduna", 5, 0);
        graph.connect("Lagos", "Kano", 30, 0);
        graph.connect("Abuja", "Kano", 7, 0);
        graph.connect("Abuja", "Kaduna", 5, 0);
        graph.connect("Kano", "Kaduna", 3, 0);
        graph.connect("Kaduna", "Kebbi", 5, 0);
        graph.connect("Kebbi", "Sokoto", 2, 0);
        graph.connect("Sokoto", "Illela", 1, 0);
        graph.addVertice("Niamey", location[5]);
        graph.addVertice("Rabatt", location[5]);
        graph.connect("Illela", "Niamey", 20, 0);
        graph.connect("Niamey", "Rabatt", 20, 0);

        Graph2.Vertice lagos = graph.graphPlain.get("Lagos");
        Graph2.Vertice sokoto = graph.graphPlain.get("Sokoto");

        //test depth first search        
        //graph.depthFirstSearch(lagos, sokoto, "lagos");
        //test bread first search
        Queue verticeQueue = new LinkedList();
        Queue prefixQueue = new LinkedList();
        //graph.breadthFirstSearch(lagos, sokoto, verticeQueue, prefixQueue, "lagos");

        //test dijksta
        lagos.myCost = 0;
        //graph.dijkstra(lagos, sokoto);
        
        //test topological sort
        graph.topologicalSort();

    }
*/
}
