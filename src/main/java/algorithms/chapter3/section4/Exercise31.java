package algorithms.chapter3.section4;

import edu.princeton.cs.algs4.Queue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise31 {

    public static void main(String[] args) {
        DoubleSeparateChainingHashST<String, Integer> st = new DoubleSeparateChainingHashST<>(2);
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("D", 4);
        st.put("E", 5);
        st.put("F", 6);
        st.put("G", 7);
        st.put("H", 8);
        st.put("I", 9);
        st.put("J", 10);
        st.put("K", 11);
        st.put("L", 12);
        st.put("M", 13);
        st.put("N", 14);
        st.put("O", 15);
        st.put("P", 16);
        st.put("Q", 17);

        List<String> letters = new ArrayList<>();
        for (String key: st.keys()) {
            System.out.print(key + ", ");
            letters.add(key);
        }

        System.out.println("\b\b");

        Collections.sort(letters);
        System.out.println(letters);
    }

    public static class DoubleSeparateChainingHashST<Key, Value> {

        private int m;
        private Node[] stNew;
        private Node[] stOld;

        private class Node {
            private Key key;
            private Value value;

            Node(Key key, Value value) {
                this.key = key;
                this.value = value;
            }
        }

        public DoubleSeparateChainingHashST() {
            this(997);
        }

        public DoubleSeparateChainingHashST(int m) {
            this.m = m;
            stNew = (Node[]) Array.newInstance(Node.class, m);
            stOld = (Node[]) Array.newInstance(Node.class, m);
        }

        public int getM() {
            return m;
        }

        private int hash1(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }

        private int hash2(Key key) {
            return (17*key.hashCode()) % m;
        }

        public int size() {
            int n = 0;
            for (Key key: keys()) {
                ++n;
            }

            return n;
        }

        public Value get(Key key) {
            Value value;
            value = stNew[hash1(key)].value;
            if (value != null) return value;
            value = stOld[hash1(key)].value;
            if (value != null) return value;
            value = stNew[hash2(key)].value;
            if (value != null) return value;
            value = stOld[hash2(key)].value;
            return value;
        }

        public void put(Key key, Value value) {
            if (size() >= m/2) resize(2*m);

            Node deletedStOld = put(key, value, hash1(key));
            if (deletedStOld != null) {
                deletedStOld = put(deletedStOld.key, deletedStOld.value, hash2(deletedStOld.key));
                if (deletedStOld != null) {
                    throw new RuntimeException("Cycle in put with key-value (" + deletedStOld.key + ", " + deletedStOld.value + ")");
                }
            }
        }

        private void resize(int cap) {
            DoubleSeparateChainingHashST<Key, Value> temp = new DoubleSeparateChainingHashST<>(cap);
            for (int i = 0; i < m; ++i) {
                Node node = stNew[i];
                if (node != null) temp.put(node.key, node.value);
            }
            for (int i = 0; i < m; ++i) {
                Node node = stOld[i];
                if (node != null) temp.put(node.key, node.value);
            }

            this.stNew = temp.stNew;
            this.stOld = temp.stOld;
            this.m = temp.m;
        }

        /**
         *
         * @param key
         * @param value
         * @param hash
         * @return null if it is inserted correctly, otherwise returns the value deleted from the other st.
         */
        private Node put(Key key, Value value, int hash) {
            if (stNew[hash] == null) {
                stNew[hash] = new Node(key, value);
                return null;
            } else {
                Node deletedStNew = new Node(stNew[hash].key, stNew[hash].value);
                stNew[hash].key = key;
                stNew[hash].value = value;

                if (stOld[hash] == null) {
                    stOld[hash] = new Node(deletedStNew.key, deletedStNew.value);
                    return null;
                } else {
                    Node deletedStOld = new Node(stOld[hash].key, stOld[hash].value);
                    stOld[hash].key = deletedStNew.key;
                    stOld[hash].value = deletedStNew.value;

                    return deletedStOld;
                }
            }
        }

        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<>();
            for (int i = 0; i < m; ++i) {
                if (stNew[i] != null) queue.enqueue(stNew[i].key);
                if (stOld[i] != null) queue.enqueue(stOld[i].key);
            }

            return queue;
        }

    }

}
