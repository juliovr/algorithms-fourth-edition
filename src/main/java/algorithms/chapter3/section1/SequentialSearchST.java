package algorithms.chapter3.section1;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key, Value> {

    private Node first;
    private int size;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }

        return null;
    }

    public void put(Key key, Value value) {
//        int compares = 0;
        for (Node node = first; node != null; node = node.next) {
//            ++compares;
            if (key.equals(node.key)) {
                // Search hit: update value
                node.value = value;

//                System.out.println("Put key = " + key + ", compares = " + compares);
                return;
            }
        }

        first = new Node(key, value, first);
        ++size;
//        System.out.println("Put key = " + key + ", compares = " + compares);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void delete(Key key) {
        if (isEmpty()) return;

        if (key.equals(first.key)) {
            first = first.next;
            --size;
            return;
        }

        for (Node node = first; node != null; node = node.next) {
            if (node.next != null) {
                if (node.next.key.equals(key)) {
                    node.next = node.next.next;
                    --size;
                }
            }
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node node = first; node != null; node = node.next) {
            queue.enqueue(node.key);
        }

        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<Character, Integer> st = new SequentialSearchST<>();
        char[] inputText = "EASYQUESTION".toCharArray();

        for (int i = 0; i < inputText.length; ++i) {
            char c = inputText[i];
            st.put(c, i);
        }

        for (char key : st.keys()) {
            System.out.println("Key = " + key + ", Value = " + st.get(key));
        }
        System.out.println("Size = " + st.size());
    }

}
