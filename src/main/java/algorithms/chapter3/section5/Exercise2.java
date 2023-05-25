package algorithms.chapter3.section5;

public class Exercise2 {

    public static void main(String[] args) {
        SequentialSearchSET<String> set = new SequentialSearchSET<>();
        set.put("C");
        set.put("A");
        set.put("B");
        set.put("Z");
        set.put("A");
        set.put("A");
        set.put("H");
        set.put("Z");

        for (String s: set.keys()) {
            System.out.println(s);
        }
    }

}
