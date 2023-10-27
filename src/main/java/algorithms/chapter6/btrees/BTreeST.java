package algorithms.chapter6.btrees;

import edu.princeton.cs.algs4.Queue;

public class BTreeST<Key extends Comparable<Key>, Value> {

    private PageST<Key, Value> root = new PageST<>(true);
    private int n = -1; // Start at -1 to ignore the sentinel
    private Key sentinel;

    public BTreeST(Key sentinel) {
        this.sentinel = sentinel;
        add(sentinel, null);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(PageST<Key, Value> page, Key key) {
        if (page.isExternal()) {
            return page.contains(key);
        }

        return contains(page.next(key), key);
    }

    public void add(Key key, Value value) {
        add(root, key, value);
        if (root.isFull()) {
            PageST<Key, Value> left = root;
            PageST<Key, Value> right = root.split();
            root = new PageST<>(false);
            root.add(left);
            root.add(right);
        }
    }

    private void add(PageST<Key, Value> page, Key key, Value value) {
        if (page.isExternal()) {
            page.add(key, value);
            ++n;
            return;
        }

        PageST<Key, Value> next = page.next(key);
        add(next, key, value);
        if (next.isFull()) {
            page.add(next.split());
        }
        next.close();
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size(Key key1, Key key2) {
        if (key1.compareTo(key2) > 0) {
            Key temp = key2;
            key2 = key1;
            key1 = temp;
        }

        int rank1 = rank(key1);
        int rank2 = contains(key2) ? rank(key2) : rank(key2) - 1;

        return (rank2 - rank1 + 1);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        extractKeys(queue, root);
        return queue;
    }

    private void extractKeys(Queue<Key> queue, PageST<Key, Value> page) {
        if (page.isExternal()) {
            for (Key key : page.keys()) {
                if (sentinel.compareTo(key) == 0) {
                    continue;
                }
                queue.enqueue(key);
            }

            return;
        }

        for (Key key : page.keys()) {
            PageST<Key, Value> next = page.next(key);
            extractKeys(queue, next);
        }
    }

    public Iterable<Key> keys(Key key1, Key key2) {
        if (key1.compareTo(key2) > 0) {
            Key temp = key2;
            key2 = key1;
            key1 = temp;
        }

        Queue<Key> queue = new Queue<>();
        extractKeys(queue, root, key1, key2);
        return queue;
    }

    private void extractKeys(Queue<Key> queue, PageST<Key, Value> page, Key min, Key max) {
        if (page.isExternal()) {
            for (Key key : page.keys()) {
                if (sentinel.compareTo(key) == 0) {
                    continue;
                }

                if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                    queue.enqueue(key);
                }
            }

            return;
        }

        for (Key key : page.keys()) {
            PageST<Key, Value> next = page.next(key);
            extractKeys(queue, next, min, max);
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(PageST<Key, Value> page, Key key) {
        if (page.isExternal()) {
            return page.get(key);
        }

        return get(page.next(key), key);
    }

    public Iterable<Value> get(Key key1, Key key2) {
        if (key1.compareTo(key2) > 0) {
            Key temp = key2;
            key2 = key1;
            key1 = temp;
        }

        Queue<Value> queue = new Queue<>();
        extractValues(queue, root, key1, key2);
        return queue;
    }

    private void extractValues(Queue<Value> queue, PageST<Key, Value> page, Key min, Key max) {
        if (page.isExternal()) {
            for (Key key : page.keys()) {
                if (sentinel.compareTo(key) == 0) {
                    continue;
                }

                if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                    queue.enqueue(get(key));
                }
            }

            return;
        }

        for (Key key : page.keys()) {
            PageST<Key, Value> next = page.next(key);
            extractValues(queue, next, min, max);
        }
    }

    public Key min() {
        return min(root);
    }

    private Key min(PageST<Key, Value> page) {
        if (page.isExternal()) {
            return minKeyFromPage(page);
        }

        for (Key key : page.keys()) {
            Key minKey = min(page.next(key));
            if (minKey != null) {
                return minKey;
            }
        }

        return null;
    }

    private Key minKeyFromPage(PageST<Key, Value> page) {
        Key minKey = page.min();
        if (page.isExternal() && minKey.compareTo(sentinel) == 0) {
            if (page.size() > 1) {
                minKey = page.key(1);
            } else {
                minKey = null;
            }
        }

        return minKey;
    }

    public Key max() {
        PageST<Key, Value> page = root;
        while (!page.isExternal()) {
            page = page.next(page.max());
        }

        return page.max();
    }

    public Key floor(Key key) {
        PageST<Key, Value> page = root;
        while (!page.isExternal()) {
            page = page.next(key);
        }

        Key floorKey = page.floor(key);
        if (floorKey == null || sentinel.compareTo(floorKey) == 0) {
            return null;
        } else {
            return floorKey;
        }
    }

    public Key ceiling(Key key) {
        PageST<Key, Value> page = root;
        while (!page.isExternal()) {
            page = page.next(key);
        }

        Key ceilingKey = page.ceiling(key);
        if (ceilingKey == null || sentinel.compareTo(ceilingKey) == 0) {
            return null;
        } else {
            return ceilingKey;
        }
    }

    public Key select(int i) {
        int current = 0;
        for (Key k : keys()) {
            if (current++ == i) {
                return k;
            }
        }

        return null;
    }

    public int rank(Key key) {
        int i = 0;
        for (Key k : keys()) {
            if (k.compareTo(key) >= 0) {
                return i;
            }
            ++i;
        }

        return i;
    }

    public void delete(Key key) {
        delete(root, key);
        --n;
    }

    private Key delete(PageST<Key, Value> page, Key key) {
        if (page.isExternal()) {
            page.delete(key);
            return page.min();
        }

        PageST<Key, Value> nextPage = page.next(key);
        delete(nextPage, key);
        if (!page.isExternal() && page.contains(key)) {
            page.delete(key);
            page.add(nextPage);
        }

        return min();
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

}
