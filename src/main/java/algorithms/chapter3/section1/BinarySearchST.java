package algorithms.chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(5);
        st.put("B", 5);
        st.put("H", 23);
        st.put("A", 62);
        st.put("C", 90);
        st.put("J", 12);

        st.delete("A");
        System.out.println(st.contains("B"));
        System.out.println(st.contains("Z"));
        System.out.println(st.contains("A"));
        System.out.println(st.contains("C"));

        for (String key : st.keys()) {
            System.out.println("Key = " + key + ", value = " + st.get(key));
        }

        System.out.println("Floor = " + st.floor("A"));
    }

    private Key[] keys;
    private Value[] values;
    private int n;
    private Key cacheKey;
    private Value cacheValue;

    public BinarySearchST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (cacheKey != null && cacheKey.compareTo(key) == 0) {
            Value value = cacheValue;
            cacheKey = null;
            cacheValue = null;
            return value;
        }

        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    public boolean contains(Key key) {
        Value value = get(key);
        if (value != null) {
            cacheKey = key;
            cacheValue = value;
            return true;
        }

        cacheKey = null;
        cacheValue = null;
        return false;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) / 2);
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return lo;
    }

    public void put(Key key, Value value) {
        // Exercise 28 (start): ordered insertions for optimization
        if (isEmpty()) {
            keys[0] = key;
            values[0] = value;
            ++n;

            return;
        }

        Key maxKey = keys[n - 1];
        if (key.compareTo(maxKey) > 0) {
            keys[n] = key;
            values[n] = value;
            ++n;

            return;
        }
        // Exercise 28 (end): ordered insertions for optimization

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        for (int j = n; j > i; --j) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        ++n;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < size(); ++i) {
            queue.enqueue(keys[i]);
        }

        return queue;
    }

    public Iterable<Key> reverseKeys() {
        Stack<Key> stack = new Stack<>();
        for (int i = 0; i < size(); ++i) {
            stack.push(keys[i]);
        }

        return stack;
    }

    // Exercise 3.1.16
    public void delete(Key key) {
        if (isEmpty()) return;

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            for (int j = i + 1; j < n; ++j) {
                keys[j - 1] = keys[j];
                values[j - 1] = values[j];
            }
            --n;
        }
    }

    // Exercise 3.1.17
    public Key floor(Key key) {
        if (get(key) != null) {
            return key;
        }

        int i = rank(key);
        if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }

}
