package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.Queue;

public class Exercise43_ReachableVertexInDigraph {

    public static void main(String[] args) {
        ReachableVertexInDigraph reachableVertexInDigraph = new ReachableVertexInDigraph();

        Digraph digraph1 = new Digraph(4);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(3, 0);

        System.out.println("Reachable 1: " + reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph1)
                + " Expected: true");

        Digraph digraph2 = new Digraph(4);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(2, 3);

        System.out.println("Reachable 2: " + reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph2)
                + " Expected: false");

        Digraph digraph3 = new Digraph(5);
        digraph3.addEdge(0, 1);
        digraph3.addEdge(0, 2);
        digraph3.addEdge(4, 3);
        digraph3.addEdge(3, 1);

        System.out.println("Reachable 3: " + reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph3)
                + " Expected: false");

        Digraph digraph4 = new Digraph(5);
        digraph4.addEdge(0, 1);
        digraph4.addEdge(0, 2);
        digraph4.addEdge(2, 1);
        digraph4.addEdge(4, 3);
        digraph4.addEdge(3, 1);

        System.out.println("Reachable 4: " + reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph4)
                + " Expected: true");

        Digraph digraph5 = new Digraph(6);
        digraph5.addEdge(0, 1);
        digraph5.addEdge(0, 2);
        digraph5.addEdge(2, 0);
        digraph5.addEdge(3, 4);
        digraph5.addEdge(4, 5);
        digraph5.addEdge(5, 3);

        System.out.println("Reachable 5: " + reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph5)
                + " Expected: false");

        Digraph digraph6 = new Digraph(6);
        digraph6.addEdge(0, 1);
        digraph6.addEdge(0, 2);
        digraph6.addEdge(2, 0);
        digraph6.addEdge(3, 4);
        digraph6.addEdge(4, 5);
        digraph6.addEdge(5, 3);
        digraph6.addEdge(2, 3);
        digraph6.addEdge(1, 0);

        System.out.println("Reachable 6: " + reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph6)
                + " Expected: true");


        System.out.println();
        Digraph digraph = new Digraph(13);
        digraph.addEdge(4, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(3, 2);
        digraph.addEdge(6, 0);
        digraph.addEdge(0, 1);
        digraph.addEdge(2, 0);
        digraph.addEdge(11, 12);
        digraph.addEdge(12, 9);
        digraph.addEdge(9, 10);
        digraph.addEdge(9, 11);
        digraph.addEdge(7, 9);
        digraph.addEdge(10, 12);
        digraph.addEdge(11, 4);
        digraph.addEdge(4, 3);
        digraph.addEdge(3, 5);
        digraph.addEdge(6, 8);
        digraph.addEdge(8, 6);
        digraph.addEdge(5, 4);
        digraph.addEdge(0, 5);
        digraph.addEdge(6, 4);
        digraph.addEdge(6, 9);
        digraph.addEdge(7, 6);
        System.out.println(reachableVertexInDigraph.hasVertexReachableFromEveryOtherVertex(digraph));

//        KosarajuSharirSCC scc = new KosarajuSharirSCC(digraph);
//        Queue<Integer>[] components = new Queue[scc.count()];
//        for (int i = 0; i < components.length; ++i) {
//            components[i] = new Queue<>();
//        }
//        for (int v = 0; v < digraph.V(); ++v) {
//            components[scc.id(v)].enqueue(v);
//        }
//        System.out.println("Components");
//        for (int i = 0; i < components.length; ++i) {
//            Queue<Integer> queue = components[i];
//            System.out.println(i + ": " + queue);
//        }
    }

    private static class ReachableVertexInDigraph {

        public boolean hasVertexReachableFromEveryOtherVertex(Digraph digraph) {
            // Run the Kosaraju-sharir algorithm and build the kernel DAG.
            // Though the kernel DAG is a DAG, it follows the same logic of the previous exercise: Should be only 1 sink in the kernel DAG
            KosarajuSharirSCC scc = new KosarajuSharirSCC(digraph);
            Digraph kernelDAG = new Digraph(scc.count());
            for (int v = 0; v < digraph.V(); ++v) {
                for (int w : digraph.adj(v)) {
                    int componentV = scc.id(v);
                    int componentW = scc.id(w);
                    if (componentV != componentW) {
                        kernelDAG.addEdge(componentV, componentW);
                    }
                }
            }

            int[] outdegrees = new int[kernelDAG.V()];
            for (int v = 0; v < kernelDAG.V(); ++v) {
                for (int w : kernelDAG.adj(v)) {
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
