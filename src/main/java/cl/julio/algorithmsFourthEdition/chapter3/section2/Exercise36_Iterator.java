package cl.julio.algorithmsFourthEdition.chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Exercise36_Iterator {

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

        System.out.println("KeysStack() = A C E R S X -> " + stackTraversal(bst));
    }

    public static Iterable<Comparable> stackTraversal(BST bst) {
        Queue<Comparable> queue = new Queue<>();

        Stack<BST.Node> stack = new Stack<>();
        BST.Node node = bst.root;
        while (node != null) {
            if (node.left == null) {
//                System.out.print(node.key + " ");
                queue.enqueue(node.key);
            } else {
                stack.push(node);
                node = node.left;
                continue;
            }

            if (node.right == null) {
                if (stack.isEmpty()) {
                    break;
                }

                BST.Node parent = stack.pop();
//                System.out.print(parent.key + " ");
                queue.enqueue(parent.key);
                node = parent.right;
            } else {
                node = node.right;
            }
        }

        return queue;
    }

}
