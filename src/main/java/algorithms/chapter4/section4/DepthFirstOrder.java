package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(EdgeWeightedDigraph digraph) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); ++v) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (DirectedEdge e : digraph.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> preorder() {
        return pre;
    }

    public Iterable<Integer> postorder() {
        return post;
    }

    public Iterable<Integer> reversePostorder() {
        return reversePost;
    }

}
