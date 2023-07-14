package algorithms.chapter4.section2;

import java.util.ArrayList;
import java.util.List;

public class Exercise29_LCA_in_a_DAG {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(4);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(1, 3);
        DirectedCycle directedCycle1 = new DirectedCycle(digraph1);
        System.out.print("Actual:   ");
        if (!directedCycle1.hasCycle()) {
            calculateLCA(digraph1, 2, 3);
        } else {
            System.out.println("Digraph is not a DAG");
        }
        System.out.println("Expected: LCA = 1");


        Digraph digraph2 = new Digraph(8);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(1, 2);
        digraph2.addEdge(2, 5);
        digraph2.addEdge(5, 6);
        digraph2.addEdge(1, 3);
        digraph2.addEdge(3, 4);
        digraph2.addEdge(4, 6);
        digraph2.addEdge(4, 7);
        DirectedCycle directedCycle2 = new DirectedCycle(digraph2);
        System.out.print("Actual:   ");
        if (!directedCycle2.hasCycle()) {
            System.out.println("LCA = " + calculateLCA(digraph2, 5, 4));
        } else {
            System.out.println("Digraph is not a DAG");
        }
        System.out.println("Expected: LCA = 1");

        System.out.print("Actual:   ");
        if (!directedCycle2.hasCycle()) {
            System.out.println("LCA = " + calculateLCA(digraph2, 2, 3));
        } else {
            System.out.println("Digraph is not a DAG");
        }
        System.out.println("Expected: LCA = 1");

        System.out.print("Actual:   ");
        if (!directedCycle2.hasCycle()) {
            System.out.println("LCA = " + calculateLCA(digraph2, 6, 7));
        } else {
            System.out.println("Digraph is not a DAG");
        }
        System.out.println("Expected: LCA = 4");
    }

    private static int source(Digraph digraph) {
        Degrees degrees = new Degrees(digraph);
        if (degrees.sources().iterator().hasNext()) {
            return degrees.sources().iterator().next();
        }

        return -1;
    }

    private static int calculateLCA(Digraph digraph, int v, int w) {
        int s = source(digraph);
        DirectedDFS dfs = new DirectedDFS(digraph, s);

        int[] height = new int[digraph.V()];
        for (int i = 0; i < digraph.V(); ++i) {
            int vertexHeight = 0;
            for (int path : dfs.pathTo(i)) {
                ++vertexHeight;
            }
            height[i] = vertexHeight;
        }

        List<Integer> ancestors = new ArrayList<>();
        Iterable<Integer> pathToV = dfs.pathTo(v);
        Iterable<Integer> pathToW = dfs.pathTo(w);
        for (int vertexV : pathToV) {
            for (int vertexW : pathToW) {
                if (vertexV == vertexW) {
                    ancestors.add(vertexW);
                }
            }
        }

        int maxHeight = -1;
        int lca = -1;
        for (int i = 0; i < ancestors.size(); ++i) {
            int ancestor = ancestors.get(i);
            if (height[ancestor] > maxHeight) {
                maxHeight = height[ancestor];
                lca = ancestor;
            }
        }

        return lca;
    }

}
