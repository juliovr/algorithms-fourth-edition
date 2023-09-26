package algorithms.chapter5.section4;

public class Exercise17_Wildcard {

    public static void main(String[] args) {
        String pattern1 = ".*NEEDLE.*";
        NFA nfa1 = new NFA(pattern1);
        String text1 = "A HAYSTACK NEEDLE IN";
        boolean matches1 = nfa1.recognizes(text1);
        System.out.println("Text 1 check: " + matches1 + " Expected: true");

        String pattern2 = "R.N.1.3";
        NFA regularExpressionMatcherWildcard2 = new NFA(pattern2);
        String text2 = "RENE123";
        boolean matches2 = regularExpressionMatcherWildcard2.recognizes(text2);
        System.out.println("Text 2 check: " + matches2 + " Expected: true");

        String text3 = "RRNN193";
        boolean matches3 = regularExpressionMatcherWildcard2.recognizes(text3);
        System.out.println("Text 3 check: " + matches3 + " Expected: true");

        String text4 = "RENE333";
        boolean matches4 = regularExpressionMatcherWildcard2.recognizes(text4);
        System.out.println("Text 4 check: " + matches4 + " Expected: false");
    }

}
