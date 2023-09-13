package algorithms.chapter5.section3;

public class Exercise8 {

    public static void main(String[] args) {
        String text = "abcdrenetestreneabdreneabcdd";

        String pattern1 = "rene";
        KMP KMP1 = new KMP(pattern1);
        int count1 = KMP1.count(text);
        System.out.println("Count 1: " + count1 + " Expected: 3");

        System.out.println("Occurrences");
        KMP1.searchAll(text);
        System.out.println("Expected: 4, 12, 19\n");

        String pattern2 = "abcd";
        KMP KMP2 = new KMP(pattern2);
        int count2 = KMP2.count(text);
        System.out.println("Count 2: " + count2 + " Expected: 2");

        System.out.println("Occurrences");
        KMP2.searchAll(text);
        System.out.println("Expected: 0, 23\n");

        String pattern3 = "d";
        KMP KMP3 = new KMP(pattern3);
        int count3 = KMP3.count(text);
        System.out.println("Count 3: " + count3 + " Expected: 4");

        System.out.println("Occurrences");
        KMP3.searchAll(text);
        System.out.println("Expected: 3, 18, 26, 27\n");

        String pattern4 = "zzz";
        KMP KMP4 = new KMP(pattern4);
        int count4 = KMP4.count(text);
        System.out.println("Count 4: " + count4 + " Expected: 0");

        System.out.println("Occurrences");
        KMP4.searchAll(text);
        System.out.println("Expected: No occurrences");
    }
    
}
