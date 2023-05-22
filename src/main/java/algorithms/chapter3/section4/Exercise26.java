package algorithms.chapter3.section4;

import edu.princeton.cs.algs4.Queue;

public class Exercise26 {

    public static void main(String[] args) {
        LinearProbingHashSTLazyDelete<String, Integer> st = new LinearProbingHashSTLazyDelete<>(4);
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

        st.delete("D");
        st.delete("E");
        st.delete("J");
        st.delete("A");

        System.out.println("Expected: B: 2, C: 3, F: 6, G: 7, H: 8, I: 9");
        System.out.print("Actual  : ");
        for (String key: st.keys()) {
            int value = st.get(key);
            System.out.print(key + ": " + value + ", ");
        }
        System.out.println("\b\b");
    }

    public static class LinearProbingHashSTLazyDelete<Key, Value> {

        private int n;
        private int m;
        private Key[] keys;
        private Value[] values;

        public LinearProbingHashSTLazyDelete() {
            this(16);
        }

        public LinearProbingHashSTLazyDelete(int m) {
            this.m = m;
            keys = (Key[]) new Object[m];
            values = (Value[]) new Object[m];
        }

        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }

        private void resize(int cap) {
            LinearProbingHashSTLazyDelete<Key, Value> t = new LinearProbingHashSTLazyDelete<>(cap);
            for (int i = 0; i < m; ++i) {
                if (keys[i] != null) {
                    t.put(keys[i], values[i]);
                }
            }

            // Assign the new arrays into this object
            keys = t.keys;
            values = t.values;
            m = t.m;
        }

        public void put(Key key, Value value) {
            if (n >= m/2) resize(2*m);

            int i;
            for (i = hash(key); keys[i] != null && values[i] != null; i = (i + 1) % m) {
                if (keys[i].equals(key)) {
                    values[i] = value;
                    return;
                }
            }

            keys[i] = key;
            values[i] = value;
            ++n;
        }

        public Value get(Key key) {
            for (int i = hash(key); keys[i] != null && values[i] != null; i = (i + 1) % m) {
                if (keys[i].equals(key)) {
                    return values[i];
                }
            }

            return null;
        }

        public boolean contains(Key key) {
            return get(key) != null;
        }

        public void delete(Key key) {
            if (!contains(key)) return;
            int i = hash(key);
            while (!key.equals(keys[i])) {
                i = (i + 1) % m;
            }
            values[i] = null;

            --n;
            if (n > 0 && n <= m/8) {
                resize(m/2);
            }
        }

        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<>();
            for (Key key: keys) {
                if (key != null) {
                    Value value = get(key);
                    if (value != null) {
                        queue.enqueue(key);
                    }
                }
            }

            return queue;
        }

    }

}
