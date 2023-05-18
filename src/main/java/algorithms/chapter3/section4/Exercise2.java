package algorithms.chapter3.section4;

import java.lang.reflect.Array;

public class Exercise2 {

    public static void main(String[] args) {
        SeparateChainingHashSTInline<String, Integer> st = new SeparateChainingHashSTInline<>();
        st.put("S", 1);
        st.put("A", 1);
        st.put("E", 1);
        st.put("H", 1);

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

    }

}
