package algorithms.chapter3.section4;

import java.util.HashSet;
import java.util.Set;

public class Exercise4 {

    public static void main(String[] args) {
        char[] keys = "SEARCHXMPL".toCharArray();

        int m = keys.length;
        int a = 1;
        main_loop:
        for (; m < 100; ++m) {
            for (; a < m; a += 2) {
                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < keys.length; ++i) {
                    char c = keys[i];
                    int index = (a * alphabetValue(c)) % m;
                    set.add(index);

                    if (set.size() == keys.length) {
                        break main_loop;
                    }
                }
            }
        }

        System.out.println("m = " + m);
        System.out.println("a = " + a);
    }

    private static int alphabetValue(char c) {
        return ((int)c - 'A' + 1);
    }

}
