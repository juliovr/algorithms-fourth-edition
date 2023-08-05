package algorithms.chapter4.section4;

public class Topological {

    private Iterable<Integer> order;

    public Topological(EdgeWeightedDigraph digraph) {
        EdgeWeightedDirectedCycle directedCycle = new EdgeWeightedDirectedCycle(digraph);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.reversePostorder();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

}
