package algorithms.chapter5.section4;

public class Exercise19_SpecifiedSet {

    public static void main(String[] args) {
        String pattern1 = ".*[ABC]Z";
        NFA nfa1 = new NFA(pattern1);
        String text1 = "This is a text AZ";
        boolean matches1 = nfa1.recognizes(text1);
        System.out.println("Text 1 check: " + matches1 + " Expected: true");

        String text2 = "This is a text BZ";
        boolean matches2 = nfa1.recognizes(text2);
        System.out.println("Text 2 check: " + matches2 + " Expected: true");

        String text3 = "This is a text CZ";
        boolean matches3 = nfa1.recognizes(text3);
        System.out.println("Text 3 check: " + matches3 + " Expected: true");

        String text4 = "This is a text DZ";
        boolean matches4 = nfa1.recognizes(text4);
        System.out.println("Text 4 check: " + matches4 + " Expected: false");

        String pattern2 = "R[AEIOU]N";
        NFA nfa2 = new NFA(pattern2);
        String text5 = "RAN";
        boolean matches5 = nfa2.recognizes(text5);
        System.out.println("Text 5 check: " + matches5 + " Expected: true");

        String text6 = "RIN";
        boolean matches6 = nfa2.recognizes(text6);
        System.out.println("Text 6 check: " + matches6 + " Expected: true");

        String text7 = "RUN";
        boolean matches7 = nfa2.recognizes(text7);
        System.out.println("Text 7 check: " + matches7 + " Expected: true");

        String text8 = "RAEN";
        boolean matches8 = nfa2.recognizes(text8);
        System.out.println("Text 8 check: " + matches8 + " Expected: false");

        String text9 = "RAZN";
        boolean matches9 = nfa2.recognizes(text9);
        System.out.println("Text 9 check: " + matches9 + " Expected: false");

        String pattern3 = "R[AEIOU]*N";
        NFA nfa3 = new NFA(pattern3);
        String text10 = "RAEIOUN";
        boolean matches10 = nfa3.recognizes(text10);
        System.out.println("Text 10 check: " + matches10 + " Expected: true");

        String text11 = "RN";
        boolean matches11 = nfa3.recognizes(text11);
        System.out.println("Text 11 check: " + matches11 + " Expected: true");

        String pattern4 = "R[AEIOU]+N";
        NFA nfa4 = new NFA(pattern4);
        String text12 = "RAEIOUN";
        boolean matches12 = nfa4.recognizes(text12);
        System.out.println("Text 12 check: " + matches12 + " Expected: true");

        String text13 = "RN";
        boolean matches13 = nfa4.recognizes(text13);
        System.out.println("Text 13 check: " + matches13 + " Expected: false");

        String pattern5 = "R(0|1|[AEIOU]+Z)+N";
        NFA nfa5 = new NFA(pattern5);
        String text14 = "RAEIOUZN";
        boolean matches14 = nfa5.recognizes(text14);
        System.out.println("Text 14 check: " + matches14 + " Expected: true");

        String text15 = "RAEIOUN";
        boolean matches15 = nfa5.recognizes(text15);
        System.out.println("Text 15 check: " + matches15 + " Expected: false");

        String text16 = "RUZN";
        boolean matches16 = nfa5.recognizes(text16);
        System.out.println("Text 16 check: " + matches16 + " Expected: true");

        String text17 = "RUN";
        boolean matches17 = nfa5.recognizes(text17);
        System.out.println("Text 17 check: " + matches17 + " Expected: false");

        String text18 = "R0N";
        boolean matches18 = nfa5.recognizes(text18);
        System.out.println("Text 18 check: " + matches18 + " Expected: true");

        String text19 = "R1N";
        boolean matches19 = nfa5.recognizes(text19);
        System.out.println("Text 19 check: " + matches19 + " Expected: true");
    }

}
