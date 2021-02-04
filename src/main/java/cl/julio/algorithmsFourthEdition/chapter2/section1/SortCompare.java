package cl.julio.algorithmsFourthEdition.chapter2.section1;

import java.io.InputStream;
import java.util.Scanner;

public class SortCompare {

	private static final String WHITESPACE = " ";
	
	public static void main(String[] args) {
		Comparable[] a = read(System.in);
		Comparable[] b = new Comparable[a.length];
		System.arraycopy(a, 0, b, 0, a.length);

		long start;
		long end;
		
		String alg1 = "Insertion";
		String alg2 = "InsertionWithSentinel";
		
		start = System.currentTimeMillis();
		sort(alg1, a);
		end = System.currentTimeMillis();
		double time1 = (end - start) / 1000.0;
		
		start = System.currentTimeMillis();
		sort(alg2, b);
		end = System.currentTimeMillis();
		double time2 = (end - start) / 1000.0;
		
		double ratio = time2 / time1;

		System.out.printf("Results for %d elements:\n", a.length);
		System.out.printf("%s sort time = %.1f seconds.\n", alg1, time1);
		System.out.printf("%s sort time = %.1f seconds.\n", alg2, time2);
		System.out.printf("%s is %.1f faster than %s.\n", alg1, ratio, alg2);
	}
	
	private static Comparable[] read(InputStream is) {
		String line;
		
		try (Scanner sc = new Scanner(is)) {
			if (!sc.hasNext()) {
				System.out.println("Empty file");
				return new String[] {};
			}

			line = sc.nextLine();
		}
		
		return line.split(WHITESPACE);
	}
	
	private static void sort(String algorithm, Comparable[] a) {
		if ("Insertion".equalsIgnoreCase(algorithm)) Insertion.sort(a);
		if ("InsertionWithSentinel".equalsIgnoreCase(algorithm)) Insertion.sortWithSentinel(a);
		if ("Selection".equalsIgnoreCase(algorithm)) Selection.sort(a);
		if ("Shell".equalsIgnoreCase(algorithm)) Shell.sort(a);
	}
	
}
