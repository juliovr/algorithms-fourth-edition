package algorithms.chapter5.section2;

public class Exercise15_UniqueSubstrings {

    public static void main(String[] args) {
        String text1 = "cgcgggcgcg";
        System.out.println("Number of unique substrings: " + countUniqueSubstrings(text1));
        System.out.println("Expected: 37");

        System.out.println();

        String text2 = "renerene";
        System.out.println("Number of unique substrings: " + countUniqueSubstrings(text2));
        System.out.println("Expected: 25");
    }

    private static int countUniqueSubstrings(String text) {
        TSTWithSize<Integer> tst = new TSTWithSize<>();

        for (int substringCountIndex = 1; substringCountIndex <= text.length(); ++substringCountIndex) {
            for (int i = 0; i < text.length() - substringCountIndex + 1; ++i) {
                String substring = text.substring(i, substringCountIndex + i);
                tst.put(substring, 0);
            }
        }

        return tst.size();
    }

}
