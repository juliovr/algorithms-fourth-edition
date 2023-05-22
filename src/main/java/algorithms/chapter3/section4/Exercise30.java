package algorithms.chapter3.section4;

public class Exercise30 {

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(4);
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("D", 4);
        st.put("E", 5);
        st.put("F", 6);
        st.put("G", 7);
        st.put("H", 8);
        st.put("I", 9);
        st.put("J", 10);

        System.out.println("X^2 = " + st.chiSquareStatistic());
    }

}
