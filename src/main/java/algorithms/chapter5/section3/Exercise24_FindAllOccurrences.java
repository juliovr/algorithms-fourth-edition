package algorithms.chapter5.section3;

import java.util.StringJoiner;

public class Exercise24_FindAllOccurrences {

    public static void main(String[] args) {
        System.out.println("*** Bruteforce implementation tests ***");
        test(SubstringSearch.BRUTEFORCE);

        System.out.println("*** Knuth-Morris-Pratt tests ***");
        test(SubstringSearch.KNUTH_MORRIS_PRATT);

        System.out.println("*** Boyer-Moore tests ***");
        test(SubstringSearch.BOYER_MOORE);

        System.out.println("*** Rabin-Karp tests ***");
        test(SubstringSearch.RABIN_KARP);
    }

    private static void test(int substringSearchMethodId) {

        String text = "abcdrenetestreneabdreneabcdd";

        String pattern1 = "rene";
        SubstringSearch substringSearch1 = createSubstringSearch(substringSearchMethodId, pattern1);

        if (substringSearch1 == null) {
            return;
        }

        StringJoiner offsets1 = new StringJoiner(", ");
        for (int offset : substringSearch1.findAll(text)) {
            offsets1.add(String.valueOf(offset));
        }

        System.out.println("Offsets 1: " + offsets1.toString());
        System.out.println("Expected: 4, 12, 19\n");


        String pattern2 = "abcd";
        SubstringSearch substringSearch2 = createSubstringSearch(substringSearchMethodId, pattern2);

        StringJoiner offsets2 = new StringJoiner(", ");
        for (int offset : substringSearch2.findAll(text)) {
            offsets2.add(String.valueOf(offset));
        }

        System.out.println("Offsets 2: " + offsets2.toString());
        System.out.println("Expected: 0, 23\n");


        String pattern3 = "d";
        SubstringSearch substringSearch3 = createSubstringSearch(substringSearchMethodId, pattern3);

        StringJoiner offsets3 = new StringJoiner(", ");
        for (int offset : substringSearch3.findAll(text)) {
            offsets3.add(String.valueOf(offset));
        }

        System.out.println("Offsets 3: " + offsets3.toString());
        System.out.println("Expected: 3, 18, 26, 27\n");


        String pattern4 = "zzz";
        SubstringSearch substringSearch4 = createSubstringSearch(substringSearchMethodId, pattern4);

        StringJoiner offsets4 = new StringJoiner(", ");
        for (int offset : substringSearch4.findAll(text)) {
            offsets4.add(String.valueOf(offset));
        }

        System.out.println("Offsets 4: " + offsets4.toString());
        System.out.println("Expected: \n");
    }

    private static SubstringSearch createSubstringSearch(int substringSearchMethodId, String pattern) {
        SubstringSearch substringSearch = null;

        switch (substringSearchMethodId) {
            case SubstringSearch.BRUTEFORCE:
                substringSearch = new Brute(pattern);
                break;
            case SubstringSearch.KNUTH_MORRIS_PRATT:
                substringSearch = new KMP(pattern);
                break;
            case SubstringSearch.BOYER_MOORE:
                substringSearch = new BoyerMoore(pattern);
                break;
            case SubstringSearch.RABIN_KARP:
                substringSearch = new RabinKarp(pattern);
                break;
        }

        return substringSearch;
    }
    
}
