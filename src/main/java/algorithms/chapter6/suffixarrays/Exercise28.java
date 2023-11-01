package algorithms.chapter6.suffixarrays;

public class Exercise28 {

    public static void main(String[] args) {
        String text = "barcelona";
        SuffixArray suffixArray = new SuffixArray(text);

        System.out.println("  i index lcp rank select");
        System.out.println("------------------------------");

        for (int i = 0; i < text.length(); i++) {
            int index = suffixArray.index(i);
            String suffix = "\"" + text.substring(index) + "\"";
            assert text.substring(index).equals(suffixArray.select(i));

            int rank = suffixArray.rank(text.substring(index));

            if (i == 0) {
                System.out.printf("%3d %5d %3s %4d %s\n", i, index, "-", rank, suffix);
            } else {
                int lcp = suffixArray.lcp(i);
                System.out.printf("%3d %5d %3d %4d %s\n", i, index, lcp, rank, suffix);
            }
        }

        System.out.println("\n*** Longest common prefix tests ***");

        int lcp1 = suffixArray.lcp(1);
        System.out.println("LCP 1: " + lcp1 + " Expected: 1");

        int lcp2 = suffixArray.lcp(2);
        System.out.println("LCP 2: " + lcp2 + " Expected: 0");

        System.out.println("\n*** Rank tests ***");

        int rank1 = suffixArray.rank("algorithms");
        System.out.println("Rank algorithms: " + rank1 + " Expected: 1");

        int rank2 = suffixArray.rank("fenwick");
        System.out.println("Rank fenwick: " + rank2 + " Expected: 5");

        int rank3 = suffixArray.rank("rene argento");
        System.out.println("Rank rene argento: " + rank3 + " Expected: 9");
    }
    
}
