package algorithms.chapter5.section4;

import algorithms.chapter4.section2.Digraph;
import algorithms.chapter4.section2.DirectedDFS;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFA {

    private char[] re;         // match transitions
    private Digraph graph;      // epsilon transitions
    private int m;              // number of states
    private Map<Integer, Set<Character>> sets;
    private Map<Integer, Integer> setMapping;
    private Map<Integer, Set<Character>> complementSets;
    private Map<Integer, Integer> complementSetMapping;

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        m = re.length;
        graph = new Digraph(m+1);
        sets = new HashMap<>();
        setMapping = new HashMap<>();
        complementSets = new HashMap<>();
        complementSetMapping = new HashMap<>();

        for (int i = 0; i < m; ++i) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    Queue<Integer> operators = new Queue<>(); // Handle multiway or
                    operators.enqueue(or);
                    while (true) {
                        int operator = ops.pop();
                        if (re[operator] == '|') {
                            operators.enqueue(operator);
                        } else {
                            lp = operator;
                            break;
                        }
                    }

                    for (Integer op : operators) {
                        graph.addEdge(lp, op+1);
                        graph.addEdge(op, i);
                    }
                } else {
                    lp = or;
                }
            } else if (re[i] == '[') {
                Set<Character> charsSet = new HashSet<>();
                int openBracket = i;

                ++i;

                boolean complementChars = false;
                if (re[i] == '^') {
                    complementChars = true;
                    ++i;
                }

                while (re[i] != ']' && i < m) {
                    if (re[i] == '-') {
                        char start = re[i-1];
                        char end = re[i+1];
                        for (char currentChar = start; currentChar <= end; ++currentChar) {
                            charsSet.add(currentChar);
                        }
                    } else {
                        charsSet.add(re[i]);
                    }

                    ++i;
                }

                int closeBracket = i;
                if (complementChars) {
                    complementSets.put(openBracket, charsSet);
                    complementSetMapping.put(openBracket, closeBracket);
                } else {
                    sets.put(openBracket, charsSet);
                    setMapping.put(openBracket, closeBracket);
                }
            }

            if (i < m-1) {
                if (re[i+1] == '*') {
                    graph.addEdge(lp, i+1);
                    graph.addEdge(i+1, lp);
                } else if (re[i+1] == '+') {
                    graph.addEdge(i+1, lp);
                }
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')' || re[i] == '+' || re[i] == '[' || re[i] == ']') {
                graph.addEdge(i, i+1);
            }
        }
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(graph, 0);
        for (int v = 0; v < graph.V(); ++v) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }

        for (int i = 0; i < txt.length(); ++i) {
            Bag<Integer> states = new Bag<>();
            for (int v : pc) {
                if (v < m) {
                    Set<Character> charsSet = sets.get(v);
                    Set<Character> complementCharsSet = complementSets.get(v);

                    if (re[v] == txt.charAt(i) || re[v] == '.') {
                        states.add(v+1);
                    } else if (charsSet != null && charsSet.contains(txt.charAt(i))) {
                        states.add(setMapping.get(v)+1);
                    } else if (complementCharsSet != null) {
                        if (!complementCharsSet.contains(txt.charAt(i))) {
                            states.add(complementSetMapping.get(v) + 1);
                        }
                    }
                }
            }

            pc = new Bag<>();
            dfs = new DirectedDFS(graph, states);
            for (int v = 0; v < graph.V(); ++v) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }

        for (int v : pc) {
            if (v == m) {
                return true;
            }
        }

        return false;
    }

}
