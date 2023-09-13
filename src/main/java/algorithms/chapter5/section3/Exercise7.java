package algorithms.chapter5.section3;

public class Exercise7 {

    public static void main(String[] args) {
        String text = "abcdrenetestreneabdreneabcdd";

        String pattern1 = "rene";
        Brute brute1 = new Brute(pattern1);
        int count1 = brute1.count(text);
        System.out.println("Count 1: " + count1 + " Expected: 3");

        System.out.println("Occurrences");
        brute1.searchAll(text);
        System.out.println("Expected: 4, 12, 19\n");

        String pattern2 = "abcd";
        Brute brute2 = new Brute(pattern2);
        int count2 = brute2.count(text);
        System.out.println("Count 2: " + count2 + " Expected: 2");

        System.out.println("Occurrences");
        brute2.searchAll(text);
        System.out.println("Expected: 0, 23\n");

        String pattern3 = "d";
        Brute brute3 = new Brute(pattern3);
        int count3 = brute3.count(text);
        System.out.println("Count 3: " + count3 + " Expected: 4");

        System.out.println("Occurrences");
        brute3.searchAll(text);
        System.out.println("Expected: 3, 18, 26, 27\n");

        String pattern4 = "zzz";
        Brute brute4 = new Brute(pattern4);
        int count4 = brute4.count(text);
        System.out.println("Count 4: " + count4 + " Expected: 0");

        System.out.println("Occurrences");
        brute4.searchAll(text);
        System.out.println("Expected: No occurrences");
    }

}
