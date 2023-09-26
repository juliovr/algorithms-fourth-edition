package algorithms.chapter5.section4;

public class Exercise18_OneOrMore {

    public static void main(String[] args) {
        String pattern1 = "(RE)+NE";
        NFA nfa1 = new NFA(pattern1);
        String text1 = "RERERENE";
        boolean matches1 = nfa1.recognizes(text1);
        System.out.println("Text 1 check: " + matches1 + " Expected: true");

        String text2 = "RONE";
        boolean matches2 = nfa1.recognizes(text2);
        System.out.println("Text 2 check: " + matches2 + " Expected: false");

        String text3 = "RENE";
        boolean matches3 = nfa1.recognizes(text3);
        System.out.println("Text 3 check: " + matches3 + " Expected: true");

        String pattern2 = "ABCR+PQR";
        NFA nfa2 = new NFA(pattern2);
        String text4 = "ABCRPQR";
        boolean matches4 = nfa2.recognizes(text4);
        System.out.println("Text 4 check: " + matches4 + " Expected: true");

        String text5 = "ABCRRRRRRRRRRRRRRPQR";
        boolean matches5 = nfa2.recognizes(text5);
        System.out.println("Text 5 check: " + matches5 + " Expected: true");

        String text6 = "ABCPQR";
        boolean matches6 = nfa2.recognizes(text6);
        System.out.println("Text 6 check: " + matches6 + " Expected: false");
    }

}
