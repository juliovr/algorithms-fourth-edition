package algorithms.chapter5.section4;

import algorithms.chapter4.section2.Digraph;
import algorithms.chapter4.section2.DirectedDFS;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

public class NFA {

    private char [] re;         // match transitions
    private Digraph graph;      // epsilon transitions
    private int m;              // number of states

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        m = re.length;
        graph = new Digraph(m+1);

        for (int i = 0; i < m; ++i) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    graph.addEdge(lp, or+1);
                    graph.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if (i < m-1 && re[i+1] == '*') {
                graph.addEdge(lp, i+1);
                graph.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                graph.addEdge(i, i+1);
            }
        }
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(graph, 0);
        for (int v = 0; v < graph.V(); ++v) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }

        for (int i = 0; i < txt.length(); ++i) {
            Bag<Integer> states = new Bag<>();
            for (int v : pc) {
                if (v < m) {
                    if (re[v] == txt.charAt(i) || re[v] == '.') {
                        states.add(v+1);
                    }
                }
            }

            pc = new Bag<>();
            dfs = new DirectedDFS(graph, states);
            for (int v = 0; v < graph.V(); ++v) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }

        for (int v : pc) {
            if (v == m) {
                return true;
            }
        }

        return false;
    }

}
