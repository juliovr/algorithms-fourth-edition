package algorithms.chapter4.section1;

import algorithms.chapter3.section1.ST;
import edu.princeton.cs.algs4.Stack;

public class Exercise30 {

    public static void main(String[] args) {
        Graph graph1 = new Graph(10);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(0, 3);
        graph1.addEdge(1, 3);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 5);
        graph1.addEdge(2, 9);
        graph1.addEdge(3, 6);
        graph1.addEdge(4, 7);
        graph1.addEdge(4, 8);
        graph1.addEdge(5, 8);
        graph1.addEdge(5, 9);
        graph1.addEdge(6, 7);
        graph1.addEdge(6, 9);
        graph1.addEdge(7, 8);

        Graph graph2 = new Graph(10);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(0, 3);
        graph2.addEdge(1, 3);
        graph2.addEdge(0, 3);
        graph2.addEdge(2, 5);
        graph2.addEdge(5, 6);
        graph2.addEdge(3, 6);
        graph2.addEdge(4, 7);
        graph2.addEdge(4, 8);
        graph2.addEdge(5, 8);
        graph2.addEdge(5, 9);
        graph2.addEdge(6, 7);
        graph2.addEdge(6, 9);
        graph2.addEdge(8, 8);

        Graph graph3 = new Graph(10);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);
        graph3.addEdge(1, 3);
        graph3.addEdge(0, 3);
        graph3.addEdge(0, 4);
        graph3.addEdge(2, 5);
        graph3.addEdge(2, 9);
        graph3.addEdge(3, 6);
        graph3.addEdge(4, 7);
        graph3.addEdge(4, 8);
        graph3.addEdge(5, 8);
        graph3.addEdge(5, 9);
        graph3.addEdge(6, 7);
        graph3.addEdge(6, 9);
        graph3.addEdge(7, 8);

        Graph graph4 = new Graph(10);
        graph4.addEdge(4, 1);
        graph4.addEdge(7, 9);
        graph4.addEdge(6, 2);
        graph4.addEdge(7, 3);
        graph4.addEdge(5, 0);
        graph4.addEdge(0, 2);
        graph4.addEdge(0, 8);
        graph4.addEdge(1, 6);
        graph4.addEdge(3, 9);
        graph4.addEdge(6, 3);
        graph4.addEdge(2, 8);
        graph4.addEdge(1, 5);
        graph4.addEdge(9, 8);
        graph4.addEdge(4, 5);
        graph4.addEdge(4, 7);

        System.out.println("Graph 1:");
        System.out.println("    Eulerian cycles? " + (haveEulerianCycles(graph1) ? "Yes" : "No"));

        System.out.println("Graph 2:");
        System.out.println("    Eulerian cycles? " + (haveEulerianCycles(graph2) ? "Yes" : "No"));

        System.out.println("Graph 3:");
        System.out.println("    Eulerian cycles? " + (haveEulerianCycles(graph3) ? "Yes" : "No"));

        System.out.println("Graph 4:");
        System.out.println("    Eulerian cycles? " + (haveEulerianCycles(graph4) ? "Yes" : "No"));
    }

    private static boolean haveEulerianCycles(Graph graph) {
        boolean[] marked = new boolean[graph.V()];
        int vertexOddDegree = 0;

        for (int s = 0; s < graph.V(); ++s) {
            Stack<Integer> paths = new Stack<>();
            paths.push(s);
            while (!paths.isEmpty()) {
                int v = paths.pop();
                if (marked[v]) {
                    continue;
                }

                marked[v] = true;
                Stack<Integer> adj = new Stack<>();
                int degree = 0;
                for (int w : graph.adj(v)) {
                    ++degree;
                    if (!marked[w]) {
                        adj.push(w);
                    }
                }

                if (degree % 2 != 0) {
                    ++vertexOddDegree;
                }

                while (!adj.isEmpty()) {
                    paths.push(adj.pop());
                }
            }
        }

        boolean haveCycle = (vertexOddDegree == 0);

        return haveCycle;
    }

}
