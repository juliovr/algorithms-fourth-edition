package algorithms.chapter3.section4;

public class Exercise36 {

    public static void main(String[] args) {
        SeparateChainingHashST<Integer, Integer> st10_3 = createHashTable(1000);
        SeparateChainingHashST<Integer, Integer> st10_4 = createHashTable(10000);
        SeparateChainingHashST<Integer, Integer> st10_5 = createHashTable(100000);
        SeparateChainingHashST<Integer, Integer> st10_6 = createHashTable(1000000);

        System.out.print("10^3: ");
        printShortestAndLongestList(st10_3);

        System.out.print("10^4: ");
        printShortestAndLongestList(st10_4);

        System.out.print("10^5: ");
        printShortestAndLongestList(st10_5);

        System.out.print("10^6: ");
        printShortestAndLongestList(st10_6);
    }

    private static SeparateChainingHashST<Integer, Integer> createHashTable(int n) {
        SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<>(n/100);
        for (int i = 0; i < n; ++i) {
            int value = (int)(Integer.MAX_VALUE*Math.random());
            st.put(value, value);
        }

        return st;
    }

    private static void printShortestAndLongestList(SeparateChainingHashST<Integer, Integer> st) {
        int shortest = Integer.MAX_VALUE;
        int longest = 0;
        for (int i = 0; i < st.getM(); ++i) {
            int size = st.getList(i).size();

            if (size < shortest) {
                shortest = size;
            }

            if (size > longest) {
                longest = size;
            }
        }

        System.out.println("Shortest = " + shortest + ", Longest = " + longest);
    }

}
