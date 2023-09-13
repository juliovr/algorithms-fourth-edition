package algorithms.chapter5.section3;

public class Exercise1 {

    public static void main(String[] args) {
        String text = "abacadabrabracabracadabrabrabracad";

        String pattern1 = "abracadabra";
        Brute bruteforce1 = new Brute(pattern1);
        int index1 = bruteforce1.search(text);
        System.out.println("Index 1: " + index1 + " Expected: 14");

        String pattern2 = "rab";
        Brute bruteforce2 = new Brute(pattern2);
        int index2 = bruteforce2.search(text);
        System.out.println("Index 2: " + index2 + " Expected: 8");

        String pattern3 = "bcara";
        Brute bruteforce3 = new Brute(pattern3);
        int index3 = bruteforce3.search(text);
        System.out.println("Index 3: " + index3 + " Expected: 34");

        String pattern4 = "rabrabracad";
        Brute bruteforce4 = new Brute(pattern4);
        int index4 = bruteforce4.search(text);
        System.out.println("Index 4: " + index4 + " Expected: 23");

        String pattern5 = "abacad";
        Brute bruteforce5 = new Brute(pattern5);
        int index5 = bruteforce5.search(text);
        System.out.println("Index 5: " + index5 + " Expected: 0");
    }
    
}
