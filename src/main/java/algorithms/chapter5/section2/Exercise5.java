package algorithms.chapter5.section2;

public class Exercise5 {

    public static void main(String[] args) {
        String[] keys = new String[] {
                "now",
                "is",
                "the",
                "time",
                "for",
                "all",
                "good",
                "people",
                "to",
                "come",
                "to",
                "the",
                "aid",
                "of"
        };

        System.out.println("=====================================");
        System.out.println("Test TrieST");
        System.out.println("=====================================");
        TrieST<Integer> trieST = new TrieST<>();
        TrieSTNonRecursive<Integer> trieSTNonRecursive = new TrieSTNonRecursive<>();
        for (int i = 0; i < keys.length; ++i) {
            trieST.put(keys[i], i);
            trieSTNonRecursive.put(keys[i], i);
        }

        System.out.println("now    -> " + trieSTNonRecursive.get("now") + ", expected = " + trieST.get("now"));
        System.out.println("is     -> " + trieSTNonRecursive.get("is") + ", expected = " + trieST.get("is"));
        System.out.println("time   -> " + trieSTNonRecursive.get("time") + ", expected = " + trieST.get("time"));
        System.out.println("for    -> " + trieSTNonRecursive.get("for") + ", expected = " + trieST.get("for"));
        System.out.println("all    -> " + trieSTNonRecursive.get("all") + ", expected = " + trieST.get("all"));
        System.out.println("good   -> " + trieSTNonRecursive.get("good") + ", expected = " + trieST.get("good"));
        System.out.println("people -> " + trieSTNonRecursive.get("people") + ", expected = " + trieST.get("people"));
        System.out.println("come   -> " + trieSTNonRecursive.get("come") + ", expected = " + trieST.get("come"));
        System.out.println("to     -> " + trieSTNonRecursive.get("to") + ", expected = " + trieST.get("to"));
        System.out.println("the    -> " + trieSTNonRecursive.get("the") + ", expected = " + trieST.get("the"));
        System.out.println("aid    -> " + trieSTNonRecursive.get("aid") + ", expected = " + trieST.get("aid"));
        System.out.println("of     -> " + trieSTNonRecursive.get("of") + ", expected = " + trieST.get("of"));
        System.out.println("asd    -> " + trieSTNonRecursive.get("asd") + ", expected = " + trieST.get("asd"));


        System.out.println();

        System.out.println("=====================================");
        System.out.println("Test TST");
        System.out.println("=====================================");
        TST<Integer> tst = new TST<>();
        TSTNonRecursive<Integer> tstNonRecursive = new TSTNonRecursive<>();
        for (int i = 0; i < keys.length; ++i) {
            tst.put(keys[i], i);
            tstNonRecursive.put(keys[i], i);
        }

        System.out.println("now    -> " + tstNonRecursive.get("now") + ", expected = "+ tst.get("now"));
        System.out.println("is     -> " + tstNonRecursive.get("is") + ", expected = "+ tst.get("is"));
        System.out.println("time   -> " + tstNonRecursive.get("time") + ", expected = "+ tst.get("time"));
        System.out.println("for    -> " + tstNonRecursive.get("for") + ", expected = "+ tst.get("for"));
        System.out.println("all    -> " + tstNonRecursive.get("all") + ", expected = "+ tst.get("all"));
        System.out.println("good   -> " + tstNonRecursive.get("good") + ", expected = "+ tst.get("good"));
        System.out.println("people -> " + tstNonRecursive.get("people") + ", expected = "+ tst.get("people"));
        System.out.println("come   -> " + tstNonRecursive.get("come") + ", expected = "+ tst.get("come"));
        System.out.println("to     -> " + tstNonRecursive.get("to") + ", expected = "+ tst.get("to"));
        System.out.println("the    -> " + tstNonRecursive.get("the") + ", expected = "+ tst.get("the"));
        System.out.println("aid    -> " + tstNonRecursive.get("aid") + ", expected = "+ tst.get("aid"));
        System.out.println("of     -> " + tstNonRecursive.get("of") + ", expected = "+ tst.get("of"));
        System.out.println("asd    -> " + tstNonRecursive.get("asd") + ", expected = " + tst.get("asd"));
    }

}
