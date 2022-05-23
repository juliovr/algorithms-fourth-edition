package cl.julio.algorithmsFourthEdition;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cl.julio.algorithmsFourthEdition.chapter2.section1.Insertion;

public class Common {

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    public static boolean equals(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println();
    }
    
    public static void showOneLine(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    public static boolean check(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        System.arraycopy(a, 0, b, 0, a.length);

        Insertion.sort(a);
        Arrays.sort(b);

        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static Comparable[] generateRandomData(int n) {
        int max = n;
        int min = 1;

        Comparable[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            int rand = random.nextInt(max - min + 1) + min;
            a[i] = rand;
        }

        return a;
    }

    public static int[] generateRandomIntData(int n) {
        int max = 50;
        int min = 1;

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            int rand = random.nextInt(max - min + 1) + min;
            a[i] = rand;
        }

        return a;
    }

    public static void generateDataFile(int n, String filename) {
        System.out.println("Generating " + n + " characters to " + filename);
        try (Writer writer = new PrintWriter("test_data/" + filename, "UTF-8")) {
            for (int i = 0; i < n; i++) {
                int rand = random.nextInt(n);

                writer.write(rand + "");
                writer.write("\n");
                
                if (i % 100 == 0) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println("Done");
    }

    public static Comparable[] loadDataFromFile(String filename) {
        try (InputStream is = new FileInputStream(filename); Scanner sc = new Scanner(is)) {

            List<Comparable> data = new ArrayList<>();
            while (sc.hasNext()) {
                data.add(sc.next());
            }

            Comparable[] a = new String[data.size()];
            return data.toArray(a);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        Comparable[] a = new Integer[] { 1, 5, 75, 68, 05, 42, 6, 9, 94, 10, 45, 13, 45, 64, 65 };
//        System.out.println(Common.check(a));
//        for (Comparable comparable : a) {
//            System.out.print(comparable + " ");
//        }
        
        generateDataFile(50_000, "50000_numbers");
    }

}
