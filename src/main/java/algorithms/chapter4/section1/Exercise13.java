package algorithms.chapter4.section1;

public class Exercise13 {

    public static void main(String[] args) {
        Graph graph1 = new Graph(12);
        graph1.addEdge(8, 4);
        graph1.addEdge(2, 3);
        graph1.addEdge(1, 11);
        graph1.addEdge(0, 6);
        graph1.addEdge(3, 6);
        graph1.addEdge(10, 3);
        graph1.addEdge(7, 11);
        graph1.addEdge(7, 8);
        graph1.addEdge(11, 8);
        graph1.addEdge(2, 0);
        graph1.addEdge(6, 2);
        graph1.addEdge(5, 2);
        graph1.addEdge(5, 10);
        graph1.addEdge(5, 0);
        graph1.addEdge(8, 1);
        graph1.addEdge(4, 1);

        System.out.println(graph1);

        System.out.println("Paths");
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph1, 0);
        for (Integer i : bfp.pathTo(10)) {
            System.out.println(i);
        }

        System.out.println(bfp.distTo(10));
    }

}
