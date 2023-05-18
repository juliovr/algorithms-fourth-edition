package algorithms.chapter3.section2;

public class Exercise31_Certification {

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

        System.out.println("isBST() = true -> " + isBST(bst));
    }

    public static boolean isBST(BST bst) {
        //return isBST(root, null, null);
        BST.Node node;

        // Left
        node = bst.root;
        while (node.left != null) {
            if (node.key.compareTo(node.left.key) <= 0) {
                return false;
            }

            node = node.left;
        }

        // Right
        node = bst.root;
        while (node.right != null) {
            if (node.key.compareTo(node.right.key) >= 0) {
                return false;
            }

            node = node.right;
        }

        return true;
    }

    private boolean isBST(BST<String, String>.Node node, String min, String max) {
        if (node == null) { return true; }

        if (min != null && node.key.compareTo(min) <= 0) { return false; }
        if (max != null && node.key.compareTo(max) >= 0) { return false; }

        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

}
