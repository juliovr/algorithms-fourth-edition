package algorithms.chapter6.suffixarrays;

public class Exercise32_BurrowsWheelerTransform {

    public static void main(String[] args) {
        String text = "mississippi$";
//        String text = "julio$";
        String BWT = calculateBWT(text);
        System.out.println("BWT: " + BWT);
        String BWI = calculateBWI(BWT);
        System.out.println("BWI: " + BWI);
    }

    private static String calculateBWT(String text) {
        char[] BWT = new char[text.length()];
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
        int lastCharIndex = circularSuffixArray.length() - 1;
        for (int i = 0; i < circularSuffixArray.length(); ++i) {
            BWT[i] = circularSuffixArray.select(i).charAt(lastCharIndex);
        }

        return String.valueOf(BWT);
    }

    private static final int R = 256;

    private static String calculateBWI(String BWT) {
        char[] BWI = new char[BWT.length()];
        char[] BWTSorted = new char[BWT.length()];

        char[] count = new char[R + 1];

        // Compute frequency count
        for (int i = 0; i < BWI.length; ++i) {
            char c = BWT.charAt(i);
            count[c + 1]++;
        }

        // Transform count to indices
        for (int i = 0; i < R; ++i) {
            count[i+1] += count[i];
        }

        int[] next = new int[BWTSorted.length];
        for (int i = 0; i < BWI.length; ++i) {
            char c = BWT.charAt(i);
            char index = count[c]++;
            BWTSorted[index] = c;
            next[index] = i;
        }

        for (int i = 0, index = next[0]; i < BWI.length; ++i, index = next[index]) {
            BWI[i] = BWTSorted[index];
        }

        return String.valueOf(BWI);
    }

}
