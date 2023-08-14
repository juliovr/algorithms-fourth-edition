package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Exercise23_SourceSinkShortestPaths {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(8);
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 1, 0.1));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 2, 0.2));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 5, 0.3));
        edgeWeightedDigraph.addEdge(new DirectedEdge(2, 3, 0.24));
        edgeWeightedDigraph.addEdge(new DirectedEdge(2, 4, 0.51));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 6, 0.22));
        edgeWeightedDigraph.addEdge(new DirectedEdge(4, 3, 0.12));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 3, 0.11));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 7, 0.6));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 4, 0.21));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 7, 0.6));

        DijkstraSPSourceSink dijkstraSPSourceSink1 = new DijkstraSPSourceSink(edgeWeightedDigraph);

        System.out.println("Has path to target sink 7?: " + dijkstraSPSourceSink1.hasPathToSink() + " Expected: true");
        System.out.println("Distance from source 0 to sink 7: " + dijkstraSPSourceSink1.distToSink() + " Expected: 1.0");

        System.out.print("Path to sink 7: ");

        for (DirectedEdge edge : dijkstraSPSourceSink1.pathToSink()) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected:       0->1 1->5 5->7");
    }

    private static class DijkstraSPSourceSink {

        private double spDistToSink = Double.POSITIVE_INFINITY;
        private Iterable<DirectedEdge> spToSink;

        private Queue<Integer> sources = new Queue<>();
        private Queue<Integer> sinks = new Queue<>();

        public DijkstraSPSourceSink(EdgeWeightedDigraph graph) {
            int[] indegrees = new int[graph.V()];
            int[] outdegrees = new int[graph.V()];

            for (int v = 0; v < graph.V(); ++v) {
                for (DirectedEdge e : graph.adj(v)) {
                    ++indegrees[e.to()];
                    ++outdegrees[e.from()];
                }
            }

            for (int v = 0; v < graph.V(); ++v) {
                if (indegrees[v] == 0) {
                    sources.enqueue(v);
                }

                if (outdegrees[v] == 0) {
                    sinks.enqueue(v);
                }
            }

            for (int source : sources) {
                DijkstraSP sp = new DijkstraSP(graph, source);
                for (int sink : sinks) {
                    if (sp.hasPathTo(sink)) {
                        double distToSink = sp.distTo(sink);
                        if (spDistToSink > distToSink) {
                            spDistToSink = distToSink;
                            spToSink = sp.pathTo(sink);
                        }
                    }
                }
            }
        }

        public double distToSink() {
            return spDistToSink;
        }

        public boolean hasPathToSink() {
            return spToSink != null;
        }

        public Iterable<DirectedEdge> pathToSink() {
            return spToSink;
        }

    }
    
}
