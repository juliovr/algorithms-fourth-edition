package algorithms.chapter4.section2;

import java.util.Iterator;

public class Exercise32_HamiltonianPathInDAG {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(4);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(1, 3);

        System.out.print("Actual:   ");
        Iterable<Integer> path1 = hamiltonianPath(digraph1);
        printHamiltonianPath(path1);
        System.out.println("Expected: 0 1 2 3");


        Digraph digraph2 = new Digraph(8);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(1, 2);
        digraph2.addEdge(2, 5);
        digraph2.addEdge(5, 6);
        digraph2.addEdge(1, 3);
        digraph2.addEdge(3, 4);
        digraph2.addEdge(4, 6);
        digraph2.addEdge(4, 7);

        System.out.print("Actual:   ");
        Iterable<Integer> path2 = hamiltonianPath(digraph2);
        printHamiltonianPath(path2);
        System.out.println("Expected: There is no Hamiltonian Path");


        Digraph digraph3 = new Digraph(4);
        digraph3.addEdge(0, 1);
        digraph3.addEdge(1, 2);
        digraph3.addEdge(2, 3);

        System.out.print("Actual:   ");
        Iterable<Integer> pat3 = hamiltonianPath(digraph3);
        printHamiltonianPath(pat3);
        System.out.println("Expected: 0 1 2 3");
    }

    private static Iterable<Integer> hamiltonianPath(Digraph digraph) {
        Topological topological = new Topological(digraph);
        if (!topological.isDAG()) {
            System.out.println("Digraph is not a DAG");
            return null;
        }

        int count = 1;
        Iterator<Integer> iterator = topological.order().iterator();
        if (iterator.hasNext()) {
            int v = iterator.next();

            while (iterator.hasNext()) {
                int w = iterator.next();

                if (digraph.hasEdge(v, w)) {
                    ++count;
                }

                v = w;
            }
        }

        if (count != digraph.V()) {
            System.out.println("There is no Hamiltonian Path");
            return null;
        }

        return topological.order();
    }

    private static void printHamiltonianPath(Iterable<Integer> path) {
        if (path != null) {
            for (int v : path) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

}
