package algorithms.chapter5.section2;

import edu.princeton.cs.algs4.Queue;

public class TrieSTWithSize<Value> {

    public final static int R = 256;
    public NodeWithSize root = new NodeWithSize();

    public static class NodeWithSize {
        public Object val;
        public NodeWithSize[] next = new NodeWithSize[R];
        public int size;
    }

    public int size() {
        return root.size;
    }

    public Value get(String key) {
        NodeWithSize x = get(root, key, 0);
        if (x == null) {
            return null;
        }

        return (Value) x.val;
    }

    private NodeWithSize get(NodeWithSize x, String key, int d) {
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
        boolean isNewKey = get(key) == null;
        root = put(root, key, val, 0, isNewKey);
    }

    private NodeWithSize put(NodeWithSize x, String key, Value val, int d, boolean isNewKey) {
        if (x == null) {
            x = new NodeWithSize();
        }

        if (isNewKey) {
            x.size++;
        }

        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1, isNewKey);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<>();
        NodeWithSize firstNodeMatchingPrefixWithSize = get(root, prefix, 0);
        collect(firstNodeMatchingPrefixWithSize, prefix, queue);
        return queue;
    }

    private void collect(NodeWithSize x, String prefix, Queue<String> queue) {
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

    private void collect(NodeWithSize x, String prefix, String pattern, Queue<String> queue) {
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

    private int search(NodeWithSize x, String s, int d, int length) {
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

    private NodeWithSize delete(NodeWithSize x, String key, int d) {
        if (x == null) {
            return null;
        }

        x.size--;

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

    public String floor(String key) {
        return floor(root, key, -1, "", null);
    }

    private String floor(NodeWithSize x, String key, int d, String prefix, String lastKeyFound) {
        if (x == null) {
            return null;
        }

        if (x.val != null) {
            if (key.compareTo(prefix) == 0) {
                return prefix;
            }

            if (lastKeyFound == null || prefix.compareTo(lastKeyFound) > 0) {
                lastKeyFound = prefix;
            }
        }

        for (char c = 0; c < R; ++c) {
            String keyFound = floor(x.next[c], key, d+1, prefix + c, lastKeyFound);
            // Is keyFound greater than lastKeyFound and less than key?
            // All the null checker is for compareTo does not throw NullPointerException
            if (lastKeyFound == null ||
                    (keyFound != null && keyFound.compareTo(lastKeyFound) > 0)) {
                if (keyFound == null || keyFound.compareTo(key) <= 0) {
                    lastKeyFound = keyFound;
                }
            }
        }

        return lastKeyFound;
    }

    private class CeilingState {
        boolean oneStringPassKey;
    }

    public String ceiling(String key) {
        return ceiling(root, key, -1, "", null, new CeilingState());
    }

    private String ceiling(NodeWithSize x, String key, int d, String prefix, String lastKeyFound, CeilingState state) {
        if (x == null) {
            return null;
        }

        if (x.val != null) {
            if (key.compareTo(prefix) == 0) {
                return prefix;
            }

            if (!state.oneStringPassKey && prefix.compareTo(key) >= 0) {
                state.oneStringPassKey = true;
                lastKeyFound = prefix;
            }
        }

        for (char c = 0; c < R; ++c) {
            String keyFound = ceiling(x.next[c], key, d+1, prefix + c, lastKeyFound, state);
            if (lastKeyFound == null ||
                    (keyFound != null && keyFound.compareTo(key) < 0)) {
                lastKeyFound = keyFound;
            }
        }

        return lastKeyFound;
    }

    private class SelectState {
        int runningRank = 0;
    }

    public String select(int rank) {
        return select(root, rank, "", new SelectState());
    }

    private String select(NodeWithSize x, int rank, String prefix, SelectState state) {
        if (x == null) {
            return null;
        }

        if (x.val != null) {
            if (state.runningRank++ == rank) {
                return prefix;
            }
        }

        for (char c = 0; c < R; ++c) {
            String keyFound = select(x.next[c], rank, prefix + c, state);
            if (keyFound != null) {
                return keyFound;
            }
        }

        return null;
    }

    private class RankStatus {
        int rank;
        boolean completed;
    }
    public int rank(String key) {
        return rank(root, key, "", new RankStatus());
    }

    private int rank(NodeWithSize x, String key, String prefix, RankStatus status) {
        if (x == null) {
            return status.rank;
        }

        if (status.completed) {
            return status.rank;
        }

        if (prefix.compareTo(key) >= 0) {
            status.completed = true;
            return status.rank;
        }

        if (x.val != null) {
            status.rank++;
        }

        for (char c = 0; c < R; ++c) {
            status.rank = rank(x.next[c], key, prefix + c, status);
        }

        return status.rank;
    }

}
