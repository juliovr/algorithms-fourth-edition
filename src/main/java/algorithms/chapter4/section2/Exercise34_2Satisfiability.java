package algorithms.chapter4.section2;

import algorithms.chapter3.section4.SeparateChainingHashST;

import java.util.*;

public class Exercise34_2Satisfiability {

    /**
     * The implication graph is in conjunctive normal form, which means is a collection of conjunctions (ANDs)
     * of clauses of disjunctions (ORs), so the Clause class does not have the operand type because of this constraint.
     */
    public static void main(String[] args) {
        List<Clause> clauses = Arrays.asList(
                new Clause("x0", "x2"),
                new Clause("x1", "x2")
        );

        System.out.println("Formula: ");
        printClauses(clauses);

        ImplicationDigraph implicationGraph = new ImplicationDigraph(clauses);

        KosarajuSharirSCC scc = new KosarajuSharirSCC(implicationGraph.digraph);

        boolean is2Satisfiable = true;

        for (String literal : implicationGraph.keys) {
            String v = literal;
            String vNeg = literal.startsWith(NEGATE) ? literal.substring(1) : NEGATE + literal;

            if (scc.stronglyConnected(implicationGraph.indexOf(v), implicationGraph.indexOf(vNeg))) {
                is2Satisfiable = false;
                break;
            }
        }

        if (is2Satisfiable) {
            System.out.println("The formula is 2-satisfiable");
        } else {
            System.out.println("The formula is not 2-satisfiable");
        }
    }

    private static final String NEGATE = "¬";
    private static final String AND = "^";
    private static final String OR = "v";

    private static class Variable {
        private boolean neg;
        private String name;
        private String fullname;

        public Variable(String name) {
            boolean neg = name.startsWith(NEGATE);
            this.neg = neg;
            if (neg) {
                this.name = name.substring(1);
            } else {
                this.name = name;
            }

            this.fullname = name;
        }

    }

    private static class Clause {
        private Variable var1;
        private Variable var2;

        public Clause(String var1, String var2) {
            this.var1 = new Variable(var1);
            this.var2 = new Variable(var2);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(var1.fullname);
            sb.append(" ");
            sb.append(OR);
            sb.append(" ");
            sb.append(var2.fullname);
            sb.append(")");

            return sb.toString();
        }
    }

    private static class ImplicationDigraph {
        private SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        private String[] keys;
        private Digraph digraph;

        public ImplicationDigraph(List<Clause> clauses) {
            Set<String> variables = new HashSet<>();
            for (Clause clause : clauses) {
                if (!variables.contains(clause.var1.name)) {
                    variables.add(clause.var1.name);
                }

                if (!variables.contains(clause.var2.name)) {
                    variables.add(clause.var2.name);
                }
            }

            // System.out.println("Variables: " + variables);

            digraph = new Digraph(variables.size()*2);

            for (Clause clause : clauses) {
                String v1 = clause.var1.name;
                String v1Neg = clause.var1.neg ? clause.var1.name.substring(1) : NEGATE + clause.var1.name;
                String v2 = clause.var2.name;
                String v2Neg = clause.var2.neg ? clause.var2.name.substring(1) : NEGATE + clause.var2.name;

                // System.out.printf("(%s -> %s), (%s -> %s)\n", v1Neg, v2, v2Neg, v1);

                if (!st.contains(v1Neg)) st.put(v1Neg, st.size());
                if (!st.contains(v2)) st.put(v2, st.size());
                if (!st.contains(v2Neg)) st.put(v2Neg, st.size());
                if (!st.contains(v1)) st.put(v1, st.size());

                digraph.addEdge(st.get(v1Neg), st.get(v2));
                digraph.addEdge(st.get(v2Neg), st.get(v1));
            }

            keys = new String[st.size()];
            for (String literal : st.keys()) {
                keys[st.get(literal)] = literal;
            }

        }

        public Digraph digraph() {
            return digraph;
        }

        public int indexOf(String name) {
            return st.get(name);
        }

        public String nameOf(int v) {
            return keys[v];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int v = 0; v < digraph.V(); ++v) {
                for (int w : digraph.adj(v)) {
                    sb.append(nameOf(v) + " -> " + nameOf(w));
                    sb.append('\n');
                }
            }

            return sb.toString();
        }

    }

    private static void printClauses(List<Clause> clauses) {
        boolean first = true;
        for (Clause clause : clauses) {
            if (first) {
                System.out.print(clause + " ");
                first = false;
            } else {
                System.out.print(AND + " " + clause + " ");
            }
        }

        System.out.println();
    }

}
