package cl.julio.algorithmsFourthEdition.chapter2.section5;

import cl.julio.algorithmsFourthEdition.chapter2.section4.MinPQ;

public class Exercise12_Scheduling {

    public static void main(String[] args) {
        String[] lines = new String[] {
            "Job1", "123",
            "Job2", "111",
            "Job3", "1",
            "Job4", "4",
            "Job5", "53",
        };

        Job[] jobs = new Job[lines.length / 2];
        int jobsIndex = 0;
        for (int i = 0; i < lines.length; i += 2) {
            Job job = new Job();
            job.name = lines[i];
            job.processingTime = Integer.parseInt(lines[i+1]);

            jobs[jobsIndex++] = job;
        }

        spt(jobs);
    }

    // Shortest Processing Time
    private static void spt(Job[] jobs) {
        MinPQ<Job> minPq = new MinPQ<>(jobs.length);
        
        for (int i = 0; i < jobs.length; ++i) {
            minPq.insert(jobs[i]);
        }

        while (!minPq.isEmpty()) {
            System.out.println(minPq.delMin());
        }
    }
    
    private static class Job implements Comparable<Job> {

        public String name;
        public int processingTime;

        @Override
        public int compareTo(Job o) {
            if (this.processingTime < o.processingTime) return -1;
            if (this.processingTime > o.processingTime) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "Job: " + this.name + ", processing time: " + this.processingTime;
        }

    }
    
}
