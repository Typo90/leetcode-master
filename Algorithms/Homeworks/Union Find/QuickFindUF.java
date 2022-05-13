public class QuickFindUF {

    private int[] id;

    // Time: N
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // Time: 1
    public boolean conneted(int p, int q) {
        if (id[p] == id[q]) {
            return true;
        } else {
            return false;
        }
    }

    // Time N
    public void union(int p, int q) {

        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {

            if (id[i] == pid) {
                id[i] = qid;
            }

        }

    }

    public static void main(String[] args) {

    }
}
