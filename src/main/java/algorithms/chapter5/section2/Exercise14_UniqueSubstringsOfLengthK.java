package algorithms.chapter5.section2;

public class Exercise14_UniqueSubstringsOfLengthK {

    public static void main(String[] args) {
        String text1 = "cgcgggcgcg";
        int substringLength1 = 3;
        System.out.println("Number of unique substrings of length " + substringLength1 + ": " + countUniqueSubstrings(text1, substringLength1));
        System.out.println("Expected: 5");

        String text2 = "renerene";
        int substringLength2 = 4;
        System.out.println("Number of unique substrings of length " + substringLength2 + ": " + countUniqueSubstrings(text2, substringLength2));
        System.out.println("Expected: 4");
    }

    private static int countUniqueSubstrings(String text, int substringLength) {
        TSTWithSize<Integer> tst = new TSTWithSize<>();
        int maxIndex = text.length() - substringLength;
        for (int i = 0; i < maxIndex; ++i) {
            String substring = text.substring(i, i + substringLength);
            tst.put(substring, 0);
        }

        return tst.size();
    }

}
