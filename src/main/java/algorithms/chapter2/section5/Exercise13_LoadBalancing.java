package algorithms.chapter2.section5;

import java.util.ArrayList;
import java.util.List;

import algorithms.chapter2.section4.MinPQ;

public class Exercise13_LoadBalancing {

    public static void main (String[] args) {
        Processor[] processors = new Processor[] {
            new Processor(1),
            new Processor(2),
            new Processor(3),
            new Processor(4)
        };

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

        lpt(processors, jobs);
    }
    
    // Longest Processing Time
    private static void lpt(Processor[] processors, Job[] jobs) {
        MinPQ<Processor> processorsPq = new MinPQ<>(processors.length);
        
        for (int i = 0; i < processors.length; ++i) {
            processorsPq.insert(processors[i]);
        }

        for (int i = 0; i < jobs.length; ++i) {
            Processor p = processorsPq.delMin();
            p.addJob(jobs[i]);
            processorsPq.insert(p);
        }

        while (!processorsPq.isEmpty()) {
            System.out.println(processorsPq.delMin());
        }
    }

    private static class Processor implements Comparable<Processor> {

        private int number;
        private List<Job> jobs = new ArrayList<>();
        private int totalProcessingTime = 0;

        public Processor(int number) {
            this.number = number;
        }
        
        public void addJob(Job job) {
            this.jobs.add(job);
            this.totalProcessingTime += job.processingTime;
        }

        @Override
        public int compareTo(Processor o) {
            if (this.totalProcessingTime < o.totalProcessingTime) return -1;
            if (this.totalProcessingTime > o.totalProcessingTime) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "Processor " + this.number + ", processing time = " + this.totalProcessingTime + " from jobs " + this.jobs;
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
