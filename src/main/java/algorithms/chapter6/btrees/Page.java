package algorithms.chapter6.btrees;

import algorithms.chapter3.section1.BinarySearchST;

public class Page<Key extends Comparable<Key>> {

    private int m;
    private BinarySearchST<Key, Page<Key>> st;
    private boolean isExternal;

    public Page(boolean isExternal, int m) {
        this.m = m;
        this.st = new BinarySearchST<>(this.m);
        this.isExternal = isExternal;
    }

    public void close() {

    }

    public int m() {
        return m;
    }

    public void add(Key key) {
        st.put(key, null);
    }

    public void add(Page<Key> page) {
        Key smallestKey = page.st.key(0);
        st.put(smallestKey, page);
    }

    public boolean isExternal() {
        return isExternal;
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public Page<Key> next(Key key) {
        Page<Key> nextPage = st.floorValue(key);
        if (nextPage == this) {
            return null;
        } else {
            return nextPage;
        }
    }

    public boolean isFull() {
        return st.size() == m;
    }

    public Page<Key> split() {
        Page<Key> highestRankingHalf = new Page<>(isExternal, m);
        for (int i = m/2; i < st.size(); ++i) {
            highestRankingHalf.st.put(st.key(i), st.value(i));
        }

        for (Key key : highestRankingHalf.keys()) {
            st.delete(key);
        }

        return highestRankingHalf;
    }

    public Iterable<Key> keys() {
        return st.keys();
    }

    public int numberOfChildren() {
        int result = 0;
        for (Key key : keys()) {
            if (next(key) != null) {
                result++;
            }
        }

        return result;
    }

}
