package algorithms.chapter5.section3;

public class Exercise15 {

    public static void main(String[] args) {
        String text = "abacadabrabracabracadabrabrabracad";

        String pattern1 = "abracadabra";
        BruteForceRL bruteForceRL1 = new BruteForceRL(pattern1);
        int index1 = bruteForceRL1.search(text);
        System.out.println("Index 1: " + index1 + " Expected: 14");

        String pattern2 = "rab";
        BruteForceRL bruteForceRL2 = new BruteForceRL(pattern2);
        int index2 = bruteForceRL2.search(text);
        System.out.println("Index 2: " + index2 + " Expected: 8");

        String pattern3 = "bcara";
        BruteForceRL bruteForceRL3 = new BruteForceRL(pattern3);
        int index3 = bruteForceRL3.search(text);
        System.out.println("Index 3: " + index3 + " Expected: 34");

        String pattern4 = "rabrabracad";
        BruteForceRL bruteForceRL4 = new BruteForceRL(pattern4);
        int index4 = bruteForceRL4.search(text);
        System.out.println("Index 4: " + index4 + " Expected: 23");

        String pattern5 = "abacad";
        BruteForceRL bruteForceRL5 = new BruteForceRL(pattern5);
        int index5 = bruteForceRL5.search(text);
        System.out.println("Index 5: " + index5 + " Expected: 0");
    }
    
}
