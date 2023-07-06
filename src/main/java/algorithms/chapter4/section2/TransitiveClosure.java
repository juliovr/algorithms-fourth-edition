package algorithms.chapter4.section2;

public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph digraph) {
        all = new DirectedDFS[digraph.V()];
        for (int v = 0; v < digraph.V(); ++v) {
            all[v] = new DirectedDFS(digraph, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < all.length; ++i) {
            sb.append(i + " ");
        }

        sb.append("\n");

        for (int i = 0; i < all.length; ++i) {
            sb.append(i + " " + all[i] + "\n");
        }

        return sb.toString();
    }

}
