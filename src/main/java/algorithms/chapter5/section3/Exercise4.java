package algorithms.chapter5.section3;

public class Exercise4 {

    public static void main(String[] args) {
        String text = " abacada  abr   braca  brabrabracad";

        int index1 = findBlankCharacters(text, 1);
        System.out.println("Index 1: " + index1 + " Expected: 0");

        int index2 = findBlankCharacters(text, 2);
        System.out.println("Index 2: " + index2 + " Expected: 8");

        int index3 = findBlankCharacters(text, 3);
        System.out.println("Index 3: " + index3 + " Expected: 13");

        int index4 = findBlankCharacters(text, 4);
        System.out.println("Index 4: " + index4 + " Expected: 35");
    }

    private static int findBlankCharacters(String text, int m) {
        int n = text.length();
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (text.charAt(i) == ' ') {
                ++j;
            } else {
                j = 0;
            }

            if (j == m) {
                return i - m + 1;
            }
        }

        return n;
    }

}
