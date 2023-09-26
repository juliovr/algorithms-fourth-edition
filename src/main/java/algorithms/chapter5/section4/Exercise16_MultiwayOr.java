package algorithms.chapter5.section4;

public class Exercise16_MultiwayOr {

    public static void main(String[] args) {
        String pattern1 = "(A|B)(C|D)*";
        NFA nfa1 = new NFA(pattern1);
        String text1 = "ACCD";
        boolean matches1 = nfa1.recognizes(text1);
        System.out.println("Text 1 check: " + matches1 + " Expected: true");

        String pattern2 = "(.*AB((C|D|E)F)*G)";
        NFA nfa2 = new NFA(pattern2);
        String text2 = "RENEABCFDFG";
        boolean matches2 = nfa2.recognizes(text2);
        System.out.println("Text 2 check: " + matches2 + " Expected: true");

        String text3 = "RENEABDFEFG";
        boolean matches3 = nfa2.recognizes(text3);
        System.out.println("Text 3 check: " + matches3 + " Expected: true");

        String text4 = "RENEABCFDFEFG";
        boolean matches4 = nfa2.recognizes(text4);
        System.out.println("Text 4 check: " + matches4 + " Expected: true");

        String text5 = "RENEABCDEFG";
        boolean matches5 = nfa2.recognizes(text5);
        System.out.println("Text 5 check: " + matches5 + " Expected: false");

        String pattern3 = "(A|B|C|D|E|F)";
        NFA nfa3 = new NFA(pattern3);
        String text6 = "A";
        boolean matches6 = nfa3.recognizes(text6);
        System.out.println("Text 6 check: " + matches6 + " Expected: true");

        String text7 = "E";
        boolean matches7 = nfa3.recognizes(text7);
        System.out.println("Text 7 check: " + matches7 + " Expected: true");

        String text8 = "Z";
        boolean matches8 = nfa3.recognizes(text8);
        System.out.println("Text 8 check: " + matches8 + " Expected: false");
    }

}
