package algorithms.chapter5.section1;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    public static final String BINARY = "01";
    public static final String DNA = "ACGT";
    public static final String OCTAL = "01234567";
    public static final String DECIMAL = "0123456789";
    public static final String HEXADECIMAL = "0123456789ABCDEF";
    public static final String PROTEIN = "ACDEFGHIKLMNPQRSTVWY";
    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static final String ASCII = generateAsciiCharacters(128);
    public static final String EXTENDED_ASCII = generateAsciiCharacters(256);

    private static String generateAsciiCharacters(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append((char)i);
        }

        return sb.toString();
    }

    private final char[] characters;
    private final Map<Character, Integer> indices;

    public Alphabet(String s) {
        characters = s.toCharArray();
        indices = new HashMap<>(characters.length);
        for (int i = 0; i < characters.length; ++i) {
            char c = characters[i];
            indices.put(c, i);
        }
    }

    public char toChar(int index) {
        return characters[index];
    }

    public int toIndex(char c) {
        Integer i = indices.get(c);
        if (i == null) {
            return -1;
        } else {
            return i;
        }
    }

    public boolean contains(char c) {
        return toIndex(c) != -1;
    }

    public int radix() {
        return characters.length;
    }

    public int lgR() {
        return (int)(Math.log(characters.length) + 1);
    }

    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            indices[i] = toIndex(c);
        }

        return indices;
    }

    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.length; ++i) {
            int index = indices[i];
            if (index != -1) {
                sb.append((char) toChar(index));
            }
        }

        return sb.toString();
    }

}
