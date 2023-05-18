package algorithms.chapter3.section2;

public class Exercise32_SubtreeCountCheck {

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

        System.out.println("subtreeCountCheck() = true -> " + subtreeCountCheck(bst));
    }

    public static boolean subtreeCountCheck(BST bst) {
        int count = subtreeCountCheck(bst.root.left) + subtreeCountCheck(bst.root.right);
        boolean result = count == bst.root.n;
        return result;
    }

    private static int subtreeCountCheck(BST.Node node) {
        if (node == null) { return 0; }
        int countLeft = subtreeCountCheck(node.left);
        int countRight = subtreeCountCheck(node.right);
        int total = 1 + countLeft + countRight;
        return total;
    }

}
