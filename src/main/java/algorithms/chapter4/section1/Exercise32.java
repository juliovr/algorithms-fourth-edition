package algorithms.chapter4.section1;

public class Exercise32 {

    public static void main(String[] args) {
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        System.out.println("Parallel edges: " + countParallelEdges(graph1) + " Expected: 0");

        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 0);
        graph2.addEdge(3, 2);
        System.out.println("Parallel edges: " + countParallelEdges(graph2) + " Expected: 1");

        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 0);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 0);
        System.out.println("Parallel edges: " + countParallelEdges(graph3) + " Expected: 3");
    }

    private static int countParallelEdges(Graph graph) {
        int parallelEdges = 0;
        int[] neighbors = new int[graph.V()];

        int iteration = 1;
        for (int v = 0; v < graph.V(); ++v) {
            for (int w : graph.adj(v)) {
                if (neighbors[w] != iteration) {
                    neighbors[w] = iteration;
                } else {
                    ++parallelEdges;
                }
            }

            ++iteration;
        }

        return parallelEdges / 2;
    }

}
