package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class Exercise15_MinPQCertification {

    public static void main(String[] args) {
        double log = Math.ceil((Math.log10(6) / Math.log10(2)));
        System.out.println(log);
        Comparable[] pq;
        pq = new Comparable[] { 0, 0, 1, 2, 3, 4, 5, 6 };
        System.out.println("Expected = true, given = " + certificate(pq));
        
        pq = new Comparable[] { 0, 0, 2, 1, 3, 4, 5, 6 };
        System.out.println("Expected = true, given = " + certificate(pq));
        
        pq = new Comparable[] { 0, 1, 2, 2, 3, 4, 5, 6 };
        System.out.println("Expected = true, given = " + certificate(pq));
        
        pq = new Comparable[] { 0, 0, 1, 2, 3, 4, 0, 6 };
        System.out.println("Expected = false, given = " + certificate(pq));
        
        pq = new Comparable[] { 0, 0, 1, 2, 3, 4, 0 };
        System.out.println("Expected = false, given = " + certificate(pq));
        
        pq = new Comparable[] { 0, 0, 1, 2, 3, 4, 7 };
        System.out.println("Expected = true, given = " + certificate(pq));

        pq = new Comparable[] { 0, 0, 1, 5, 3, 4, 5, 6 };
        System.out.println("Expected = true, given = " + certificate(pq));
    }
    
    private static boolean certificate(Comparable[] pq) {
        int lastParentNodeIndex = (int) Math.ceil(Math.log10(pq.length) / Math.log10(2));
        for (int i = 1; i <= lastParentNodeIndex; ++i) {
            int left = i*2;
            int right = (i*2) + 1;
            if (less(pq, left, i) || (right < pq.length && less(pq, right, i))) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
}
