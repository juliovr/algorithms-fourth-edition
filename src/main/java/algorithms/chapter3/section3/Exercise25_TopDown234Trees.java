package algorithms.chapter3.section3;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

public class Exercise25_TopDown234Trees {

    public static void main(String[] args) {
        /*
                    M
                 /     \
               E         R
             /   \     /   \
          A-C   H-L   P   S-X
         */

        TopDownRedBlackBST<String, String> bst = new TopDownRedBlackBST<>();
        bst.put("S", "S");
        bst.put("E", "E");
        bst.put("A", "A");
        bst.put("R", "R");
        bst.put("C", "C");
        bst.put("H", "H");
        bst.put("X", "X");
        bst.put("M", "M");
        bst.put("P", "P");
        bst.put("L", "L");


        System.out.println("get(\"S\") = S -> " + bst.get("S"));
        System.out.println("get(\"E\") = E -> " + bst.get("E"));
        System.out.println("get(\"L\") = L -> " + bst.get("L"));
        System.out.println("get(\"F\") = null -> " + bst.get("F"));
        System.out.println();
        System.out.println("contains(\"S\") = true -> " + bst.contains("S"));
        System.out.println("contains(\"E\") = true -> " + bst.contains("E"));
        System.out.println("contains(\"L\") = true -> " + bst.contains("L"));
        System.out.println("contains(\"F\") = false -> " + bst.contains("F"));
        System.out.println();
        System.out.println("min() = A -> " + bst.min());
        System.out.println("max() = X -> " + bst.max());
        System.out.println();
        System.out.println("size() = 10 -> " + bst.size());
        System.out.println();
        System.out.println("floor(\"A\") = A -> " + bst.floor("A"));
        System.out.println("floor(\"Z\") = X -> " + bst.floor("Z"));
        System.out.println("floor(\"R\") = R -> " + bst.floor("R"));
        System.out.println("floor(\"T\") = S -> " + bst.floor("T"));
        System.out.println("floor(\"F\") = E -> " + bst.floor("F"));
        System.out.println();
        System.out.println("ceiling(\"A\") = A -> " + bst.ceiling("A"));
        System.out.println("ceiling(\"Z\") = null -> " + bst.ceiling("Z"));
        System.out.println("ceiling(\"R\") = R -> " + bst.ceiling("R"));
        System.out.println("ceiling(\"T\") = X -> " + bst.ceiling("T"));
        System.out.println("ceiling(\"F\") = H -> " + bst.ceiling("F"));
        System.out.println();
        System.out.println("rank(\"S\") = 8 -> " + bst.rank("S"));
        System.out.println("rank(\"A\") = 0 -> " + bst.rank("A"));
        System.out.println("rank(\"R\") = 7 -> " + bst.rank("R"));
        System.out.println("rank(\"L\") = 4 -> " + bst.rank("L"));
        System.out.println("rank(\"T\") = 9 -> " + bst.rank("T"));
        System.out.println();
        System.out.println("select(rank(\"S\")) = S -> " + bst.select(bst.rank("S")));
        System.out.println("select(rank(\"A\")) = A -> " + bst.select(bst.rank("A")));
        System.out.println("select(rank(\"R\")) = R -> " + bst.select(bst.rank("R")));
        System.out.println("select(rank(\"L\")) = L -> " + bst.select(bst.rank("L")));
        System.out.println("select(rank(\"T\")) = X -> " + bst.select(bst.rank("T")));
        System.out.println();
        System.out.println("size(\"A\", \"C\") = 2 -> " + bst.size("A", "C"));
        System.out.println("size(\"A\", \"D\") = 2 -> " + bst.size("A", "D"));
        System.out.println("size(\"A\", \"L\") = 5 -> " + bst.size("A", "L"));
        System.out.println("size(\"L\", \"P\") = 3 -> " + bst.size("L", "P"));
        System.out.println();
        System.out.println("keys() = A C E H L M P R S X ->\n" +
                           "         " + bst.keys());
        System.out.println("keys(\"A\", \"C\") = A C ->\n" +
                           "                 " + bst.keys("A", "C"));
        System.out.println("keys(\"A\", \"D\") = A C ->\n" +
                           "                 " + bst.keys("A", "D"));
        System.out.println("keys(\"H\", \"T\") = H L M P R S ->\n" +
                           "                 " + bst.keys("H", "T"));
        System.out.println("keys(\"X\", \"X\") = X ->\n" +
                           "                 " + bst.keys("X", "X"));
        System.out.println();
        System.out.println("Deleting min...");
        bst.deleteMin();
        System.out.println("keys() = C E H L M P R S X ->\n" +
                "         " + bst.keys());
        System.out.println("Deleting min...");
        bst.deleteMin();
        System.out.println("keys() = E H L M P R S X ->\n" +
                "         " + bst.keys());
        System.out.println();
        System.out.println("Deleting max...");
        bst.deleteMax();
        System.out.println("keys() = E H L M P R S ->\n" +
                "         " + bst.keys());
        System.out.println("Deleting max...");
        bst.deleteMax();
        System.out.println("keys() = E H L M P R ->\n" +
                "         " + bst.keys());
    }

    private static class TopDownRedBlackBST<Key extends Comparable<Key>, Value> {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private Node root;

        private class Node {
            Key key;
            Value val;
            Node left, right;
            int n;
            boolean color;

            public Node(Key key, Value val, int n, boolean color) {
                this.key = key;
                this.val = val;
                this.n = n;
                this.color = color;
            }
        }

        private boolean isRed(Node node) {
            if (node == null) return false;
            return node.color == RED;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            x.n = h.n;
            h.n = 1 + size(h.left) + size(h.right);

            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            x.n = h.n;
            h.n = 1 + size(h.left) + size(h.right);

            return x;
        }

        private void flipColors(Node h) {
            h.color = RED;
            h.left.color = BLACK;
            h.right.color = BLACK;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public int size() {
            return size(root);
        }

        private int size(Node x) {
            if (x == null) return 0;
            return x.n;
        }

        public void put(Key key, Value val) {
            root = put(root, key, val);
            root.color = BLACK;
        }

        private Node put(Node h, Key key, Value val) {
            if (h == null) {
                return new Node(key, val, 1, RED);
            }

            int cmp = key.compareTo(h.key);
            if (cmp < 0) {
                h.left = put(h.left, key, val);
            } else if (cmp > 0) {
                h.right = put(h.right, key, val);
            } else {
                h.val = val;
            }

            //
            // Correct any red-black links left when the new value was inserted
            //
            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }

            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }

            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }

            h.n = 1 + size(h.left) + size(h.right);

            return h;
        }

        public Value get(Key key) {
            Node node = root;
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp < 0) {
                    node = node.left;
                } else if (cmp > 0) {
                    node = node.right;
                } else {
                    return node.val;
                }
            }
            
            return null;
        }

        public boolean contains(Key key) {
            return get(key) != null;
        }

        public Key min() {
            Node node = root;
            while (node.left != null) {
                node = node.left;
            }

            return node.key;
        }

        public Key max() {
            Node node = root;
            while (node.right != null) {
                node = node.right;
            }

            return node.key;
        }

        public Key floor(Key key) {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            Node node = root;
            Node bestMatch = null; // When looking for right branch
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp < 0) {
                    if (node.left == null) {
                        break;
                    } else {
                        node = node.left;
                    }
                } else if (cmp > 0) {
                    if (node.right == null) {
                        return node.key;
                    } else {
                        bestMatch = node;
                        node = node.right;
                    }
                } else {
                    return node.key;
                }
            }

            if (bestMatch != null) {
                return bestMatch.key;
            } else {
                return null;
            }
        }

        public Key ceiling(Key key) {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            Node node = root;
            Node bestMatch = null; // When looking for right branch
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp < 0) {
                    if (node.left == null) {
                        return node.key;
                    } else {
                        bestMatch = node;
                        node = node.left;
                    }
                } else if (cmp > 0) {
                    if (node.right == null) {
                        break;
                    } else {
                        node = node.right;
                    }
                } else {
                    return node.key;
                }
            }

            if (bestMatch != null) {
                return bestMatch.key;
            } else {
                return null;
            }
        }

        public int rank(Key key) {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            Node node = root;
            int count = 0;
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp < 0) {
                    node = node.left;
                } else if (cmp > 0) {
                    count += (size(node.left) + 1);
                    node = node.right;
                } else {
                    count += size(node.left);
                    break;
                }
            }

            return count;
        }

        public Key select(int index) {
            if (index < 0 || index > size()) {
                throw new IllegalArgumentException("Index out of bounds");
            }

            Node node = root;
            while (node != null) {
                int leftSize = size(node.left);
                if (leftSize > index) {
                    node = node.left;
                } else if (leftSize < index) {
                    node = node.right;
                    index = (index - leftSize - 1);
                } else {
                    break;
                }
            }

            return node.key;
        }


        public void delete(Key key) {
            // TODO: implement
            // Eliminate the node by re-linking the parent to the children and apply the same rules in put()
            // to recalculate the link colors.



            //
            // Correct any red-black links left
            //
            /*
            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }

            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }

            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }

            h.n = 1 + size(h.left) + size(h.right);
            */
        }
/*
        private Node delete(Node node, Key key) {

        }
*/

        private boolean is2Node(Node node) {
            return !isRed(node.left);
        }

        public void deleteMin() {
            if (isEmpty()) {
                throw new NoSuchElementException("The tree is empty");
            }

            if (size() == 1) {
                root = null;
                return;
            }

            if (is2Node(root) && is2Node(root.left) && is2Node(root.right)) {
                // First case of the book
            }

            Node node = root;
            while (node != null) {

            }
        }

        public void deleteMax() {

        }

        public int size(Key lo, Key hi) {
            return 1 + rank(floor(hi)) - rank(floor(lo));
        }

        public Iterable<Key> keys() {
            return keys(min(), max());
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> queue = new Queue<>();
            keys(queue, root, lo, hi);
            return queue;
        }

        private void keys(Queue<Key> queue, Node node, Key lo, Key hi) {
            if (node == null) return;

            int cmplo = lo.compareTo(node.key);
            int cmphi = hi.compareTo(node.key);
            if (cmplo < 0) keys(queue, node.left, lo, hi);
            if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
            if (cmphi > 0) keys(queue, node.right, lo, hi);
        }

    }

}
