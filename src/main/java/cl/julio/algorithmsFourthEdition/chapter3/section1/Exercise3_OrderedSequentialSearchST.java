package cl.julio.algorithmsFourthEdition.chapter3.section1;

import cl.julio.algorithmsFourthEdition.Common;
import edu.princeton.cs.algs4.Queue;

public class Exercise3_OrderedSequentialSearchST {

    public static void main(String[] args) {
        OrderedSequentialSearchST<String, Integer> st = new OrderedSequentialSearchST<>();
        st.put("C", 3);
        st.put("B", 2);
        st.put("A", 1);
        st.put("A", 100);
        st.put("F", 120);
        st.put("G", 10);
        st.put("Z", 50);

        for (String key : st.keys()) {
            System.out.println("Key = " + key + ", value = " + st.get(key));
        }
        System.out.println("Size = " + st.size());
        System.out.println("Rank = " + st.rank("B"));
        System.out.println("Floor G = " + st.floor("G"));
        System.out.println("Floor Y = " + st.floor("Y"));
        System.out.println("Ceiling G = " + st.ceiling("G"));
        System.out.println("Ceiling H = " + st.ceiling("H"));
    }

    private static class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {

        private class Node {
            private Key key;
            private Value value;
            private Node next;
        }

        private Node first;
        private int size;

        public void put(Key key, Value value) {
            if (isEmpty()) {
                Node newNode = new Node();
                newNode.key = key;
                newNode.value = value;

                first = newNode;

                ++size;
                return;
            }

            if (first.key.compareTo(key) > 0) {
                Node newNode = new Node();
                newNode.key = key;
                newNode.value = value;
                newNode.next = first;
                first = newNode;

                ++size;
                return;
            } else if (first.key.compareTo(key) == 0) {
                // Update value of existing key
                first.value = value;
                return;
            }

            for (Node node = first; node != null; node = node.next) {
                // Update value of existing key
                if (node.key.compareTo(key) == 0) {
                    node.value = value;
                    return;
                }

                Node newNode = new Node();
                newNode.key = key;
                newNode.value = value;

                if (node.next == null) {
                    // Last node
                    node.next = newNode;

                    ++size;
                } else {
                    if (node.next.key.compareTo(key) > 0) {
                        newNode.next = node.next;
                        node.next = newNode;

                        ++size;
                    } else if (node.next.key.compareTo(key) == 0) {
                        // Equals: update the value of existing key
                        node.next.value = value;
                    }
                }
            }
        }

        public Value get(Key key) {
            for (Node node = first; node != null; node = node.next) {
                if (node.key.compareTo(key) == 0) {
                    return node.value;
                }
            }

            return null;
        }

        public void delete(Key key) {
            if (isEmpty()) {
                return;
            }

            if (first.key.compareTo(key) == 0) {
                first = first.next;
                --size;
                return;
            }

            for (Node node = first; node != null; node = node.next) {
                if (node.next != null) {
                    if (node.next.key.compareTo(key) == 0) {
                        node.next = node.next.next;
                        --size;
                    }
                }
            }
        }

        public boolean contains(Key key) {
            return get(key) != null;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public Key min() {
            return first.key;
        }

        public Key max() {
            for (Node node = first; node != null; node = node.next) {
                if (node.next == null) {
                    return node.key;
                }
            }

            return null;
        }

        public Key floor(Key key) {
            Key result = null;
            for (Node node = first; node != null; node = node.next) {
                if (node.key.compareTo(key) <= 0) {
                    result = node.key;
                } else {
                    break;
                }
            }

            return result;
        }

        public Key ceiling(Key key) {
            for (Node node = first; node != null; node = node.next) {
                if (node.key.compareTo(key) >= 0) {
                    return node.key;
                }
            }

            return null;
        }

        public int rank(Key key) {
            int index = 0;
            for (Node node = first; node != null; node = node.next) {
                if (node.key.compareTo(key) < 0) {
                    ++index;
                } else {
                    break;
                }
            }

            return index;
        }

        public Key select(int k) {
            if (k < 0 || k >= size) {
                throw new IllegalArgumentException("Index out of bounds = " + k + ". Actual size = " + size);
            }

            int i = 0;
            for (Node node = first; node != null; node = node.next) {
                if (k == i) {
                    return node.key;
                }
                ++i;
            }

            return null;
        }

        public void deleteMin() {
            delete(min());
        }

        public void deleteMax() {
            delete(max());
        }

        public int size(Key lo, Key hi) {
            if (hi.compareTo(lo) < 0) {
                return 0;
            }

            if (contains(hi)) {
                return rank(hi) - rank(lo) + 1;
            } else {
                return rank(hi) - rank(lo);
            }
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> queue = new Queue<>();
            for (Node node = first; node != null; node = node.next) {
                queue.enqueue(node.key);
            }

            return queue;
        }

        public Iterable<Key> keys() {
            return keys(min(), max());
        }

    }

}
