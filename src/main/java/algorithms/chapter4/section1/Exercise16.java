package algorithms.chapter4.section1;

public class Exercise16 {

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

        GraphProperties graphProperties = new GraphProperties(graph);
        System.out.println("Diameter: " + graphProperties.diameter() + " Expected: 10");
        System.out.println("Radius: " + graphProperties.radius() + " Expected: 5");
        System.out.println("Center: " + graphProperties.center() + " Expected: 5");
    }

}
