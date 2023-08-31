package algorithms.chapter5.section2;

public class TrieSTNonRecursive<Value> {

    private final static int R = 256;
    private Node root = new Node();

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = root;
        int d = 0;
        while (x != null) {
            if (d == key.length()) {
                break;
            }

            char c = key.charAt(d);
            x = x.next[c];
            d++;
        }

        if (x == null) {
            return null;
        } else {
            return (Value) x.val;
        }
    }

    public void put(String key, Value val) {
        Node x = root;
        int d = 0;
        while (true) {
            if (d == key.length()) {
                x.val = val;
                break;
            }

            char c = key.charAt(d);
            if (x.next[c] == null) {
                x.next[c] = new Node();
            }

            x = x.next[c];
            d++;
        }
    }

}
