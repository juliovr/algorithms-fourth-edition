package algorithms.chapter6.btrees;

public class Exercise15 {

    public static void main(String[] args) {
        BTreeSET<String> btree = new BTreeSET<>("*");
        btree.add("Rene");
        btree.add("Sedgewick");
        btree.add("Wayne");
        btree.add("B-tree");
        btree.add("Binary tree");
        btree.add("Red-black tree");
        btree.add("AVL tree");
        btree.add("Segment tree");
        btree.add("Fenwick tree");

        System.out.println("Contains Binary tree: " + btree.contains("Binary tree") + " Expected: true");
        System.out.println("Contains Red-black tree: " + btree.contains("Red-black tree") + " Expected: true");
        System.out.println("Contains B-tree: " + btree.contains("B-tree") + " Expected: true");
        System.out.println("Contains Rene: " + btree.contains("Rene") + " Expected: true");
        System.out.println("Contains Binary trees: " + btree.contains("Binary trees") + " Expected: false");
        System.out.println("Contains Binary tre: " + btree.contains("Binary tre") + " Expected: false");
        System.out.println("Contains Binary : " + btree.contains("Binary ") + " Expected: false");
    }

}
