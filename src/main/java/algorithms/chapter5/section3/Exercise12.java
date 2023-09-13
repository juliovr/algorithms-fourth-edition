package algorithms.chapter5.section3;

public class Exercise12 {

    public static void main(String[] args) {
        String text = "abacadabrabracabracadabrabrabracad";

        String pattern1 = "abracadabra";
        RabinKarpLasVegas rabinKarpLasVegas1 = new RabinKarpLasVegas(pattern1);
        int index1 = rabinKarpLasVegas1.search(text);
        System.out.println("Index 1: " + index1 + " Expected: 14");

        String pattern2 = "rab";
        RabinKarpLasVegas rabinKarpLasVegas2 = new RabinKarpLasVegas(pattern2);
        int index2 = rabinKarpLasVegas2.search(text);
        System.out.println("Index 2: " + index2 + " Expected: 8");

        String pattern3 = "bcara";
        RabinKarpLasVegas rabinKarpLasVegas3 = new RabinKarpLasVegas(pattern3);
        int index3 = rabinKarpLasVegas3.search(text);
        System.out.println("Index 3: " + index3 + " Expected: 34");

        String pattern4 = "rabrabracad";
        RabinKarpLasVegas rabinKarpLasVegas4 = new RabinKarpLasVegas(pattern4);
        int index4 = rabinKarpLasVegas4.search(text);
        System.out.println("Index 4: " + index4 + " Expected: 23");

        String pattern5 = "abacad";
        RabinKarpLasVegas rabinKarpLasVegas5 = new RabinKarpLasVegas(pattern5);
        int index5 = rabinKarpLasVegas5.search(text);
        System.out.println("Index 5: " + index5 + " Expected: 0");
    }
    
}
