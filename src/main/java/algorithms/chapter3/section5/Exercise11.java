package algorithms.chapter3.section5;

public class Exercise11 {

    public static void main(String[] args) {
        MultiSET<String> set = new MultiSET<>();
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
