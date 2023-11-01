package algorithms.chapter6.suffixarrays;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise30 {

    public static void main(String[] args) {
        String text = "barcelona";
        SuffixArrayNoInnerClass suffixArrayNoInnerClass = new SuffixArrayNoInnerClass(text);

        System.out.println("  i index lcp rank select");
        System.out.println("------------------------------");

        for (int i = 0; i < text.length(); i++) {
            int index = suffixArrayNoInnerClass.index(i);
            String suffix = suffixArrayNoInnerClass.select(i);
            int rank = suffixArrayNoInnerClass.rank(suffix);

            if (i == 0) {
                System.out.printf("%3d %5d %3s %4d %s\n", i, index, "-", rank, suffix);
            } else {
                int lcp = suffixArrayNoInnerClass.lcp(i);
                System.out.printf("%3d %5d %3d %4d %s\n", i, index, lcp, rank, suffix);
            }
        }

        System.out.println("\nLength: " + suffixArrayNoInnerClass.length() + " Expected: 9");

        System.out.println("\n*** Index tests ***");

        int index1 = suffixArrayNoInnerClass.index(0);
        System.out.println("Index 0: " + index1 + " Expected: 8");

        int index2 = suffixArrayNoInnerClass.index(1);
        System.out.println("Index 1: " + index2 + " Expected: 1");

        int index3 = suffixArrayNoInnerClass.index(6);
        System.out.println("Index 6: " + index3 + " Expected: 7");

        int index4 = suffixArrayNoInnerClass.index(8);
        System.out.println("Index 8: " + index4 + " Expected: 2");

        System.out.println("\n*** Select tests ***");

        String select1 = suffixArrayNoInnerClass.select(0);
        System.out.println("Select 0: " + select1 + " Expected: a");

        String select2 = suffixArrayNoInnerClass.select(2);
        System.out.println("Select 2: " + select2 + " Expected: barcelona");

        String select3 = suffixArrayNoInnerClass.select(8);
        System.out.println("Select 8: " + select3 + " Expected: rcelona");

        System.out.println("\n*** Longest common prefix tests ***");

        int lcp1 = suffixArrayNoInnerClass.lcp(1);
        System.out.println("LCP 1: " + lcp1 + " Expected: 1");

        int lcp2 = suffixArrayNoInnerClass.lcp(2);
        System.out.println("LCP 2: " + lcp2 + " Expected: 0");

        System.out.println("\n*** Rank tests ***");

        int rank1 = suffixArrayNoInnerClass.rank("algorithms");
        System.out.println("Rank algorithms: " + rank1 + " Expected: 1");

        int rank2 = suffixArrayNoInnerClass.rank("fenwick");
        System.out.println("Rank fenwick: " + rank2 + " Expected: 5");

        int rank3 = suffixArrayNoInnerClass.rank("rene argento");
        System.out.println("Rank rene argento: " + rank3 + " Expected: 9");


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("----------------------------------------------");
        System.out.println("Running experiments...");

        String[] strings = generateRandomStrings(100_000);

        System.out.print("SuffixArray... ");
        long start = System.currentTimeMillis();
        for (int i = 0; i < strings.length; ++i) {
            SuffixArray suffixArray = new SuffixArray(strings[i]);
            int max = 0;
            for (int j = 1; j < strings[i].length()-1; ++j) {
                int lcp = suffixArray.lcp(j);
                if (lcp > max) {
                    max = lcp;
                }
            }

//            System.out.println("Longest common prefix = " + max);
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");

        System.out.print("SuffixArrayNoInnerClass... ");
        start = System.currentTimeMillis();
        for (int i = 0; i < strings.length; ++i) {
            SuffixArrayNoInnerClass suffixArray = new SuffixArrayNoInnerClass(strings[i]);
            int max = 0;
            for (int j = 1; j < strings[i].length(); ++j) {
                int lcp = suffixArray.lcp(j);
                if (lcp > max) {
                    max = lcp;
                }
            }

//            System.out.println("Longest common prefix = " + max);
        }

        end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");
    }

    private static final char FIRST_PRINTABLE_CHARACTER = ' ';
    private static final char LAST_PRINTABLE_CHARACTER = '~';

    private static String[] generateRandomStrings(int n) {
        String[] strings = new String[n];
        for (int i = 0; i < n; ++i) {
            int length = 100;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; ++j) {
                char c = (char)StdRandom.uniform(FIRST_PRINTABLE_CHARACTER, LAST_PRINTABLE_CHARACTER);
                sb.append(c);
            }
            strings[i] = sb.toString();
        }

        return strings;
    }
    
}
