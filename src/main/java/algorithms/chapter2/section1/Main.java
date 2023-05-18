package algorithms.chapter2.section1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String WHITESPACE = " ";

    /**
     * Generate random test data.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//		Random random = new Random();
//		
//		int n = 50_000;
//		try (OutputStream os = new FileOutputStream("test_data/medium")) {
//			for (int i = 0; i < n; i++) {
//				int rand = random.nextInt(90 - 65 + 1) + 65;
//				
//				os.write((char) rand);
//				if (i != n - 1) {
//					os.write(WHITESPACE.getBytes());
//				}
//			}
//		}

        try (InputStream is = new FileInputStream("test_data/large"); 
                Scanner sc = new Scanner(is)) {

            int i = 0;
            while (sc.hasNext()) {
                System.out.println(i++ + ": " + sc.next());
            }
        }
    }

//	public static void main(String[] args) {
//		String line;
//		
//		try (Scanner sc = new Scanner(System.in)) {
//			if (!sc.hasNext()) {
//				System.out.println("Empty file");
//				return;
//			}
//
//			line = sc.nextLine();
//		}
//		
//		String[] tokens = line.split(WHITESPACE);
//		Shell.sort(tokens);
//		System.out.println("isSorted = " + Common.isSorted(tokens));
//		Common.show(tokens);
//	}

}
