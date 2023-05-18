package algorithms.chapter3.section4;

public class Exercise9 {

    //
    // Implemented in SeparateChainingHashST.delete(Key)
    //

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(2);
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("D", 4);
        st.put("E", 5);

        st.delete("A");

        System.out.println(st);
    }

}
