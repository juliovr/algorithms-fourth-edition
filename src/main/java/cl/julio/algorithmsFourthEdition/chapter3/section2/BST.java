package cl.julio.algorithmsFourthEdition.chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

    public static void main(String[] args) {
        /*
                R
              C   S
             A E   X
         */
        BST<String, String> bst = new BST<>();
        bst.putRecursive("R", "1");
        bst.putRecursive("C", "2");
        bst.putRecursive("A", "3");
        bst.putRecursive("E", "4");
        bst.putRecursive("S", "5");
        bst.putRecursive("X", "6");

        System.out.println("Height tree = " + bst.height());

        System.out.println(bst.get("R"));
        System.out.println(bst.get("X"));
        System.out.println(bst.get("B"));

        System.out.println("min() = A -> " + bst.min());
        System.out.println("max() = X -> " + bst.max());
        System.out.println("floor(C) = C -> " + bst.floor("C"));
        System.out.println("floor(D) = C -> " + bst.floor("D"));
        System.out.println("ceiling(S) = S -> " + bst.ceiling("S"));
        System.out.println("ceiling(M) = R -> " + bst.ceiling("M"));
        System.out.println("rank(C) = 1 -> " + bst.rank("C"));
        System.out.println("rank(X) = 5 -> " + bst.rank("X"));
        System.out.println("rank(T) = 5 -> " + bst.rank("T"));
        System.out.println("select(0) = A -> " + bst.select(0));
        System.out.println("select(1) = C -> " + bst.select(1));
        System.out.println("keys() = A C E R S X -> " + bst.keys());

        bst.draw();
    }

    private static final float RADIUS = 0.02f;

    public void draw() {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setPenColor(StdDraw.BLACK);

        if (isEmpty()) {
            StdDraw.text(0.5f, 0.5f, "The BST is empty");
            return;
        }

        drawNodeRecursive();
    }

    private void drawNodeRecursive() {
        int totalHeight = heightRecursive();
        float x = 0.5f;
        float y = 0.5f + (totalHeight*0.05f); // Center the tree in y-axis

        drawNodeRecursive(root, x, y, totalHeight);
    }

    private void drawNodeRecursive(Node node, float x, float y, float height) {
        if (node == null) {
            return;
        }

        drawNode(x, y, String.valueOf(node.key));

        float xDiff = (0.05f*height);
        float newY = y - 0.1f;
        float newHeight = height - 1;

        if (node.left != null) {
            drawNodeRecursive(node.left, x - xDiff, newY, newHeight);
            drawLineNode(x - (RADIUS * 0.8f), y - (RADIUS * 1.7f), x - xDiff, newY + (RADIUS * 2));
        }

        if (node.right != null) {
            drawNodeRecursive(node.right, x + xDiff, newY, newHeight);
            drawLineNode(x + (RADIUS * 0.8f), y - (RADIUS * 1.7f), x + xDiff, newY + (RADIUS * 2));
        }
    }

    private void drawNode(float x, float y, String text) {
        StdDraw.circle(x, y, RADIUS);
        float xTextCentered = x*1.002f; // Using x the text is not center, so we add a little pad
        StdDraw.text(xTextCentered, y, text);
    }

    private void drawLineNode(float x1, float y1, float x2, float y2) {
        StdDraw.line(x1, y1 + RADIUS, x2, y2 - RADIUS);
    }

    public class DrawableNode {
        public Node node;
        public float x, y;
    }

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

    public Value getRecursive(Key key) {
        return getRecursive(root, key);
    }

    /**
     * Return the value associated with the key in the subtree rooted at node; otherwise, return null.
     */
    private Value getRecursive(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getRecursive(node.left, key);
        } else if (cmp > 0) {
            return getRecursive(node.right, key);
        } else {
            return node.value;
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

    public void putRecursive(Key key, Value value) {
        root = putRecursive(root, key, value);
    }

    /**
     * If the key exists in the subtree rooted at node, updates the value; otherwise, add a new node to subtree
     * with the key-value pair.
     */
    private Node putRecursive(Node node, Key key, Value value) {
        // There are no more nodes in the subtree or the tree is completely empty.
        if (node == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putRecursive(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putRecursive(node.right, key, value);
        } else {
            node.value = value;
        }

        node.n = size(node.left) + size(node.right) + 1;
        node.height = height(node.left) + height(node.right) + 1;

        return node;
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
        while (node != null) {
            parent = node;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
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

    public Key minRecursive() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty");
        }

        return minRecursive(root).key;
    }

    private Node minRecursive(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minRecursive(node.left);
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

    public Key maxRecursive() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty");
        }

        return maxRecursive(root);
    }

    private Key maxRecursive(Node node) {
        if (node.right == null) {
            return node.key;
        } else {
            return maxRecursive(node.right);
        }
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

    public Key floorRecursive(Key key) {
        Node node = floorRecursive(root, key);
        if (node == null) {
            throw new NoSuchElementException();
        }

        return node.key;
    }

    public Node floorRecursive(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return floorRecursive(node.left, key);
        }

        Node temp = floorRecursive(node.right, key);
        if (temp != null) {
            return temp;
        } else {
            return node;
        }
    }

    public Key floor(Key key) {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty");
        }

        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp >= 0) {
                break;
            }

            node = node.left;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.key;
        } else if (cmp > 0) {
            int cmpRight = key.compareTo(node.right.key);
            if (cmpRight >= 0) {
                return node.right.key;
            }
        }

        return node.key;
    }

    public Key ceilingRecursive(Key key) {
        Node node = ceilingRecursive(root, key);
        if (node == null) {
            throw new NoSuchElementException();
        }

        return node.key;
    }

    public Node ceilingRecursive(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }
        if (cmp > 0) {
            return ceilingRecursive(node.right, key);
        }

        Node temp = ceilingRecursive(node.left, key);
        if (temp != null) {
            return temp;
        } else {
            return node;
        }
    }

    public Key ceiling(Key key) {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty");
        }

        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp <= 0) {
                break;
            }

            node = node.right;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.key;
        } else if (cmp < 0) {
            int cmpRight = key.compareTo(node.left.key);
            if (cmpRight <= 0) {
                return node.left.key;
            }
        }

        return node.key;
    }

    public Key selectRecursive(int index) {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        Node node = selectRecursive(root, index);
        return node.key;
    }

    private Node selectRecursive(Node node, int index) {
        if (node == null) {
            return null;
        }

        int leftSize = size(node.left);
        if (leftSize > index) {
            return selectRecursive(node.left, index);
        } else if (leftSize < index) {
            return selectRecursive(node.right, index - leftSize - 1);
        } else {
            return node;
        }
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

    public int rankRecursive(Key key) {
        return rankRecursive(root, key);
    }

    private int rankRecursive(Node node, Key key) {
        if (node == null) {
            return 0;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rankRecursive(node.left, key);
        } else if (cmp > 0) {
            return 1 + size(node.left) + rankRecursive(node.right, key);
        } else {
            return size(node.left);
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

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty");
        }

        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
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
        if (node.right == null) {
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
            if (node.right == null) { return node.left; }
            if (node.left == null) { return node.right; }

            Node t = node;
            node.left = t.left;
            node.right = deleteMin(t.right);
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
        int heightRight = heightRecursive(node.right);

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
