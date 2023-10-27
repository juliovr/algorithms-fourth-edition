package algorithms.chapter6.btrees;

import algorithms.chapter3.section1.BinarySearchST;

public class PageST<Key extends Comparable<Key>, Value> {

    private class PageValue {
        PageST<Key, Value> next;
        Value value;
    }

    private int m;
    private BinarySearchST<Key, PageValue> st;
    private boolean isExternal;

    public PageST(boolean isExternal) {
        this.m = 6;
        this.st = new BinarySearchST<>(this.m);
        this.isExternal = isExternal;
    }

    public void close() {

    }

    public void add(Key key, Value value) {
        PageValue pageValue = new PageValue();
        pageValue.next = this;
        pageValue.value = value;

        st.put(key, pageValue);
    }

    public void add(PageST<Key, Value> page) {
        Key smallestKey = page.st.key(0);
        PageValue pageValue = new PageValue();
        pageValue.next = page;
        st.put(smallestKey, pageValue);
    }

    public boolean isExternal() {
        return isExternal;
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public PageST<Key, Value> next(Key key) {
        return st.floorValue(key).next;
    }

    public boolean isFull() {
        return st.size() == m;
    }

    public PageST<Key, Value> split() {
        PageST<Key, Value> highestRankingHalf = new PageST<>(isExternal);
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

    public int size() {
        return st.size();
    }

    public Value get(Key key) {
        PageValue pageValue = st.get(key);
        if (pageValue == null) {
            return null;
        }

        return pageValue.value;
    }

    public Key min() {
        return key(0);
    }

    public Key max() {
        return key(st.size() - 1);
    }

    public Key key(int i) {
        return st.key(i);
    }

    public Key floor(Key key) {
        return st.floor(key);
    }

    public Key ceiling(Key key) {
        return st.ceiling(key);
    }

    public int rank(Key key) {
        return st.rank(key);
    }

    public void delete(Key key) {
        st.delete(key);
    }

}
