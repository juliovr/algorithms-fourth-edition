package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

public class Exercise18 {

    public static void main(String[] args) {
        // Graph with two critical paths: 0->1->2->3 and 0->4->5
        // Both with total duration of 35
        String[] precedenceConstraints = new String[6]; // first item = duration; other items = successors
        precedenceConstraints[0] = "10 1 4";
        precedenceConstraints[1] = "15 2";
        precedenceConstraints[2] = "5 3";
        precedenceConstraints[3] = "5";
        precedenceConstraints[4] = "20 5";
        precedenceConstraints[5] = "5";

        System.out.println("Actual:");
        printAllCriticalPaths(precedenceConstraints);

        System.out.println("\nExpected: \n0->4->5\n0->1->2->3");
    }

    private static void printAllCriticalPaths(String[] precedenceConstraints) {
        int n = precedenceConstraints.length;
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(2*n + 2);

        int source = 2*n;
        int target = 2*n + 1;


        for (int i = 0; i < n; ++i) {
            String[] precedences = precedenceConstraints[i].split("\\s+");
            if (precedences.length == 0) continue;

            double duration = Double.parseDouble(precedences[0]);

            int start = i;
            int end = i+n;
            graph.addEdge(new DirectedEdge(start, end, duration));
            graph.addEdge(new DirectedEdge(source, start, 0.0));
            graph.addEdge(new DirectedEdge(end, target, 0.0));

            for (int j = 1; j < precedences.length; ++j) {
                int successor = Integer.parseInt(precedences[j]);
                if (successor < graph.V()) {
                    graph.addEdge(new DirectedEdge(end, successor, 0.0));
                }
            }
        }

        CriticalPathsFinder lp = new CriticalPathsFinder(graph, source);
        for (Iterable<DirectedEdge> paths : lp.pathTo(target)) {
            StringBuilder sb = new StringBuilder();
            for (DirectedEdge e : paths) {
                sb.append(e.from() + "->");
            }

            // Remove last "->" added
            if (sb.length() > 2) {
                sb.setLength(sb.length() - 2);
            }

            System.out.println(sb);
        }
    }

    private static class CriticalPathsFinder {

        private List<DirectedEdge>[] edgeTo;
        private double[] distTo;

        public CriticalPathsFinder(EdgeWeightedDigraph graph, int s) {
            edgeTo = new List[graph.V()];
            distTo = new double[graph.V()];

            for (int i = 0; i < edgeTo.length; ++i) {
                edgeTo[i] = new ArrayList<>();
            }

            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.NEGATIVE_INFINITY;
            }

            distTo[s] = 0.0;

            Topological topological = new Topological(graph);

            for (int v : topological.order()) {
                relax(graph, v);
            }
        }

        private void relax(EdgeWeightedDigraph graph, int v) {
            for (DirectedEdge e : graph.adj(v)) {
                int w = e.to();
                if (distTo[w] == distTo[v] + e.weight()) {
                    edgeTo[w].add(e);
                } else if (distTo[w] < distTo[v] + e.weight()) {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w].clear();
                    edgeTo[w].add(e);
                }
            }
        }

        public double distTo(int v) {
            return distTo[v];
        }

        public boolean hasPathTo(int v) {
            return distTo[v] < Double.POSITIVE_INFINITY;
        }

        public Iterable<Iterable<DirectedEdge>> pathTo(int v) {
            if (!hasPathTo(v)) return null;

            Stack<Iterable<DirectedEdge>> paths = new Stack<>();

            Stack<DirectedEdge> path = new Stack<>();

            // Push the last edges
            List<DirectedEdge> edges = edgeTo[v];
            Stack<DirectedEdge> stack = new Stack<>();
            for (DirectedEdge e : edges) {
                stack.push(e);
            }

            // Pop last edges and build the path for each one of them
            while (!stack.isEmpty()) {
                DirectedEdge e = stack.pop();
                if (e.weight() > 0.0) {
                    path.push(e);
                }
                int vertex = e.from();
                if (edgeTo[vertex].isEmpty()) {
                    paths.push(path);
                    path = new Stack<>();
                }

                for (DirectedEdge edge : edgeTo[vertex]) {
                    stack.push(edge);
                }
            }

            return paths;
        }

    }

}
