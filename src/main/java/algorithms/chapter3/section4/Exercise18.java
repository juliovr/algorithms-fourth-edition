package algorithms.chapter3.section4;

import algorithms.chapter3.section1.SequentialSearchST;

public class Exercise18 {

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(4);
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);

        System.out.println(st);
    }

    public static class SeparateChainingHashST<Key, Value> {

        private int m;
        private int avgListSize;
        private int n;
        private SequentialSearchST<Key, Value>[] st;

        public SeparateChainingHashST() {
            this(997);
        }

        public SeparateChainingHashST(int m) {
            this.m = m;
            this.n = 0;
            this.avgListSize = 100;
            st = new SequentialSearchST[m];
            for (int i = 0; i < m; ++i) {
                st[i] = new SequentialSearchST<>();
            }
        }

        public SeparateChainingHashST(int m, int avgListSize) {
            this(m);
            this.avgListSize = avgListSize;
        }

        private int hash(Key key) {
            int h = key.hashCode();
            h ^= (h >>> 20) ^ (h >>> 12);
            h ^= (h >>> 7) ^ (h >>> 4);
            return h & (m - 1);
        }

        public Value get(Key key) {
            return st[hash(key)].get(key);
        }

        private void resize(int cap) {
            SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(cap);
            for (int i = 0; i < m; ++i) {
                SequentialSearchST<Key, Value> nodes = st[i];
                for (Key key: nodes.keys()) {
                    temp.put(key, nodes.get(key));
                }
            }

            this.st = temp.st;
            this.m = temp.m;
        }

        public void put(Key key, Value value) {
            if (n >= m/2) resize(2*m);

            SequentialSearchST<Key, Value> node = st[hash(key)];
            if (node.size() >= avgListSize) resize(2*m);

            if (node.get(key) == null) {
                ++n;
            }

            node.put(key, value);
        }

        // Exercise 9
        public void delete(Key key) {
            st[hash(key)].delete(key);
        }

    }


}
