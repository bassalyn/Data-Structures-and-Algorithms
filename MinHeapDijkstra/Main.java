import java.io.*;
import java.util.*;       //import java io and util

public class Main {       //main function

    private static int numVertex;  //variable for number of vertexs of input graph

    public static void main(String[] args){

        String file = null;
        if (args.length == 1){
            file = args[0];           //if there is one arg then set it to the file
        }else{
            System.out.println("There was no input file in the argument."); //otherwise warn user
            return;
        }

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));  //use buffered reader to parse file
            String line = reader.readLine();
            numVertex = Integer.parseInt(line);                 //first line is the number of vertex
            Graph g = new Graph(numVertex);                     //create a new graph with the given number of vertex
            while ((line = reader.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line); //while file not null read lines using string tokenizer
                while (st.hasMoreTokens()){
                    int id = Integer.parseInt(st.nextToken());    // convert the tuple on each line to values
                    int dest = Integer.parseInt(st.nextToken());
                    int weight = Integer.parseInt(st.nextToken());
                    g.addEdge(id,dest,weight);                   // add the tuple as a weighted edge to the graph
                }
            }
            g.printGraph();                                      //print the graph using function to show edges
            int[] keys = new int[numVertex+1];                   //create array of keys to input to algorithm
            keys[1] = 0;
            keys[0] = Integer.MAX_VALUE;                         //set first value to 0 and others to max
            for (int i =2; i<numVertex+1; i++){
                keys[i] = Integer.MAX_VALUE;
            }
            g.dijkstra(g, keys, numVertex);                      //calls dijkstras for graph with given inputa

        } catch (FileNotFoundException e) {
            e.printStackTrace();                                  //catch errors
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Node{                                     //node class
        int vertex;                                               //contains id and weight
        int weight;

        Node(int v, int w){
            this.vertex = v;                                     //constructor for node
            this.weight = w;
        }
    }

    public static class Graph {                                  //graph class
        int numVertices;
        LinkedList<Node> adjLists[];                             //has int for number of vertices and list of edges

        Graph(int v) {
            numVertices = v;                                     //constructor for the graph
            adjLists = new LinkedList[v+1];
            for (int i = 1; i < numVertices+1; i++) {
                adjLists[i] = new LinkedList<>();                //generates list of edges
            }
        }
        void addEdge(int src, int dest, int weight) {            //function adds a weighted edge with an input tuple
            adjLists[src].add(new Node(dest,weight));
        }

        void printGraph(){                                      //function outputs the graph and each vertices edges
            System.out.println("The input graph in list representation format is: ");
            for (int i = 1; i < numVertex +1; i++){
                System.out.println("The vertex " + i + " has the following edges");
                for (int j = 0; j < adjLists[i].size(); j++){                //prints each edge for each vertex
                    System.out.println("(" + i + "," + adjLists[i].get(j).vertex + ")");
                }
            }
        }

        void dijkstra(Graph g, int[] source, int size){                  //implementation of dijkstras using min heap
            ArrayList<Integer> spt = new ArrayList<>();                  //arraylist to shortest path tree
            heap hp = new heap(source, size);                            //heapify the input source array
            while (!hp.is_empty()){                                      // while heap not empty
                int w = hp.min_key();                                    //finds minimum id and weight
                int u = hp.min_id();
                spt.add(u);                                              //adds to shortest path tree
                hp.delete_min();                                        // delete minimum from the array
                for (int i= 0; i< g.adjLists[u].size(); i++){           //for each edge of u update key weights
                    hp.decrease_key(g.adjLists[u].get(i).vertex, w + g.adjLists[u].get(i).weight);
                }
            }
            System.out.println("The shortest path tree output produced by Dijkstra's in order is: ");
            for (int i = 0; i< spt.size(); i++){           //prints and displays the output produced by dijkstras
                System.out.println("Vertex: " + spt.get(i));
            }
        }
    }
}
