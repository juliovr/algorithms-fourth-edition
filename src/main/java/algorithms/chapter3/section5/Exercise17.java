package algorithms.chapter3.section5;

public class Exercise17 {

    public static void main(String[] args) {
        Integer[] universe = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        MathSET<Integer> mathSet = new MathSET<>(universe);
        mathSet.add(1);
        mathSet.add(2);
        mathSet.add(3);
        mathSet.add(1);
        mathSet.add(30);

        System.out.println("=================================");
        System.out.println("add");
        System.out.println("Expected = 1, 2, 3");
        System.out.print("Actual   = ");
        for (int i: mathSet.keys()) {
            System.out.print(i + ", ");
        }
        System.out.println("\b\b");
        System.out.println("=================================");


        System.out.println("=================================");
        System.out.println("delete");
        mathSet.delete(50);
        mathSet.delete(1);
        System.out.println("Expected = 2, 3");
        System.out.print("Actual   = ");
        for (int i: mathSet.keys()) {
            System.out.print(i + ", ");
        }
        System.out.println("\b\b");
        System.out.println("=================================");


        System.out.println("=================================");
        System.out.println("complement");
        MathSET<Integer> complement = mathSet.complement();
        System.out.println("Expected = 1, 4, 5, 6, 7, 8, 9, 10");
        System.out.print("Actual   = ");
        for (int i: complement.keys()) {
            System.out.print(i + ", ");
        }
        System.out.println("\b\b");
        System.out.println("=================================");


        System.out.println("=================================");
        System.out.println("union");
        MathSET<Integer> unionSource = new MathSET<>(universe);
        unionSource.add(5);
        unionSource.add(6);
        unionSource.add(7);

        mathSet.union(unionSource);
        System.out.println("Expected = 2, 3, 5, 6, 7");
        System.out.print("Actual   = ");
        for (int i: mathSet.keys()) {
            System.out.print(i + ", ");
        }
        System.out.println("\b\b");
        System.out.println("=================================");


        System.out.println("=================================");
        System.out.println("intersection");
        MathSET<Integer> intersectionSource = new MathSET<>(universe);
        intersectionSource.add(5);
        intersectionSource.add(6);
        intersectionSource.add(7);

        mathSet.intersection(intersectionSource);
        System.out.println("Expected = 5, 6, 7");
        System.out.print("Actual   = ");
        for (int i: mathSet.keys()) {
            System.out.print(i + ", ");
        }
        System.out.println("\b\b");
        System.out.println("=================================");
    }

}
