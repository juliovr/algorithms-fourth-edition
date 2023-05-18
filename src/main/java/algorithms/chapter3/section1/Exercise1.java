package algorithms.chapter3.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise1 {

    public static void main(String[] args) {
        ST<String, Float> gradesValues = new ST<>();
        gradesValues.put("A+", 4.33f);
        gradesValues.put("A",  4.0f);
        gradesValues.put("A-", 3.67f);
        gradesValues.put("B+", 3.33f);
        gradesValues.put("B",  3.0f);
        gradesValues.put("B-", 2.67f);
        gradesValues.put("C+", 2.33f);
        gradesValues.put("C",  2.0f);
        gradesValues.put("C-", 1.67f);
        gradesValues.put("D",  1.0f);
        gradesValues.put("F",  0.0f);

        float total = 0.0f;
        String[] grades = StdIn.readLine().split(" ");
        for (String inputGrade : grades) {
            Float gradeValue = gradesValues.get(inputGrade);
            if (gradeValue == null) {
                StdOut.printf("Grade '%s' invalid\n", inputGrade);
            } else {
                total += gradeValue;
            }
        }

        StdOut.println("GPA = " + total / grades.length);
    }

}
