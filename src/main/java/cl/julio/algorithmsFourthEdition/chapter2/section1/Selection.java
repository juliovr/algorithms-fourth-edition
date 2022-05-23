package cl.julio.algorithmsFourthEdition.chapter2.section1;

import cl.julio.algorithmsFourthEdition.Common;
import edu.princeton.cs.algs4.StdDraw;

public class Selection {

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (Common.less(a[j], a[min])) {
					min = j;
				}
			}
			
			Common.exchange(a, i, min);
		}
	}
	
	public static void sortVisualizing(Comparable[] a) {
		int n = a.length;
		
		StdDraw.setScale(1, 100);
		StdDraw.setCanvasSize(n * 30, 200);
		
		draw(a, 0, 0);
		
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (Common.less(a[j], a[min])) {
					min = j;
				}
			}
			
			Common.exchange(a, i, min);
			
			draw(a, i, min);
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
			
			if (i == jth) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
			} else if (i == ith) {
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			} else if (i > ith) {
				StdDraw.setPenColor(StdDraw.BLACK);
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
		
		Selection.sortVisualizing(a);
	}
	
}
