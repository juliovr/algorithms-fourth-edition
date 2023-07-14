package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.Queue;

public class Exercise39_QueueBasedTopologicalSort {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(5);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(0, 2);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(3, 4);

        TopologicalQueueBased topologicalOrder1 = new TopologicalQueueBased(digraph1);

        System.out.println("Topological order 1: ");

        for (int vertex : topologicalOrder1.order()) {
            System.out.print(vertex + " ");
        }
        System.out.println("\nExpected: 0 1 2 3 4");

        Digraph digraph2 = new Digraph(6);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(1, 2);
        digraph2.addEdge(3, 4);
        digraph2.addEdge(4, 5);

        TopologicalQueueBased topologicalOrder2 = new TopologicalQueueBased(digraph2);

        System.out.println("\nTopological order 2: ");

        for (int vertex : topologicalOrder2.order()) {
            System.out.print(vertex + " ");
        }
        System.out.println("\nExpected: 0 3 1 4 2 5 ");

        Digraph digraph3 = new Digraph(9);
        digraph3.addEdge(0, 1);
        digraph3.addEdge(1, 2);
        digraph3.addEdge(1, 3);

        digraph3.addEdge(4, 5);
        digraph3.addEdge(5, 6);
        digraph3.addEdge(6, 8);
        digraph3.addEdge(6, 7);
        digraph3.addEdge(7, 2);
        digraph3.addEdge(8, 3);

        TopologicalQueueBased topologicalOrder3 = new TopologicalQueueBased(digraph3);

        System.out.println("\nTopological order 3: ");

        for (int vertex : topologicalOrder3.order()) {
            System.out.print(vertex + " ");
        }
        System.out.println("\nExpected: 0 4 1 5 6 7 8 2 3");
    }

    private static class TopologicalQueueBased {

        private Queue<Integer> order = new Queue<>();

        public TopologicalQueueBased(Digraph digraph) {
            int[] indegree = new int[digraph.V()];
            Queue<Integer> sources = new Queue<>();

            for (int v = 0; v < digraph.V(); ++v) {
                for (int w : digraph.adj(v)) {
                    ++indegree[w];
                }
            }

            for (int v = 0; v < digraph.V(); ++v) {
                if (indegree[v] == 0) {
                    sources.enqueue(v);
                }
            }

            while (!sources.isEmpty()) {
                int v = sources.dequeue();
                order.enqueue(v);

                for (int w : digraph.adj(v)) {
                    --indegree[w];
                    if (indegree[w] == 0) {
                        sources.enqueue(w);
                    }
                }
            }
        }

        public Iterable<Integer> order() {
            return order;
        }

    }

}
