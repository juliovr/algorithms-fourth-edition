package algorithms.chapter5.section4;

public class Exercise20_Range {

    public static void main(String[] args) {
        String pattern1 = "RENE[A-Z]";
        NFA nfa1 = new NFA(pattern1);
        String text1 = "RENEA";
        boolean matches1 = nfa1.recognizes(text1);
        System.out.println("Text 1 check: " + matches1 + " Expected: true");

        String text2 = "RENER";
        boolean matches2 = nfa1.recognizes(text2);
        System.out.println("Text 2 check: " + matches2 + " Expected: true");

        String text3 = "RENEZ";
        boolean matches3 = nfa1.recognizes(text3);
        System.out.println("Text 3 check: " + matches3 + " Expected: true");

        String text4 = "RENEa";
        boolean matches4 = nfa1.recognizes(text4);
        System.out.println("Text 4 check: " + matches4 + " Expected: false");

        String text5 = "RENEb";
        boolean matches5 = nfa1.recognizes(text5);
        System.out.println("Text 5 check: " + matches5 + " Expected: false");

        String pattern2 = "[A-j]*RENE[0-8]+";
        NFA nfa2 = new NFA(pattern2);
        String text6 = "AbjRENE012345678";
        boolean matches6 = nfa2.recognizes(text6);
        System.out.println("Text 6 check: " + matches6 + " Expected: true");

        String text7 = "AbkRENE012345678";
        boolean matches7 = nfa2.recognizes(text7);
        System.out.println("Text 7 check: " + matches7 + " Expected: false");

        String text8 = "AbjRENE0123456789";
        boolean matches8 = nfa2.recognizes(text8);
        System.out.println("Text 8 check: " + matches8 + " Expected: false");

        String pattern3 = "[a-kA-K0-5]+";
        NFA nfa3 = new NFA(pattern3);
        String text9 = "aB234";
        boolean matches9 = nfa3.recognizes(text9);
        System.out.println("Text 9 check: " + matches9 + " Expected: true");

        String text10 = "mB2";
        boolean matches10 = nfa3.recognizes(text10);
        System.out.println("Text 10 check: " + matches10 + " Expected: false");

        String text11 = "aM234";
        boolean matches11 = nfa3.recognizes(text11);
        System.out.println("Text 11 check: " + matches11 + " Expected: false");

        String text12 = "aB236";
        boolean matches12 = nfa3.recognizes(text12);
        System.out.println("Text 12 check: " + matches12 + " Expected: false");

        String pattern4 = "([a-kA-K0-5]+ABC)*";
        NFA nfa4 = new NFA(pattern4);
        String text13 = "0ABC";
        boolean matches13 = nfa4.recognizes(text13);
        System.out.println("Text 13 check: " + matches13 + " Expected: true");

        String text14 = "0aAABC";
        boolean matches14 = nfa4.recognizes(text14);
        System.out.println("Text 14 check: " + matches14 + " Expected: true");

        String pattern5 = "[A-Zabc]+";
        NFA nfa5 = new NFA(pattern5);
        String text15 = "abbbbbcABCDEFZ";
        boolean matches15 = nfa5.recognizes(text15);
        System.out.println("Text 15 check: " + matches15 + " Expected: true");

        String text16 = "abcdA";
        boolean matches16 = nfa5.recognizes(text16);
        System.out.println("Text 16 check: " + matches16 + " Expected: false");
    }
    
}
