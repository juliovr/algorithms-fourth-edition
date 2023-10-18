package algorithms;

import java.util.HashMap;
import java.util.Map;

public class MathUtil {

    private static final Map<Long, Long> FACTORIALS = new HashMap<>();

    public static long factorial(long n) {
        if (FACTORIALS.containsKey(n)) {
            return FACTORIALS.get(n);
        } else if (n > 1) {
            long result = n * factorial(n - 1);
            FACTORIALS.put(n, result);
            return result;
        } else {
            return 1;
        }
    }

}
