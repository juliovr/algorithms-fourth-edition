package algorithms.chapter4.section2;

public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
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
