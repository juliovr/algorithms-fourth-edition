package algorithms.chapter5.section3;

public class Exercise27_TandemRepeatSearch {

    public static void main(String[] args) {
        String baseString1 = "abcab";
        String text1 = "abcabcababcababcababcab";

        KMPTandemRepeat KMPTandemRepeat1 = new KMPTandemRepeat(baseString1);
        int tandemRepeat1 = KMPTandemRepeat1.findTandemRepeat(text1);
        System.out.println("Tandem repeat 1: " + tandemRepeat1 + " Expected: 3");


        String baseString2 = "rene";
        String text2 = "renereneabrenerenereneab";

        KMPTandemRepeat KMPTandemRepeat2 = new KMPTandemRepeat(baseString2);
        int tandemRepeat2 = KMPTandemRepeat2.findTandemRepeat(text2);
        System.out.println("Tandem repeat 2: " + tandemRepeat2 + " Expected: 0");


        String baseString3 = "abcab";
        String text3 = "abcababcababcababcabreabcab";

        KMPTandemRepeat KMPTandemRepeat3 = new KMPTandemRepeat(baseString3);
        int tandemRepeat3 = KMPTandemRepeat3.findTandemRepeat(text3);
        System.out.println("Tandem repeat 3: " + tandemRepeat3 + " Expected: 0");


        String baseString4 = "rene";
        String text4 = "abcabcabrenereneababcab";

        KMPTandemRepeat KMPTandemRepeat4 = new KMPTandemRepeat(baseString4);
        int tandemRepeat4 = KMPTandemRepeat4.findTandemRepeat(text4);
        System.out.println("Tandem repeat 4: " + tandemRepeat4 + " Expected: 8");


        // A tandem repeat requires at least 2 consecutive occurrences of baseString in the text,
        // so the two following tests should return -1.

        String baseString5 = "rene";
        String text5 = "abcabcababcababcababcab";

        KMPTandemRepeat KMPTandemRepeat5 = new KMPTandemRepeat(baseString5);
        int tandemRepeat5 = KMPTandemRepeat5.findTandemRepeat(text5);
        System.out.println("Tandem repeat 5: " + tandemRepeat5 + " Expected: -1");


        String baseString6 = "a";
        String text6 = "abcde";

        KMPTandemRepeat KMPTandemRepeat6 = new KMPTandemRepeat(baseString6);
        int tandemRepeat6 = KMPTandemRepeat6.findTandemRepeat(text6);
        System.out.println("Tandem repeat 6: " + tandemRepeat6 + " Expected: -1");

        String baseString7 = "a";
        String text7 = "abada";

        KMPTandemRepeat KMPTandemRepeat7 = new KMPTandemRepeat(baseString7);
        int tandemRepeat7 = KMPTandemRepeat7.findTandemRepeat(text7);
        System.out.println("Tandem repeat 7: " + tandemRepeat7 + " Expected: -1");
    }
    
}
