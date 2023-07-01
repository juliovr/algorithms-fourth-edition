package algorithms.chapter4.section1;

import algorithms.chapter3.section1.ST;
import algorithms.chapter3.section4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Exercise34_SymbolGraphOnePass {

    public static void main(String[] args) {
        SymbolGraph symbolGraph = new SymbolGraph("test_data/movies.txt", "/");
        SymbolGraphOnePass symbolGraphOnePass = new SymbolGraphOnePass("test_data/movies.txt", "/");

        if (symbolGraph.graph().toString().equals(symbolGraphOnePass.graph.toString())) {
            System.out.println("Equals");
        } else {
            System.out.println("Not equals");
        }
    }

    private static class InternalGraph {

        private int V = 0;
        private int E = 0;
        private SeparateChainingHashST<Integer, Bag<Integer>> adj; // The primary modification is to change this from an array of Bags to a HashMap of Bags.

        public InternalGraph() {
            adj = new SeparateChainingHashST<>();
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        public void addEdge(int v, int w) {
            if (!adj.contains(v)) {
                adj.put(v, new Bag<>());
                V++;
            }

            if (!adj.contains(w)) {
                adj.put(w, new Bag<>());
                V++;
            }

            adj.get(v).add(w);
            adj.get(w).add(v);
            E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj.get(v);
        }

        public boolean hasEdge(int v, int w) {
            if (v >= V || w >= V) return false;

            for (int adjV : adj.get(v)) {
                if (adjV == w) return true;
            }

            return false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int v = 0; v < V; ++v) {
                sb.append(v + ": ");
                Bag<Integer> value = adj.get(v);
                for (Integer val : value) {
                    sb.append(val + " ");
                }

                sb.append("\n");
            }

            return sb.toString();
        }

    }

    public static class SymbolGraphOnePass {

        private ST<String, Integer> st; // String -> index
        private String[] keys;          // index -> String
        private InternalGraph graph;

        public SymbolGraphOnePass(String stream, String separator) {
            // Associate each String with an index.
            st = new ST<>();
            graph = new InternalGraph();
            In in = new In(stream);
            while (in.hasNextLine()) {
                String[] a = in.readLine().split(separator);
                for (int i = 0; i < a.length; ++i) {
                    if (!st.contains(a[i])) {
                        st.put(a[i], st.size());
                    }
                }

                int v = st.get(a[0]);
                for (int i = 1; i < a.length; ++i) {
                    graph.addEdge(v, st.get(a[i]));
                }
            }

            // Inverted index
            keys = new String[st.size()];
            for (String name : st.keys()) {
                keys[st.get(name)] = name;
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

        public InternalGraph graph() {
            return graph;
        }

    }

}
