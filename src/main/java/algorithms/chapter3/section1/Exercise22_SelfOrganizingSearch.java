package algorithms.chapter3.section1;

import edu.princeton.cs.algs4.Queue;

public class Exercise22_SelfOrganizingSearch {

    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<>(3);
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);

        st.get("b");
        st.get("c");

        System.out.println("Size = " + st.size());
        for (String key : st.keys()) {
            System.out.printf("Key = %s,\tValue = %d\n", key, st.get(key));
        }
    }


    private static class ArrayST<K, V> {

        private K[] keys;
        private V[] values;
        private int size;

        public ArrayST(int capacity) {
            this.keys = (K[]) new Object[capacity];
            this.values = (V[]) new Object[capacity];
            this.size = 0;
        }

        public void put(K key, V value) {
            keys[size] = key;
            values[size] = value;
            ++size;
        }

        public V get(K key) {
            for (int i = 0; i < size; ++i) {
                if (keys[i] == key) {
                    // Search hit
                    moveToFront(i);
                    return values[0];
                }
            }

            return null;
        }

        private void moveToFront(int i) {
            if (i == 0) return;

            K key = keys[i];
            V value = values[i];
            for (int j = i; j > 0; --j) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }

            keys[0] = key;
            values[0] = value;
        }

        public void delete(K key) {
            for (int i = 0; i < size; ++i) {
                if (keys[i] == key) {
                    keys[i] = keys[size - 1];
                    values[i] = values[size - 1];

                    --size;
                    keys[size] = null;
                    values[size] = null;
                    return;
                }
            }
        }

        public boolean contains(K key) {
            return get(key) != null;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public Iterable<K> keys() {
            Queue<K> queue = new Queue<>();
            for (int i = 0; i < size; ++i) {
                queue.enqueue(keys[i]);
            }

            return queue;
        }

    }


}
