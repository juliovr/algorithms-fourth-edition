package algorithms.chapter3.section3;

public class Exercise21_TestClientInsertInOrder {

    public static void main(String[] args) {
        RedBlackBST<String, String> bst = new RedBlackBST<>();
        bst.put("E", "E");
        bst.put("A", "A");
        bst.put("S", "S");
        bst.put("Y", "Y");
        bst.put("Q", "Q");
        bst.put("U", "U");
        bst.put("T", "T");
        bst.put("I", "I");
        bst.put("O", "O");
        bst.put("N", "N");

        System.out.println(bst);
    }

}
