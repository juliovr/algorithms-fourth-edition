package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.Stack;

public class Exercise40_ShortestDirectedCycle {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(5);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(3, 4);
        digraph1.addEdge(4, 0);

        ShortestDirectedCycle shortestDirectedCycle1 = new ShortestDirectedCycle(digraph1);
        Stack<Integer> directedCycle1 = shortestDirectedCycle1.shortestCycle();

        if (directedCycle1 != null) {
            System.out.println("Shortest cycle 1 length: " + (directedCycle1.size() - 1));
            System.out.print("Actual:   ");
            for (int vertex : directedCycle1) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("Actual:   The digraph is a DAG");
        }
        System.out.println("\nExpected: 0 1 2 3 4 0\n");


        Digraph digraph2 = new Digraph(8);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(1, 2);
        digraph2.addEdge(2, 3);
        digraph2.addEdge(3, 4);
        digraph2.addEdge(4, 0);
        digraph2.addEdge(5, 6);
        digraph2.addEdge(6, 7);
        digraph2.addEdge(7, 5);

        ShortestDirectedCycle shortestDirectedCycle2 = new ShortestDirectedCycle(digraph2);
        Stack<Integer> directedCycle2 = shortestDirectedCycle2.shortestCycle();

        if (directedCycle2 != null) {
            System.out.println("Shortest cycle 2 length: " + (directedCycle2.size() - 1));
            System.out.print("Actual:   ");
            for (int vertex : directedCycle2) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("Actual:   The digraph is a DAG");
        }
        System.out.println("\nExpected: 5 6 7 5\n");

        
        Digraph digraph3 = new Digraph(8);
        digraph3.addEdge(0, 1);
        digraph3.addEdge(1, 2);
        digraph3.addEdge(2, 3);
        digraph3.addEdge(3, 4);
        digraph3.addEdge(4, 3);
        digraph3.addEdge(4, 0);
        digraph3.addEdge(5, 6);
        digraph3.addEdge(6, 7);
        digraph3.addEdge(7, 5);

        ShortestDirectedCycle shortestDirectedCycle3 = new ShortestDirectedCycle(digraph3);
        Stack<Integer> directedCycle3 = shortestDirectedCycle3.shortestCycle();

        if (directedCycle3 != null) {
            System.out.println("Shortest cycle 3 length: " + (directedCycle3.size() - 1));
            System.out.print("Actual:   ");
            for (int vertex : directedCycle3) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("Actual:   The digraph is a DAG");
        }
        System.out.println("\nExpected: 3 4 3\n");

        Digraph digraph4 = new Digraph(5);
        digraph4.addEdge(0, 1);
        digraph4.addEdge(1, 2);
        digraph4.addEdge(3, 4);
        digraph4.addEdge(4, 0);

        ShortestDirectedCycle shortestDirectedCycle4 = new ShortestDirectedCycle(digraph4);
        Stack<Integer> directedCycle4 = shortestDirectedCycle4.shortestCycle();

        if (directedCycle4 != null) {
            System.out.println("Shortest cycle 4 length: " + (directedCycle4.size() - 1));
            System.out.print("Actual:   ");
            for (int vertex : directedCycle4) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("Actual:   The digraph is a DAG");
        }
        System.out.println("Expected: The digraph is a DAG");
    }

    private static class ShortestDirectedCycle {

        private boolean[] marked;
        private int[] edgeTo;
        private Stack<Integer> cycle;
        private boolean[] onStack;

        public ShortestDirectedCycle(Digraph digraph) {
            onStack = new boolean[digraph.V()];
            edgeTo = new int[digraph.V()];
            marked = new boolean[digraph.V()];

            for (int v = 0; v < digraph.V(); ++v) {
                if (!marked[v]) {
                    dfs(digraph, v);
                }
            }
        }

        private void dfs(Digraph digraph, int v) {
            onStack[v] = true;

            marked[v] = true;
            for (int w : digraph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(digraph, w);
                } else if (onStack[w]) {
                    Stack<Integer> currentCycle = new Stack<>();
                    currentCycle.push(w);
                    for (int x = v; x != w; x = edgeTo[x]) {
                        currentCycle.push(x);
                    }
                    currentCycle.push(w);

                    if (cycle == null || currentCycle.size() < cycle.size()) {
                        cycle = currentCycle;
                    }
                }
            }

            onStack[v] = false;
        }

        public boolean hasCycle() {
            return cycle != null;
        }

        public Stack<Integer> shortestCycle() {
            return cycle;
        }

    }

}
