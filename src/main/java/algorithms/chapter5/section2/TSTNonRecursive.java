package algorithms.chapter5.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class TSTNonRecursive<Value> {

    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
    }

    public Value get(String key) {
//        Node x = root;
//        int d = 0;
//        while (x != null) {
//            if (d == key.length()) {
//                break;
//            }
//
//            char c = key.charAt(d);
//            if (c < x.c) {
//                x = x.left;
//            } else if (c > x.c) {
//                x = x.right;
//            } else if (d < key.length() - 1) {
//                x = x.mid;
//                d++;
//            } else {
//                d++;
//            }
//        }
//
//        if (x == null) {
//            return null;
//        } else {
//            return x.val;
//        }
        Node x = getNode(key);
        if (x == null) {
            return null;
        } else {
            return x.val;
        }
    }

    private Node getNode(String key) {
        Node x = root;
        int d = 0;
        while (x != null) {
            if (d == key.length()) {
                break;
            }

            char c = key.charAt(d);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else if (d < key.length() - 1) {
                x = x.mid;
                d++;
            } else {
                d++;
            }
        }

        return x;
    }

    private enum Direction {
        LEFT, MID, RIGHT, UNKNOWN
    }

    public void put(String key, Value val) {
        int d = 0;
        char c = key.charAt(d);
        if (root == null) {
            root = new Node();
            root.c = c;
        }

        Node current = root;
        Node parent = null;
        Direction direction = Direction.UNKNOWN;
        while (true) {
            c = key.charAt(d);

            if (current == null) {
                current = new Node();
                current.c = c;

                switch (direction) {
                    case LEFT:
                        parent.left = current;
                        break;
                    case RIGHT:
                        parent.right = current;
                        break;
                    case MID:
                        parent.mid = current;
                }
            }

            parent = current;

            if (c < current.c) {
                current = current.left;
                direction = Direction.LEFT;
            } else if (c > current.c) {
                current = current.right;
                direction = Direction.RIGHT;
            } else if (d < key.length() - 1) {
                current = current.mid;
                d++;
                direction = Direction.MID;
            } else {
                current.val = val;
                break;
            }
        }
    }

    public void delete(String key) {
        // TODO: implement
//        int d = 0;
//
//        Node current = root;
//        Node parent = null;
//        Direction direction = Direction.UNKNOWN;
//        while (true) {
//            char c = key.charAt(d);
//        }
    }

    private class ProcessingNode {
        Node node;
        String prefix;
    }

    public Iterable<String> keys() {
        Queue<String> queue = new Queue<>();
        if (root == null) {
            return queue;
        }

        Stack<ProcessingNode> nodes = new Stack<>();
        ProcessingNode rootNode = new ProcessingNode();
        rootNode.node = root;
        rootNode.prefix = "";
        nodes.push(rootNode);
        while (!nodes.isEmpty()) {
            ProcessingNode pNode = nodes.pop();
            Node x = pNode.node;
            String prefix = pNode.prefix;

            if (x.val != null) {
                queue.enqueue(prefix + x.c);
            }

            if (x.right != null) {
                ProcessingNode rightNode = new ProcessingNode();
                rightNode.node = x.right;
                rightNode.prefix = prefix;
                nodes.push(rightNode);
            }

            if (x.mid != null) {
                ProcessingNode midNode = new ProcessingNode();
                midNode.node = x.mid;
                midNode.prefix = prefix + x.c;
                nodes.push(midNode);
            }

            if (x.left != null) {
                ProcessingNode leftNode = new ProcessingNode();
                leftNode.node = x.left;
                leftNode.prefix = prefix;
                nodes.push(leftNode);
            }
        }

        return queue;
    }

}
