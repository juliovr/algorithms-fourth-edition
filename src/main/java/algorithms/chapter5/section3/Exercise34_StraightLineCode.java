package algorithms.chapter5.section3;

public class Exercise34_StraightLineCode {

    public static void main(String[] args) {
        String pattern1 = "AABAAA";
        String straightLineProgram1 = generateStraightLineProgram(pattern1);
        System.out.println("Straight line program 1:");
        System.out.println(straightLineProgram1);
    }

    private static String generateStraightLineProgram(String pattern) {
        StringBuilder sb = new StringBuilder();
        sb.append("    int i = 0;\n");

        KMP kmp = new KMP(pattern);
        int[][] dfa = kmp.dfa();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            int bestGotoState = 0;

            // Test mismatch cases to get the higher number of possible state
            for (int j = 0; j < dfa.length; ++j) {
                if ((char)j == c) continue; // Skip the same char because the test is for mismatch cases

                int gotoState = dfa[j][i];
                if (gotoState > bestGotoState) {
                    bestGotoState = gotoState;
                }
            }

            sb.append(String.format("s%d: if (txt[i++] != '%c') goto s%d;\n", i, c, bestGotoState));
        }

        sb.append("    return i - 6;\n");

        return sb.toString();
    }

}
