package cl.julio.algorithmsFourthEdition.chapter2.section2;

import static cl.julio.algorithmsFourthEdition.Common.less;

import cl.julio.algorithmsFourthEdition.Common;

public class Exercise26_ArrayCreation {
    
    public static void main(String[] args) throws Exception {
        System.out.println("Start");

        runExperiments(10);

        System.out.println("Done");
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void runExperiments(int numberExperiments) {
        System.out.println("================================================");
        System.out.println("Merge sort");
        System.out.println("================================================");
        for (int j = 0; j < 5; j++) {
            long min = Long.MAX_VALUE;
            long max = 0;
            long sum = 0;
            
            MergeSort mergeSort = new MergeSort();
            
            for (int i = 0; i < numberExperiments; i++) {
                Comparable[] data = Common.loadDataFromFile("test_data/20000_numbers");
                int n = data.length;

                long start = System.currentTimeMillis();
                mergeSort.sort(data);
                long end = System.currentTimeMillis();

                long timeElapsed = end - start;
                System.out.println("Time elapsed array " + n + " elements: " + timeElapsed + " ms");

                if (!Common.isSorted(data)) {
                    System.err.println("Data is not sorted!!");
                }
                
                if (timeElapsed < min)
                    min = timeElapsed;
                
                if (timeElapsed > max)
                    max = timeElapsed;
                
                sum += timeElapsed;
            }
            
            long avg = sum / numberExperiments;

            System.out.println("=========== Report ===========");
            System.out.printf("Merge sort\n");
            System.out.printf("Total time: %d\n", sum);
            System.out.printf("Min time:   %d\n", min);
            System.out.printf("Max time:   %d\n", max);
            System.out.printf("Avg time:   %d\n", avg);
            System.out.println("==============================");
        }
        
        System.out.println("================================================");
        System.out.println("Merge sort improved");
        System.out.println("================================================");
        for (int j = 0; j < 5; j++) {
            long min = Long.MAX_VALUE;
            long max = 0;
            long sum = 0;
            
            MergeSortImproved mergeSort = new MergeSortImproved();
            
            for (int i = 0; i < numberExperiments; i++) {
                Comparable[] data = Common.loadDataFromFile("test_data/20000_numbers");
                int n = data.length;

                long start = System.currentTimeMillis();
                mergeSort.sort(data);
                long end = System.currentTimeMillis();

                long timeElapsed = end - start;
                System.out.println("Time elapsed array " + n + " elements: " + timeElapsed + " ms");

                if (!Common.isSorted(data)) {
                    System.err.println("Data is not sorted!!");
                }
                
                if (timeElapsed < min)
                    min = timeElapsed;
                
                if (timeElapsed > max)
                    max = timeElapsed;
                
                sum += timeElapsed;
            }
            
            long avg = sum / numberExperiments;

            System.out.println("=========== Report ===========");
            System.out.printf("Merge sort improved\n");
            System.out.printf("Total time: %d\n", sum);
            System.out.printf("Min time:   %d\n", min);
            System.out.printf("Max time:   %d\n", max);
            System.out.printf("Avg time:   %d\n", avg);
            System.out.println("==============================");
        }
        
    }
    
    private static class MergeSort {
        
        public void sort(Comparable[] array) {
            Comparable[] aux = new Comparable[array.length];

            sort(array, aux, 0, array.length - 1);
        }

        private void sort(Comparable[] array, Comparable[] aux, int low, int high) {
            if (high <= low) {
                return;
            }

            int middle = low + (high - low) / 2;

            sort(array, aux, low, middle);
            sort(array, aux, middle + 1, high);

            merge(array, aux, low, middle, high);
        }
        
        private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            int i = lo;
            int j = mid + 1;
            
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }
            
            for (int k = lo; k <= hi; k++) {
                if      (i > mid)                a[k] = aux[j++];
                else if (j > hi )                a[k] = aux[i++];
                else if (less(aux[j], aux[i]))   a[k] = aux[j++];
                else                             a[k] = aux[i++];
            }
        }
        
    }
    
    private static class MergeSortImproved {
        
        @SuppressWarnings({ "rawtypes" })
        public void sort(Comparable[] array) {
            Comparable[] aux = new Comparable[array.length];
            
            // Improvement 3: Eliminate the copy to the auxiliary array. Just make the copy once.
            for (int i = 0; i < array.length; i++) {
                aux[i] = array[i];
            }

            sort(array, aux, 0, array.length - 1);
        }

        @SuppressWarnings({ "rawtypes" })
        private void sort(Comparable[] array, Comparable[] aux, int low, int high) {
            if (high <= low) {
                return;
            }
            
            int middle = low + (high - low) / 2;

            sort(aux, array, low, middle);
            sort(aux, array, middle + 1, high);

            merge(array, aux, low, middle, high);
        }
        
        @SuppressWarnings({ "rawtypes" })
        private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            int i = lo;
            int j = mid + 1;
            
            for (int k = lo; k <= hi; k++) {
                if      (i > mid)                a[k] = aux[j++];
                else if (j > hi )                a[k] = aux[i++];
                else if (less(aux[j], aux[i]))   a[k] = aux[j++];
                else                             a[k] = aux[i++];
            }
        }
        
    }
    
}
