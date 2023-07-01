package algorithms.chapter3.section4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise32_Permutations {

    private static final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final Map<Integer, List<String>> map = new HashMap<>();

    public static void main(String[] args) {
        char[] string = new char[3];
        int i = 0;
        permute(string, i);

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getValue());
            }
        }
    }

    private static void permute(char[] string, int i) {
        if (i >= string.length) {
            return;
        }

        for (int letterIndex = 0; letterIndex < LETTERS.length; ++letterIndex) {
            char letter = LETTERS[letterIndex];
            string[i] = letter;
            //printCharArray(string);
            calculateHash(String.valueOf(string));
            permute(string, i + 1);
            string[i] = 0;
        }
    }

    private static void calculateHash(String string) {
        int hash = hashCode(string);
        List<String> strings = map.get(hash);
        if (strings == null) {
            strings = new ArrayList<>();
            map.put(hash, strings);
        }

        strings.add(string);
    }

    private static void printCharArray(char[] string) {
        for (int i = 0; i < string.length; ++i) {
            if (string[i] != 0) System.out.print(string[i]);
        }
        System.out.println();
    }

    private static int hashCode(String string) {
        int hash = 0;
        for (int i = 0; i < string.length(); ++i) {
            hash = (hash * 31) + string.charAt(i);
        }

        return hash;
    }

}
