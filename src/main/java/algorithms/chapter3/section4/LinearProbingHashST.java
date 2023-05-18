package algorithms.chapter3.section4;

import edu.princeton.cs.algs4.Queue;

public class LinearProbingHashST<Key, Value> {

    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int m) {
        this.m = m;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
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
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
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
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
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
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % m;

        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            --n;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % m;
        }

        --n;
        if (n > 0 && n <= m/8) {
            resize(m/2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Key key: keys) {
            if (key != null) {
                queue.enqueue(key);
            }
        }

        return queue;
    }

}
