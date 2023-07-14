package algorithms.chapter4.section2;

public class Exercise42_ReachableVertexInDAG {

    public static void main(String[] args) {
        ReachableVertexInDAG reachableVertexInDAG = new ReachableVertexInDAG();

        Digraph digraph1 = new Digraph(4);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);

        System.out.println("Reachable 1: " + reachableVertexInDAG.hasVertexReachableFromEveryOtherVertex(digraph1)
                + " Expected: true");

        Digraph digraph2 = new Digraph(4);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(2, 3);

        System.out.println("Reachable 2: " + reachableVertexInDAG.hasVertexReachableFromEveryOtherVertex(digraph2)
                + " Expected: false");

        Digraph digraph3 = new Digraph(5);
        digraph3.addEdge(0, 1);
        digraph3.addEdge(0, 2);
        digraph3.addEdge(4, 3);
        digraph3.addEdge(3, 1);

        System.out.println("Reachable 3: " + reachableVertexInDAG.hasVertexReachableFromEveryOtherVertex(digraph3)
                + " Expected: false");

        Digraph digraph4 = new Digraph(5);
        digraph4.addEdge(0, 1);
        digraph4.addEdge(0, 2);
        digraph4.addEdge(2, 1);
        digraph4.addEdge(4, 3);
        digraph4.addEdge(3, 1);

        System.out.println("Reachable 4: " + reachableVertexInDAG.hasVertexReachableFromEveryOtherVertex(digraph4)
                + " Expected: true");
    }

    public static class ReachableVertexInDAG {

        /**
         * In DAG a vertex would be reachable from every other vertex if and only if there is only 1 sink vertex.
         * If there are more than 1, the other sinks would not be able to reach the other ones.
         */
        public boolean hasVertexReachableFromEveryOtherVertex(Digraph digraph) {
            DirectedCycle cycle = new DirectedCycle(digraph);
            if (cycle.hasCycle()) {
                System.out.println("Digraph is not a DAG");
                return false;
            }

            int[] outdegrees = new int[digraph.V()];
            for (int v = 0; v < digraph.V(); ++v) {
                for (int w : digraph.adj(v)) {
                    ++outdegrees[v];
                }
            }

            int sinks = 0;
            for (int v = 0; v < outdegrees.length; ++v) {
                if (outdegrees[v] == 0) {
                    ++sinks;
                    if (sinks > 1) {
                        break;
                    }
                }
            }

            return sinks == 1;
        }

    }
    
}
