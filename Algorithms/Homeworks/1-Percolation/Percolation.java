import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private WeightedQuickUnionUF UF;
    private int numberOfOpenSites;
    // UF = new WeightedQuickUnionUF(n)

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {

        }
        grid = new int[n][n];
        // ( 0,..., n-1)
        // ( n,...,2n-1)
        // (2n,...,3n-1)
        // ((n-1)(n),...,(n)(n-1))
        UF = new WeightedQuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            this.grid[row][col] = 1;
            this.numberOfOpenSites++;
            if (row == 0 && col == 0) {

            }
        }
        // if(row==0||row==)
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}