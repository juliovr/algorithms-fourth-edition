package algorithms.chapter6.btrees;

public class Exercise17 {

    public static void main(String[] args) {
        BTreeSET<String> btree = new BTreeSET<>("*");
        btree.add("B");
        btree.add("C");
        btree.add("K");
        btree.add("Q");
        btree.add("U");
        btree.add("D");
//        btree.add("F");
        btree.add("H");

        btree.draw();
    }

}
