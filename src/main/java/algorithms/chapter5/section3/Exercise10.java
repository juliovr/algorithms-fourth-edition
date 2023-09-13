package algorithms.chapter5.section3;

public class Exercise10 {

    public static void main(String[] args) {
        String text = "abcdrenetestreneabdreneabcdd";

        String pattern1 = "rene";
        RabinKarp RabinKarp1 = new RabinKarp(pattern1);
        int count1 = RabinKarp1.count(text);
        System.out.println("Count 1: " + count1 + " Expected: 3");

        System.out.println("Occurrences");
        RabinKarp1.searchAll(text);
        System.out.println("Expected: 4, 12, 19\n");

        String pattern2 = "abcd";
        RabinKarp RabinKarp2 = new RabinKarp(pattern2);
        int count2 = RabinKarp2.count(text);
        System.out.println("Count 2: " + count2 + " Expected: 2");

        System.out.println("Occurrences");
        RabinKarp2.searchAll(text);
        System.out.println("Expected: 0, 23\n");

        String pattern3 = "d";
        RabinKarp RabinKarp3 = new RabinKarp(pattern3);
        int count3 = RabinKarp3.count(text);
        System.out.println("Count 3: " + count3 + " Expected: 4");

        System.out.println("Occurrences");
        RabinKarp3.searchAll(text);
        System.out.println("Expected: 3, 18, 26, 27\n");

        String pattern4 = "zzz";
        RabinKarp RabinKarp4 = new RabinKarp(pattern4);
        int count4 = RabinKarp4.count(text);
        System.out.println("Count 4: " + count4 + " Expected: 0");

        System.out.println("Occurrences");
        RabinKarp4.searchAll(text);
        System.out.println("Expected: No occurrences");
    }
    
}
