package algorithms.chapter6.networkflow;

import algorithms.chapter3.section4.SeparateChainingHashST;

import java.util.ArrayList;
import java.util.List;

public class Exercise45_JobPlacement {

    public static void main(String[] args) {
        List<String> students = new ArrayList<>();
        students.add("Alice");
        students.add("Bob");
        students.add("Carol");
        students.add("Dave");
        students.add("Eliza");
        students.add("Frank");

        List<String> companies = new ArrayList<>();
        companies.add("Adobe");
        companies.add("Amazon");
        companies.add("Facebook");
        companies.add("Google");
        companies.add("IBM");
        companies.add("Yahoo");

        SeparateChainingHashST<String, List<String>> preferences = new SeparateChainingHashST<>();

        List<String> alicePreferences = new ArrayList<>();
        alicePreferences.add("Adobe");
        alicePreferences.add("Amazon");
        alicePreferences.add("Facebook");
        preferences.put("Alice", alicePreferences);

        List<String> bobPreferences = new ArrayList<>();
        bobPreferences.add("Adobe");
        bobPreferences.add("Amazon");
        bobPreferences.add("Yahoo");
        preferences.put("Bob", bobPreferences);

        List<String> carolPreferences = new ArrayList<>();
        carolPreferences.add("Facebook");
        carolPreferences.add("Google");
        carolPreferences.add("IBM");
        preferences.put("Carol", carolPreferences);

        List<String> davePreferences = new ArrayList<>();
        davePreferences.add("Adobe");
        davePreferences.add("Amazon");
        preferences.put("Dave", davePreferences);

        List<String> elizaPreferences = new ArrayList<>();
        elizaPreferences.add("Google");
        elizaPreferences.add("IBM");
        elizaPreferences.add("Yahoo");
        preferences.put("Eliza", elizaPreferences);

        List<String> frankPreferences = new ArrayList<>();
        frankPreferences.add("IBM");
        frankPreferences.add("Yahoo");
        preferences.put("Frank", frankPreferences);

        saveJobPlacement(students, companies, preferences);
    }

    private static void saveJobPlacement(List<String> students, List<String> companies, SeparateChainingHashST<String, List<String>> preferences) {
        int totalSize = students.size() + companies.size();
        SeparateChainingHashST<Integer, String> intToValues = new SeparateChainingHashST<>(totalSize);
        SeparateChainingHashST<String, Integer> valuesToInt = new SeparateChainingHashST<>(totalSize);
        int i = 1;
        for (String student : students) {
            intToValues.put(i, student);
            valuesToInt.put(student, i);
            ++i;
        }

        for (String company : companies) {
            intToValues.put(i, company);
            valuesToInt.put(company, i);
            ++i;
        }

        int s = 0;
        int t = totalSize + 1;
        FlowNetwork flowNetwork = new FlowNetwork(totalSize + 2);

        for (String student : students) {
            int v = valuesToInt.get(student);
            flowNetwork.addEdge(new FlowEdge(s, v, 1));

            for (String preference : preferences.get(student)) {
                int w = valuesToInt.get(preference);
                flowNetwork.addEdge(new FlowEdge(v, w, 1));
            }
        }

        for (String company : companies) {
            int v = valuesToInt.get(company);
            flowNetwork.addEdge(new FlowEdge(v, t, 1));
        }

        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);

        System.out.println("**** Job placements ****");
        for (int v = 0; v < flowNetwork.V(); ++v) {
            for (FlowEdge e : flowNetwork.adj(v)) {
                if (e.from() == s || e.to() == t) {
                    continue;
                }

                if ((v == e.from()) && e.flow() > 0) {
                    String student = intToValues.get(e.from());
                    String company = intToValues.get(e.to());
                    System.out.printf("%10s - %10s\n", student, company);
                }
            }
        }

        System.out.println("\nNumber of jobs filled = " + (int)fordFulkerson.value() + ", expected = 6");
    }

}
