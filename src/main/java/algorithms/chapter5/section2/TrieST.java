package algorithms.chapter5.section2;

import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {

    private final static int R = 256;
    private Node root = new Node();

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }

        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        if (d == key.length()) {
            return x;
        }

        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }

        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<>();
        Node firstNodeMatchingPrefix = get(root, prefix, 0);
        collect(firstNodeMatchingPrefix, prefix, queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> queue) {
        if (x == null) {
            return;
        }

        if (x.val != null) {
            queue.enqueue(prefix);
        }

        for (char c = 0; c < R; ++c) {
            collect(x.next[c], prefix + c, queue);
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<>();
        collect(root, "", pattern, queue);
        return queue;
    }

    private void collect(Node x, String prefix, String pattern, Queue<String> queue) {
        int d = prefix.length();
        if (x == null) {
            return;
        }

        if (d == pattern.length() && x.val != null) {
            queue.enqueue(prefix);
        }

        if (d == pattern.length()) {
            return;
        }

        char next = pattern.charAt(d);
        for (char c = 0; c < R; ++c) {
            if (next == '.' || next == c) {
                collect(x.next[c], prefix + c, pattern, queue);
            }
        }
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) {
            return length;
        }

        if (x.val != null) {
            length = d;
        }

        if (d == s.length()) {
            return length;
        }

        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        if (x.val != null) {
            return x;
        }

        for (char c = 0; c < R; ++c) {
            if (x.next[c] != null) {
                return x;
            }
        }

        return null;
    }

}
