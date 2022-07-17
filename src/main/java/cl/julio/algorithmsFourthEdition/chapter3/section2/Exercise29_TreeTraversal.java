package cl.julio.algorithmsFourthEdition.chapter3.section2;

public class Exercise29_TreeTraversal {

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

        System.out.println("A C E R S X -> inorderTraversal()");
        Exercise29_TreeTraversal.inorderTraversal(bst);
    }

    private static void inorderTraversal(BST bst) {
        BST.Node current = bst.root;

        while (current != null) {
            if (current.left == null) {
                System.out.print(current.key + " ");

                current = current.right;
            } else {
                BST.Node predecessor = current.left;

                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Temp link to preserve the parent node
                    predecessor.right = current;
                    current = current.left;

                } else {
                    // The right node is the parent due to the temp link
                    predecessor.right = null; // Restore the original null link to the right
                    System.out.print(current.key + " ");
                    current = current.right;
                }
            }

        }
    }

}
