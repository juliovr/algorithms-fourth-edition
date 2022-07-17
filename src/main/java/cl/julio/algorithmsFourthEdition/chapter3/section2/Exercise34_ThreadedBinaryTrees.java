package cl.julio.algorithmsFourthEdition.chapter3.section2;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

public class Exercise34_ThreadedBinaryTrees {

    public static void main(String[] args) {
        /*
                R
              C   S
             A E   X
         */
        BST<String, String> bst = new BST<>();
        bst.put("R", "1");
        bst.put("C", "2");
        bst.put("A", "3");
        bst.put("E", "4");
        bst.put("S", "5");
        bst.put("X", "6");

        bst.delete("C");

        System.out.println("Height tree = " + bst.height());

        System.out.println(bst.get("R"));
        System.out.println(bst.get("X"));
        System.out.println(bst.get("B"));

        System.out.println("min() = A -> " + bst.min());
        System.out.println("max() = X -> " + bst.max());
//        System.out.println("floor(C) = C -> " + bst.floor("C"));
//        System.out.println("floor(D) = C -> " + bst.floor("D"));
//        System.out.println("ceiling(S) = S -> " + bst.ceiling("S"));
//        System.out.println("ceiling(M) = R -> " + bst.ceiling("M"));
//        System.out.println("rank(C) = 1 -> " + bst.rank("C"));
//        System.out.println("rank(X) = 5 -> " + bst.rank("X"));
//        System.out.println("rank(T) = 5 -> " + bst.rank("T"));
//        System.out.println("select(0) = A -> " + bst.select(0));
//        System.out.println("select(1) = C -> " + bst.select(1));
//        System.out.println("keys() = A C E R S X -> " + bst.keys());
    }
    
    public static class BST<Key extends Comparable<Key>, Value> {

        public Node root;
        public Node cache;

        public class Node {
            public Key key;
            public Value value;
            public Node left, right;
            public int n;
            public int height;

            public Node(Key key, Value value, int n) {
                this.key = key;
                this.value = value;
                this.n = n;
                this.height = 0;
            }
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            } else {
                return node.n;
            }
        }

        /**
         * Non-recursive implementation.
         */
        public Value get(Key key) {
            if (cache != null && cache.key == key) {
                return cache.value;
            }

            Node node = root;
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp == 0) {
                    cache = node;
                    return node.value;
                } else if (cmp < 0) {
                    node = node.left;
                } else if (cmp > 0) {
                    node = node.right;
                }
            }

            return null;
        }

        private boolean isRightNormalLink(Node node) {
            return (node.right != null && node.n > node.right.n);
        }

        public void put(Key key, Value value) {
            if (root == null) {
                root = new Node(key, value, 1);
                root.key = key;
                root.value = value;

                cache = root;

                return;
            }

            if (cache.key == key) {
                cache.value = value;
                return;
            }

            Node node = root;
            Node parent = node;
            Node next = null;
            while (node != null) {
                parent = node;
                int cmp = key.compareTo(node.key);
                if (cmp < 0) {
                    next = node;
                    node = node.left;
                } else if (cmp > 0) {
                    node = node.right;
                } else {
                    node.value = value;
                    cache = node;
                    return;
                }
            }

            node = new Node(key, value, 1);
            cache = node;

            int cmp = node.key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = node;
            } else if (cmp > 0) {
                node.right = next;
                parent.right = node;
            }

            root.height = heightRecursive(root);
            for (Node x = root; x != null; x = x.left) {
                x.n = size(x.left) + size(x.right) + 1;
            }
            for (Node x = root; x != null; x = x.right) {
                x.n = size(x.left) + size(x.right) + 1;
            }
        }

        public Key min() {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            Node min = root;
            while (min.left != null) {
                min = min.left;
            }

            return min.key;
        }

        public Key max() {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            Node max = root;
            while (max.right != null) {
                max = max.right;
            }

            return max.key;
        }

        public void deleteMin() {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            root = deleteMin(root);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) {
                if (node.right == null || isRightNormalLink(node)) {
                    return node.right;
                } else {
                    // It is not normal link, so we need to look-up for the node's parent
                    Node parent = root;
                    Node current = root;
                    while (current != null) {
                        int cmp = node.key.compareTo(current.key);
                        if (cmp < 0) {
                            if (current.left.key.compareTo(node.key) != 0) {
                                parent = current;
                            }
                            current = current.left;
                        } else if (cmp > 0) {
                            if (current.right.key.compareTo(node.key) != 0) {
                                parent = current;
                            }
                            current = current.right;
                        } else {
                            // Maintain the threaded link
//                            parent.right = node.right;
                            break;
                        }
                    }

                    return parent;
                }
            }

            node.left = deleteMin(node.left);
            node.n = size(node.left) + size(node.right) + 1;
            node.height = height(node.left) + height(node.right) + 1;
            return node;
        }

        public void deleteMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            root = deleteMax(root);
        }

        private Node deleteMax(Node node) {
            if (node.right == null || !isRightNormalLink(node)) {
                return node.left;
            }

            node.right = deleteMax(node.right);
            node.n = size(node.left) + size(node.right) + 1;
            node.height = height(node.left) + height(node.right) + 1;
            return node;
        }

        public void delete(Key key) {
            root = delete(root, key);
        }

        private Node delete(Node node, Key key) {
            if (node == null) {
                return null;
            }

            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = delete(node.left, key);
            } else if (cmp > 0) {
                node.right = delete(node.right, key);
            } else {
                // delete
                if (node.right == null || !isRightNormalLink(node)) { return node.left; }
                if (node.left == null) { return node.right; }

                Node t = node;
                node = node.right;
                // Search for min
                while (node.left != null) {
                    node = node.left;
                }

                node.right = deleteMin(t.right);
                node.left = t.left;
            }

            node.n = size(node.left) + size(node.right) + 1;
            node.height = height(node.left) + height(node.right) + 1;

            return node;
        }

        public Iterable<Key> keys() {
            return keys(min(), max());
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> queue = new Queue<>();
            keys(root, queue, lo, hi);
            return queue;
        }

        private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
            if (node == null) return;

            int cmplo = lo.compareTo(node.key);
            int cmphi = hi.compareTo(node.key);
            if (cmplo < 0) { keys(node.left, queue, lo, hi); }
            if (cmplo <= 0 && cmphi >= 0) { queue.enqueue(node.key); }
            if (cmphi > 0) { keys(node.right, queue, lo, hi); }
        }

        public int heightRecursive() {
            return heightRecursive(root);
        }

        private int heightRecursive(Node node) {
            if (node == null) {
                return -1;
            }

            int heightLeft = heightRecursive(node.left);

            int heightRight = 0;
            if (isRightNormalLink(node)) {
                heightRight = heightRecursive(node.right);
            }

            return Math.max(heightLeft, heightRight) + 1;
        }

        public int height() {
            return root.height;
        }

        private int height(Node node) {
            if (node == null){
                return -1;
            }

            return node.height;
        }

    }

}
