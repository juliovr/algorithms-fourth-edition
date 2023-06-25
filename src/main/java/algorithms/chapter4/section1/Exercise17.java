package algorithms.chapter4.section1;

public class Exercise17 {

    public static void main(String[] args) {
        Graph graph = new Graph(11);
        graph.addEdge(0 ,1);
        graph.addEdge(1 ,2);
        graph.addEdge(2 ,3);
        graph.addEdge(3 ,4);
        graph.addEdge(4 ,5);
        graph.addEdge(5 ,6);
        graph.addEdge(6 ,7);
        graph.addEdge(7 ,8);
        graph.addEdge(8 ,9);
        graph.addEdge(9 ,10);

        // Wiener index
        /**
         *
         Vertex 0: 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 55
         Vertex 1: 1 + 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 46
         Vertex 2: 2 + 1 + 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 39
         Vertex 3: 3 + 2 + 1 + 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 = 34
         Vertex 4: 4 + 3 + 2 + 1 + 0 + 1 + 2 + 3 + 4 + 5 + 6 = 31
         = 205

         205 * 2 = 410 //Same sums for vertices from 6 to 10 as well

         Vertex 5: 5 + 4 + 3 + 2 + 1 + 0 + 1 + 2 + 3 + 4 + 5 = 30

         410 + 30 = 440
         */

        GraphProperties graphProperties = new GraphProperties(graph);
        System.out.println("Wiener index: " + graphProperties.wiener() + " Expected: 440");
    }

}
