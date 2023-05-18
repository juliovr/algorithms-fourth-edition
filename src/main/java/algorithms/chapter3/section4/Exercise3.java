package algorithms.chapter3.section4;

import java.lang.reflect.Array;

public class Exercise3 {

    public static void main(String[] args) {
        SeparateChainingHashSTInline<String, Integer> st = new SeparateChainingHashSTInline<>(5);
        st.put("S", 1);
        st.put("A", 2);
        st.put("E", 3);
        st.put("T", 4);
        st.put("Y", 5);
        st.put("Q", 6);
        st.put("Z", 7);
        st.put("N", 8);
        st.put("K", 9);
        st.put("O", 10);

        st.deleteAll(9);
        st.deleteAll(1);

        System.out.println("End");
    }

    public static class SeparateChainingHashSTInline<Key, Value> {

        private int m;
        private Node[] nodes;
        private int n;

        public SeparateChainingHashSTInline() {
            this(997);
        }

        public SeparateChainingHashSTInline(int m) {
            this.m = m;
            nodes = (Node[]) Array.newInstance(Node.class, m);
            this.n = 0;
        }

        private class Node {
            private Key key;
            private Value value;
            private Node next;
            private int totalSoFar;
        }

        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }

        public Value get(Key key) {
            Node first = nodes[hash(key)];
            for (Node node = first; node != null; node = node.next) {
                if (key.equals(node.key)) {
                    return node.value;
                }
            }

            return null;
        }

        public void put(Key key, Value value) {
            int hash = hash(key);
            Node first = nodes[hash];
            for (Node node = first; node != null; node = node.next) {
                if (key.equals(node.key)) {
                    // Search hit: update value
                    node.value = value;
                    return;
                }
            }

            ++n;

            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            newNode.next = nodes[hash];
            newNode.totalSoFar = n;

            nodes[hash] = newNode;
        }

        public void deleteAll(int k) {
            for (int i = 0; i < m; ++i) {
                Node first = nodes[i];
                Node prev = nodes[i];
                for (Node node = first; node != null; node = node.next) {
                    if (node.totalSoFar > k) {
                        if (node == nodes[i]) {
                            nodes[i] = nodes[i].next;
                        } else {
                            prev.next = node.next;
                        }

                        --n;
                    }

                    prev = node;
                }
            }
        }

    }
    
}
