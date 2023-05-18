package algorithms.chapter3.section4;

public class Exercise19 {

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st1 = new SeparateChainingHashST<>(5);
        st1.put("A", 1);
        st1.put("B", 2);
        st1.put("C", 3);
        st1.put("D", 4);
        st1.put("E", 5);
        st1.put("F", 6);
        st1.put("G", 7);
        st1.put("H", 8);

        System.out.println("SeparateChainingHashST");
        for (String key: st1.keys()) {
            System.out.println(key);
        }

        LinearProbingHashST<String, Integer> st2 = new LinearProbingHashST<>(5);
        st2.put("A", 1);
        st2.put("B", 2);
        st2.put("C", 3);
        st2.put("D", 4);
        st2.put("E", 5);
        st2.put("F", 6);
        st2.put("G", 7);
        st2.put("H", 8);

        System.out.println("LinearProbingHashST");
        for (String key: st2.keys()) {
            System.out.println(key);
        }
    }

}
