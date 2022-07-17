package cl.julio.algorithmsFourthEdition.chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Exercise37_LevelOrderTraversal {

    public static void main(String[] args) {
        /*
                R
              C   S
             A E
         */
        BST<String, String> bst = new BST<>();
        bst.putRecursive("R", "1");
        bst.putRecursive("C", "2");
        bst.putRecursive("A", "3");
        bst.putRecursive("E", "4");
        bst.putRecursive("S", "5");
        bst.putRecursive("X", "6");

        System.out.println("levelOrderTraversal():");
        System.out.println("expected = R C S A E X");
        System.out.print("actual   = ");
        levelOrderTraversal(bst);
    }

    public static void levelOrderTraversal(BST bst) {
        Queue<BST.Node> queue = new Queue<>();
        queue.enqueue(bst.root);
        while (!queue.isEmpty()) {
            BST.Node node = queue.dequeue();
            System.out.print(node.key + " ");

            if (node.left != null) { queue.enqueue(node.left); }
            if (node.right != null) { queue.enqueue(node.right); }
        }
    }

}
