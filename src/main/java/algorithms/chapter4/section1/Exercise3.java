package algorithms.chapter4.section1;

public class Exercise3 {

    public static void main(String[] args) {
        Graph graph1 = new Graph(5);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 0);
        graph1.addEdge(4, 3);

        Graph graph2 = new Graph(graph1);

        graph1.addEdge(2, 1);

        graph2.addEdge(3, 0);

        System.out.println("Graph1:");
        System.out.println(graph1);
        System.out.println();
        System.out.println("Graph2:");
        System.out.println(graph2);

        System.out.println("end");
    }

}
