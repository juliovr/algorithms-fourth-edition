package algorithms.chapter5.section2;

import edu.princeton.cs.algs4.Queue;

public class TST<Value> {

    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }

        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d+1);
        } else {
            // d == key.length()-1 -> last character -> hit
            return x;
        }
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d+1);
        } else {
            // d == key.length()-1 -> last character -> hit
            x.val = val;
        }

        return x;
    }

    public Iterable<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, "", queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<>();
        Node firstNodeMatchingPrefix = get(root, prefix, 0);
        if (firstNodeMatchingPrefix == null) {
            return queue;
        }

        collect(firstNodeMatchingPrefix.mid, prefix, queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> queue) {
        if (x == null) {
            return;
        }

        collect(x.left, prefix, queue);

        if (x.val != null) {
            queue.enqueue(prefix + x.c);
        }

        collect(x.mid, prefix + x.c, queue);
        collect(x.right, prefix, queue);
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

        if (d == pattern.length()) {
            return;
        }

        if (x.val != null) {
            queue.enqueue(prefix + x.c);
        }

        char next = pattern.charAt(d);
        if (next == '.') {
            collect(x.left, prefix, pattern, queue);
            collect(x.mid, prefix + x.c, pattern, queue);
            collect(x.right, prefix, pattern, queue);
        } else if (next < x.c) {
            collect(x.left, prefix, pattern, queue);
        } else if (next > x.c) {
            collect(x.right, prefix, pattern, queue);
        } else {
            collect(x.mid, prefix + x.c, pattern, queue);
        }
    }

    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
        }
        if (query.length() == 0) return null;
        int length = 0;
        Node x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if      (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.val != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) {
            return length;
        }

        if (d == s.length()) {
            return length + 1;
        }

        if (x.val != null) {
            length = d;
        }

        char c = s.charAt(d);
        if (c < x.c) {
            return search(x.left, s, d, length);
        } else if (c > x.c) {
            return search(x.right, s, d, length);
        } else {
            return search(x.mid, s, d+1, length);
        }
    }

}
