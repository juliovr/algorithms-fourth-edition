package cl.julio.algorithmsFourthEdition.chapter2.section5;

import java.util.Arrays;

public class Exercise10 {

    public static void main (String[] args) {
        Comparable[] versions = new Comparable[] {
            new Version("115.10.1"),
            new Version("115.1.1")
        };
        Arrays.sort(versions);
        for (int i = 0; i < versions.length; ++i) {
            System.out.println(versions[i]);
        }
    }

    private static class Version implements Comparable<Version> {

        private int major;
        private int minor;
        private int patch;

        public Version(String version) {
            String[] splitted = version.split("\\.");

            if (splitted.length == 1) {
                this.major = Integer.parseInt(splitted[0]);
            } else if (splitted.length == 2) {
                this.major = Integer.parseInt(splitted[0]);
                this.minor = Integer.parseInt(splitted[1]);
            } else {
                this.major = Integer.parseInt(splitted[0]);
                this.minor = Integer.parseInt(splitted[1]);
                this.patch = Integer.parseInt(splitted[2]);
            }
        }
        
        @Override
        public int compareTo(Version o) {
            if (this.major < o.major) return -1;
            if (this.major > o.major) return 1;

            if (this.minor < o.minor) return -1;
            if (this.minor > o.minor) return 1;

            if (this.patch < o.patch) return -1;
            if (this.patch > o.patch) return 1;

            return 0;
        }

        @Override
        public String toString() {
            return this.major + "." + this.minor + "." + this.patch;
        }
    }
    
}
