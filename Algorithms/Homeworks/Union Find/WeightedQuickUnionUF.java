public class WeightedQuickUnionUF {

    private int[] id;
    private int[] sz;

    // Time: N
    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int r) {

        // normal
        while (r != id[r]) {
            r = id[r];
        }

        // extend the tree to plant
        while (r != id[r]) {
            id[r] = id[id[r]];
            r = id[r];
        }

        return r;

    }

    // Time: logN
    private boolean conneted(int p, int q) {
        return root(p) == root(q);
    }

    // Time: lgN
    private void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (pid == qid) {
            return;
        }

        if (sz[pid] < sz[qid]) {

            id[pid] = qid;
            sz[qid] += sz[pid];
        } else {

            id[qid] = pid;
            sz[pid] += sz[qid];
        }
        // id[pid] = qid;

    }

    public static void main(String[] args) {

    }

}
