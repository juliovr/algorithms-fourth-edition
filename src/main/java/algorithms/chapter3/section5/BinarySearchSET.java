package algorithms.chapter3.section5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BinarySearchSET<Key extends Comparable<Key>> {

    private Key[] keys;
    private int n;

    public BinarySearchSET(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Key get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return keys[i];
        } else {
            return null;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
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

    public void put(Key key) {
        // ordered insertions for optimization
        if (isEmpty()) {
            keys[0] = key;
            ++n;

            return;
        }

        Key maxKey = keys[n - 1];
        if (key.compareTo(maxKey) > 0) {
            keys[n] = key;
            ++n;

            return;
        }
        // ordered insertions for optimization

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return;
        }

        for (int j = n; j > i; --j) {
            keys[j] = keys[j - 1];
        }

        keys[i] = key;
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

    public void delete(Key key) {
        if (isEmpty()) return;

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            for (int j = i + 1; j < n; ++j) {
                keys[j - 1] = keys[j];
            }
            --n;
        }
    }

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
