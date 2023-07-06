package algorithms.chapter4.section2;

import java.util.ArrayList;
import java.util.List;

public class Exercise9 {

    public static void main(String[] args) {
        CheckTopologicalOrder checkTopologicalOrder = new CheckTopologicalOrder();

        Digraph digraph1 = new Digraph(3);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(0, 2);
        digraph1.addEdge(1, 2);

        List<Integer> topologicalOrder1 = new ArrayList<>();
        topologicalOrder1.add(0);
        topologicalOrder1.add(1);
        topologicalOrder1.add(2);
        boolean isTopologicalOrder1 = checkTopologicalOrder.isTopologicalOrder(digraph1, topologicalOrder1);

        System.out.println("Is topological order: " + isTopologicalOrder1 + " Expected: true");

        List<Integer> topologicalOrder2 = new ArrayList<>();
        topologicalOrder2.add(1);
        topologicalOrder2.add(0);
        topologicalOrder2.add(2);
        boolean isTopologicalOrder2 = checkTopologicalOrder.isTopologicalOrder(digraph1, topologicalOrder2);

        System.out.println("Is topological order: " + isTopologicalOrder2 + " Expected: false");

        Digraph digraph2 = new Digraph(6);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(1, 2);
        digraph2.addEdge(2, 3);
        digraph2.addEdge(4, 5);

        List<Integer> topologicalOrder3 = new ArrayList<>();
        topologicalOrder3.add(4);
        topologicalOrder3.add(5);
        topologicalOrder3.add(0);
        topologicalOrder3.add(1);
        topologicalOrder3.add(2);
        topologicalOrder3.add(3);
        boolean isTopologicalOrder3 = checkTopologicalOrder.isTopologicalOrder(digraph2, topologicalOrder3);

        System.out.println("Is topological order: " + isTopologicalOrder3 + " Expected: true");
    }

    private static class CheckTopologicalOrder {

        public boolean isTopologicalOrder(Digraph digraph, List<Integer> order) {
            if (digraph.V() != order.size()) {
                return false;
            }

            Topological topological = new Topological(digraph);
            if (!topological.isDAG()) {
                return false;
            }

            int index = 0;
            for (int v : topological.order()) {
                if (v != order.get(index)) {
                    return false;
                }

                ++index;
            }

            return true;
        }

    }

}
