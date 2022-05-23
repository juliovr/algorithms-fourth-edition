package cl.julio.algorithmsFourthEdition.chapter2.section1;

import cl.julio.algorithmsFourthEdition.Common;
import edu.princeton.cs.algs4.StdDraw;

public class Shell {

	public static void sort(Comparable[] a) {
		int n = a.length;

		int h = 1;
		while (h < n/3) {
			h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		}

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && Common.less(a[j], a[j - h]); j -= h) {
					Common.exchange(a, j, j - h);
				}
			}

			h /= 3;
		}
	}
	
	public static void sortVisualizing(Comparable[] a) {
		int n = a.length;
		
		StdDraw.setScale(1, 100);
		StdDraw.setCanvasSize(n * 30, 200);
		
		draw(a, 0, 0);
		
		int h = 1;
		while (h < n/3) {
			h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		}

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && Common.less(a[j], a[j - h]); j -= h) {
					Common.exchange(a, j, j - h);
					
					draw(a, j - h, j);
				}
			}

			h /= 3;
		}
	}
	
	private static void draw(Comparable[] a, int ith, int jth) {
		StdDraw.clear();
		
		int n = a.length;
		
		double halfWidth = 0.007;
		double halfHeight = 0.1;
		for (int i = 0; i < n; i++) {
			double value = Double.parseDouble(String.valueOf(a[i])) * 0.005;
			double y = 0.2;
			double x = y + (i * 3 * halfWidth);
			
			if (i == ith) {
				StdDraw.setPenColor(StdDraw.BLACK);
			} else if (i == jth) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
			} else {
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			}
			
			StdDraw.filledRectangle(x, y + value, halfWidth, halfHeight + value);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Comparable[] a = Common.generateRandomData(20);
		
		Shell.sortVisualizing(a);
	}

}
