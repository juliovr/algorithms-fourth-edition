package algorithms.chapter4.section4;

public class DijkstraAllPairsSP {

    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph graph) {
        all = new DijkstraSP[graph.V()];
        for (int v = 0; v < graph.V(); ++v) {
            all[v] = new DijkstraSP(graph, v);
        }
    }

    public boolean hasPathTo(int s, int t) {
        return all[s].hasPathTo(t);
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].distTo(t);
    }

    public DijkstraSP[] all() {
        return all;
    }

}
