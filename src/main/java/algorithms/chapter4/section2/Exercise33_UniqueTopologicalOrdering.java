package algorithms.chapter4.section2;

import java.util.Iterator;

public class Exercise33_UniqueTopologicalOrdering {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(4);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(1, 3);

        System.out.print("Actual:   ");
        Iterable<Integer> path1 = hamiltonianPath(digraph1);
        isUniqueTopologicalOrdering(path1);
        System.out.println("Expected: Unique topological ordering");


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
        isUniqueTopologicalOrdering(path2);
        System.out.println("Expected: There is no unique topological order");


        Digraph digraph3 = new Digraph(4);
        digraph3.addEdge(0, 1);
        digraph3.addEdge(1, 2);
        digraph3.addEdge(2, 3);

        System.out.print("Actual:   ");
        Iterable<Integer> pat3 = hamiltonianPath(digraph3);
        isUniqueTopologicalOrdering(pat3);
        System.out.println("Expected: Unique topological ordering");
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
            System.out.println("There is no unique topological order");
            return null;
        }

        return topological.order();
    }

    private static void isUniqueTopologicalOrdering(Iterable<Integer> path) {
        if (path != null) {
            System.out.println("Unique topological ordering");
        }
    }

}
