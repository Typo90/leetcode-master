import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {

        /*
         * Knuth’s method: when reading the ith word,
         * select it with probability 1/i to be the champion,
         * replacing the previous champion.
         */
        double count = 1;

        while (!StdIn.isEmpty()) {

            String s = StdIn.readString();

            if (StdRandom.bernoulli(1.0 / count) == true) {

            }
            count++;
            StdOut.println(s);
            s = StdIn.readString();
            // StdOut.println("break");
        }

    }

}
