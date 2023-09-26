package algorithms.chapter5.section4;

public class Exercise21_Complement {

    public static void main(String[] args) {
        String pattern1 = "RENE[^ABC]";
        NFA nfa1 = new NFA(pattern1);
        String text1 = "RENED";
        boolean matches1 = nfa1.recognizes(text1);
        System.out.println("Text 1 check: " + matches1 + " Expected: true");

        String text2 = "RENEA";
        boolean matches2 = nfa1.recognizes(text2);
        System.out.println("Text 2 check: " + matches2 + " Expected: false");

        String text3 = "RENEC";
        boolean matches3 = nfa1.recognizes(text3);
        System.out.println("Text 3 check: " + matches3 + " Expected: false");

        String pattern2 = "A[^A-Z0]+Z";
        NFA nfa2 = new NFA(pattern2);
        String text4 = "AbZ";
        boolean matches4 = nfa2.recognizes(text4);
        System.out.println("Text 4 check: " + matches4 + " Expected: true");

        String text5 = "AAZ";
        boolean matches5 = nfa2.recognizes(text5);
        System.out.println("Text 5 check: " + matches5 + " Expected: false");

        String text6 = "A0Z";
        boolean matches6 = nfa2.recognizes(text6);
        System.out.println("Text 6 check: " + matches6 + " Expected: false");

        String text7 = "AabcdeZ";
        boolean matches7 = nfa2.recognizes(text7);
        System.out.println("Text 7 check: " + matches7 + " Expected: true");

        String pattern3 = "A[^A-Z0]+ZZ";
        NFA nfa3 = new NFA(pattern3);
        String text8 = "AbcZZ";
        boolean matches8 = nfa3.recognizes(text8);
        System.out.println("Text 8 check: " + matches8 + " Expected: true");

        String pattern4 = "A[^A-Z0]*ZZ";
        NFA nfa4 = new NFA(pattern4);
        String text9 = "AZZ";
        boolean matches9 = nfa4.recognizes(text9);
        System.out.println("Text 9 check: " + matches9 + " Expected: true");

        String text10 = "AJZZ";
        boolean matches10 = nfa4.recognizes(text10);
        System.out.println("Text 10 check: " + matches10 + " Expected: false");

        String text11 = "Abcdef123ZZ";
        boolean matches11 = nfa4.recognizes(text11);
        System.out.println("Text 11 check: " + matches11 + " Expected: true");

        String pattern5 = "A([^A-Z0]|[^a-f])+[^a-f]Z";
        NFA nfa5 = new NFA(pattern5);
        String text12 = "ABgZ";
        boolean matches12 = nfa5.recognizes(text12);
        System.out.println("Text 12 check: " + matches12 + " Expected: true");

        String text13 = "ABCDEFGagZ";
        boolean matches13 = nfa5.recognizes(text13);
        System.out.println("Text 13 check: " + matches13 + " Expected: true");

        String text14 = "ABZ";
        boolean matches14 = nfa5.recognizes(text14);
        System.out.println("Text 14 check: " + matches14 + " Expected: false");

        String text15 = "AAZafgZ";
        boolean matches15 = nfa5.recognizes(text15);
        System.out.println("Text 15 check: " + matches15 + " Expected: true");

        String text16 = "AAZaffZ";
        boolean matches16 = nfa5.recognizes(text16);
        System.out.println("Text 16 check: " + matches16 + " Expected: false");
    }
    
}
