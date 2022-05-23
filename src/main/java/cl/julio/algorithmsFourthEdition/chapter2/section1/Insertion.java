package cl.julio.algorithmsFourthEdition.chapter2.section1;

import cl.julio.algorithmsFourthEdition.Common;
import edu.princeton.cs.algs4.StdDraw;

public class Insertion {

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && Common.less(a[j], a[j - 1]); j--) {
				Common.exchange(a, j, j - 1);
			}
		}
	}
	
	public static void sortVisualizing(Comparable[] a) {
		int n = a.length;
		
		StdDraw.setScale(1, 100);
		StdDraw.setCanvasSize(n * 30, 200);
		
		draw(a, 0);
		
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && Common.less(a[j], a[j - 1]); j--) {
				Common.exchange(a, j, j - 1);
				
				draw(a, j);
			}
		}
	}
	
	private static void draw(Comparable[] a, int jth) {
		StdDraw.clear();
		
		int n = a.length;
		
		double halfWidth = 0.007;
		double halfHeight = 0.1;
		for (int i = 0; i < n; i++) {
			double value = Double.parseDouble(String.valueOf(a[i])) * 0.005;
			double y = 0.2;
			double x = y + (i * 3 * halfWidth);
			
			if (i == jth) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
			} else if (i > jth) {
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			} else {
				StdDraw.setPenColor(StdDraw.BLACK);
			}
			
			StdDraw.filledRectangle(x, y + value, halfWidth, halfHeight + value);
		}
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sortWithSentinel(Comparable[] a) {
		int n = a.length;
		
		int imin = 0;
		Comparable min = a[imin];
		for (int i = 1; i < n; i++) {
			if (Common.less(a[i], min)) {
				min = a[i];
				imin = i;
			}
		}
		Common.exchange(a, 0, imin);
		
		for (int i = 1; i < n; i++) {
			for (int j = i; Common.less(a[j], a[j - 1]); j--) {
				Common.exchange(a, j, j - 1);
			}
		}
	}
	
	public static void sortIntPrimitive(int[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				int tmp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = tmp;
			}
		}
	}
	
	public static void sortIntegerAutoboxing(Integer[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				Integer tmp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = tmp;
			}
		}
	}
	
	public static void sortCompare() {
		int n = 50_000;
		int[] a = Common.generateRandomIntData(n);
		Integer[] b = new Integer[n];
		
		for (int i = 0; i < n; i++) {
			b[i] = new Integer(a[i]);
		}
		
		long start, end;
		start = System.currentTimeMillis();
		Insertion.sortIntPrimitive(a);
		end = System.currentTimeMillis();
		double time1 = (end - start) / 1000.0;
		
		start = System.currentTimeMillis();
		Insertion.sortIntegerAutoboxing(b);
		end = System.currentTimeMillis();
		double time2 = (end - start) / 1000.0;
		
		double ratio = time2 / time1;

		String alg1 = "Insertion sort primitive";
		String alg2 = "Insertion Integer autoboxing";
		
		System.out.printf("Results for %d elements:\n", a.length);
		System.out.printf("%s sort time = %.1f seconds.\n", alg1 , time1);
		System.out.printf("%s sort time = %.1f seconds.\n", alg2 , time2);
		System.out.printf("%s is %.1f faster than %s.\n", alg1, ratio, alg2);
	}
	
	public static void main(String[] args) {
//		Comparable[] a = Common.generateRandomData(20);
//		
//		Insertion.sortVisualizing(a);
//		Insertion.sortWithSentinel(a);
//		Common.show(a);
		
		sortCompare();
	}
	
}
