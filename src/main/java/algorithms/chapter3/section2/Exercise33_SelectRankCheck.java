package algorithms.chapter3.section2;

public class Exercise33_SelectRankCheck {

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

        System.out.println("selectRankCheck() = true -> " + selectRankCheck(bst));
    }

    public static boolean selectRankCheck(BST<String, String> bst) {
        for (int i = 0; i < bst.size(); ++i) {
            if (i != bst.rank(bst.select(i))) {
                return false;
            }
        }

        for (String key : bst.keys()) {
            if (key.compareTo(bst.select(bst.rank(key))) != 0) {
                return false;
            }
        }

        return true;
    }

}
