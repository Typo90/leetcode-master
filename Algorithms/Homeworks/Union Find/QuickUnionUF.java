public class QuickUnionUF {
    private int[] id;

    // Time: N
    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int r) {

        while (r != id[r]) {
            r = id[r];
        }
        return r;

    }

    private boolean conneted(int p, int q) {
        return root(p) == root(q);
    }

    private void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        id[pid] = qid;

    }

    public static void main(String[] args) {

    }
}
