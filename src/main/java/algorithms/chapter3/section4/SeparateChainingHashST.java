package algorithms.chapter3.section4;

import algorithms.chapter3.section1.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;

public class SeparateChainingHashST<Key, Value> {

    private int m;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; ++i) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public int getM() {
        return m;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    // Exercise 9
    public void delete(Key key) {
        st[hash(key)].delete(key);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (SequentialSearchST list: st) {
            for (Object key: list.keys()) {
                queue.enqueue((Key)key);
            }
        }

        return queue;
    }

    public float chiSquareStatistic() {
        int n = 0;
        for (int i = 0; i < m; ++i) {
            for (Key key: st[i].keys()) {
                ++n;
            }
        }

        float nmRatio = n / m;
        float secondTerm = 0;
        for (int i = 0; i < m; ++i) {
            int size = st[i].size();
            secondTerm += Math.pow((size - nmRatio), 2);
        }

        float chiSq = ((float)m / (float)n)*secondTerm;

        return chiSq;
    }

}
