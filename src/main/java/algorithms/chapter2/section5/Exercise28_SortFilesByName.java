package algorithms.chapter2.section5;

import java.io.File;

public class Exercise28_SortFilesByName {

    public static void main(String[] args) {
        sortFilesByName(".");
    }

    private static void sortFilesByName(String directoryName) {
        File directory = new File(directoryName);
        if (!directory.isDirectory()) return;

        File[] files = directory.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
    
}
