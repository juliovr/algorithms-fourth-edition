package algorithms.chapter6.suffixarrays;

public class Exercise27 {

    public static void main(String[] args) {
        // Test charAt in circular suffix
//        String text = "abcd";
//        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
//        System.out.printf("%10s %10s %10s %10s %10s\n", "text", "charAt(0)", "charAt(1)", "charAt(2)", "charAt(3)");
//        for (int i = 0; i < text.length(); ++i) {
//            String s = circularSuffixArray.select(i);
//            System.out.printf("%10s", s);
//
//            for (int j = 0; j < text.length(); ++j) {
//                System.out.printf(" %10c", circularSuffixArray.circularSuffixes[i].charAt(j));
//            }
//
//            System.out.println();
//        }

        String text = "barcelona";

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);

        System.out.println("  i index lcp rank select");
        System.out.println("------------------------------");

        for (int i = 0; i < text.length(); i++) {
            int index = circularSuffixArray.index(i);
            String suffix = circularSuffixArray.select(i);
            int rank = circularSuffixArray.rank(suffix);

            if (i == 0) {
                System.out.printf("%3d %5d %3s %4d %s\n", i, index, "-", rank, suffix);
            } else {
                int longestCommonPrefix = circularSuffixArray.lcp(i);
                System.out.printf("%3d %5d %3d %4d %s\n", i, index, longestCommonPrefix, rank, suffix);
            }
        }

        System.out.println("\nLength: " + circularSuffixArray.length() + " Expected: 9");

        System.out.println("\n*** Index tests ***");

        int index1 = circularSuffixArray.index(0);
        System.out.println("Index 0: " + index1 + " Expected: 8");

        int index2 = circularSuffixArray.index(1);
        System.out.println("Index 1: " + index2 + " Expected: 1");

        int index3 = circularSuffixArray.index(6);
        System.out.println("Index 6: " + index3 + " Expected: 7");

        int index4 = circularSuffixArray.index(8);
        System.out.println("Index 8: " + index4 + " Expected: 2");

        System.out.println("\n*** Select tests ***");

        String select1 = circularSuffixArray.select(0);
        System.out.println("Select 0: " + select1 + " Expected: abarcelon");

        String select2 = circularSuffixArray.select(2);
        System.out.println("Select 2: " + select2 + " Expected: barcelona");

        String select3 = circularSuffixArray.select(8);
        System.out.println("Select 8: " + select3 + " Expected: rcelonaba");

        System.out.println("\n*** Longest common prefix tests ***");

        int lcp1 = circularSuffixArray.lcp(1);
        System.out.println("LCP 1: " + lcp1 + " Expected: 1");

        int lcp2 = circularSuffixArray.lcp(2);
        System.out.println("LCP 2: " + lcp2 + " Expected: 0");

        System.out.println("\n*** Rank tests ***");

        int rank1 = circularSuffixArray.rank("algorithms");
        System.out.println("Rank algorithms: " + rank1 + " Expected: 1");

        int rank2 = circularSuffixArray.rank("fenwick");
        System.out.println("Rank fenwick: " + rank2 + " Expected: 5");

        int rank3 = circularSuffixArray.rank("rene argento");
        System.out.println("Rank rene argento: " + rank3 + " Expected: 9");
    }
    
}
