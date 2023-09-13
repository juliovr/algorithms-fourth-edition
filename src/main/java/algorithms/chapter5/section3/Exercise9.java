package algorithms.chapter5.section3;

public class Exercise9 {

    public static void main(String[] args) {
        String text = "abcdrenetestreneabdreneabcdd";

        String pattern1 = "rene";
        BoyerMoore BoyerMoore1 = new BoyerMoore(pattern1);
        int count1 = BoyerMoore1.count(text);
        System.out.println("Count 1: " + count1 + " Expected: 3");

        System.out.println("Occurrences");
        BoyerMoore1.searchAll(text);
        System.out.println("Expected: 4, 12, 19\n");

        String pattern2 = "abcd";
        BoyerMoore BoyerMoore2 = new BoyerMoore(pattern2);
        int count2 = BoyerMoore2.count(text);
        System.out.println("Count 2: " + count2 + " Expected: 2");

        System.out.println("Occurrences");
        BoyerMoore2.searchAll(text);
        System.out.println("Expected: 0, 23\n");

        String pattern3 = "d";
        BoyerMoore BoyerMoore3 = new BoyerMoore(pattern3);
        int count3 = BoyerMoore3.count(text);
        System.out.println("Count 3: " + count3 + " Expected: 4");

        System.out.println("Occurrences");
        BoyerMoore3.searchAll(text);
        System.out.println("Expected: 3, 18, 26, 27\n");

        String pattern4 = "zzz";
        BoyerMoore BoyerMoore4 = new BoyerMoore(pattern4);
        int count4 = BoyerMoore4.count(text);
        System.out.println("Count 4: " + count4 + " Expected: 0");

        System.out.println("Occurrences");
        BoyerMoore4.searchAll(text);
        System.out.println("Expected: No occurrences");
    }
    
}
