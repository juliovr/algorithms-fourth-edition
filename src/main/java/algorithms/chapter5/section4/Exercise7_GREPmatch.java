package algorithms.chapter5.section4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Exercise7_GREPmatch {

    public static void main(String[] args) {
        In in = new In("test_data/tinyL.txt");

//        String regexp = "(" + "(A|B)(C|D)" + ")";
//        String regexp = "(" + "A(B|C)*D" + ")";
        String regexp = "(" + "(A*B|AC)D" + ")";
        NFA nfa = new NFA(regexp);
        while (in.hasNextLine()) {
            String txt = in.readLine();
            if (nfa.recognizes(txt)) {
                StdOut.println(txt);
            }
        }
    }

}
