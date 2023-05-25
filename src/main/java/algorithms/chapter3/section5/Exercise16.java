package algorithms.chapter3.section5;

public class Exercise16 {

    public static void main(String[] args) {
        SparseVector a = new SparseVector();
        a.put(0, 0.05);
        a.put(1, 0.04);
        a.put(2, 0.36);
        a.put(3, 0.37);
        a.put(4, 0.19);

        SparseVector b = new SparseVector();
        b.put(0, 0.036);
        b.put(1, 0.3);
        b.put(2, 0.36);
        b.put(6, 0.03);
        b.put(7, 0.01);
        b.put(8, 10.0);

        SparseVector c = a.sum(b);
        System.out.println(c);
    }

}
