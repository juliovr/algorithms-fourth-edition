package algorithms.chapter4.section1;

import algorithms.chapter3.section1.ST;
import edu.princeton.cs.algs4.In;

public class SymbolGraph {

    private ST<String, Integer> st; // String -> index
    private String[] keys;          // index -> String
    private Graph graph;

    public SymbolGraph(String stream, String separator) {
        // Associate each String with an index.
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(separator);
            for (int i = 0; i < a.length; ++i){
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        // Inverted index
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(separator);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; ++i) {
                graph.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String nameOf(int v) {
        return keys[v];
    }

    public Graph graph() {
        return graph;
    }

}
