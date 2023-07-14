package algorithms.chapter4.section2;

import java.util.HashMap;
import java.util.Map;

public class Exercise37_ArithmeticExpressions {

    public static void main(String[] args) {
        /**
         * Digraph 1:
         * 2 + (3 * 4) + (3 * 4) / 5
         *
         *      +
         *     / \
         *    v  v
         *   +   /(DIV)
         *   /\  / \
         *  v v  v v
         *  2  *   5
         *     /\
         *    v v
         *    3 4
         */

        // Right children should be added before the left children to maintain the expression order
        Digraph digraph1 = new Digraph(8);
        digraph1.addEdge(0, 2);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 4);
        digraph1.addEdge(1, 3);
        digraph1.addEdge(4, 7);
        digraph1.addEdge(4, 6);
        digraph1.addEdge(2, 5);
        digraph1.addEdge(2, 4);

        String[] values1 = {"+", "+", "/", "2", "*", "5", "3", "4"};
        System.out.println("2 + (3 * 4) + (3 * 4) / 5 = " + new ArithmeticExpression(digraph1, values1).evaluateDAG());
        System.out.println("Expected: 16.4");

        /**
         * Digraph 2:
         * (3 * 4) + (3 * 4) + (3 * 4) + (3 * 4) / 6
         *
         *         /(DIV)
         *       /   \
         *      v     v
         *      +     6
         *    /\ / \
         *   v v v v
         *     *
         *     /\
         *    v v
         *    3 4
         */

        Digraph digraph2 = new Digraph(6);
        digraph2.addEdge(0, 2);
        digraph2.addEdge(0, 1);

        digraph2.addEdge(1, 3);
        digraph2.addEdge(1, 3);
        digraph2.addEdge(1, 3);
        digraph2.addEdge(1, 3);
        digraph2.addEdge(3, 5);
        digraph2.addEdge(3, 4);

        String[] values2 = {"/", "+", "6", "*", "3", "4"};
        System.out.println("(3 * 4) + (3 * 4) + (3 * 4) + (3 * 4) / 6 = " + new ArithmeticExpression(digraph2, values2).evaluateDAG());
        System.out.println("Expected: 8.0");
    }

    private static class ArithmeticExpression {

        private Digraph digraph;
        private String[] values;
        private Map<Integer, Double> cache = new HashMap<>(); // Memoization for already calculated values

        public ArithmeticExpression(Digraph digraph, String[] values) {
            this.digraph = digraph;
            this.values = values;
        }
        public double evaluateDAG() {
            DirectedCycle cycle = new DirectedCycle(digraph);
            if (cycle.hasCycle()) {
                System.out.println("It is not a DAG");
                return 0.0;
            }

            return dfs(0);
        }

        private double dfs(int v) {
            if (cache.containsKey(v)) {
                return cache.get(v);
            }

            String value = values[v];

            if (!"+".equals(value) &&
                    !"-".equals(value) &&
                    !"*".equals(value) &&
                    !"/".equals(value)) {
                return Double.parseDouble(value);
            }

            double result = Double.NEGATIVE_INFINITY;

            for (int w : digraph.adj(v)) {
                switch (value) {
                    case "+":
                        if (result == Double.NEGATIVE_INFINITY) {
                            result = 0;
                        }

                        result += dfs(w);
                        break;
                    case "-":
                        if (result == Double.NEGATIVE_INFINITY) {
                            result = dfs(w);
                        } else {
                            result -= dfs(w);
                        }

                        break;
                    case "*":
                        if (result == Double.NEGATIVE_INFINITY) {
                            result = 1;
                        }

                        result *= dfs(w);
                        break;
                    case "/":
                        if (result == Double.NEGATIVE_INFINITY) {
                            result = dfs(w);
                        } else {
                            result /= dfs(w);
                        }

                        break;
                }
            }
            cache.put(v, result);

            return result;
        }

    }

}
