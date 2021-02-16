import java.util.Vector;

class treeNode {  /* node object */
    treeNode root;
    treeNode child;      /*declare variables */
    private int num;
    private int rank;

    public treeNode(int n) {  /*constructor for node */
        num = n;
        rank = 0;
    }

    public int getNum() {  /* getters and setters for treeNode object */
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

class fNode implements Comparable{   /* freq node implements comparable */
    public char symbol;
    public int frequency;

    public fNode(char symbol, int freq){
        this.symbol = symbol;     /*constructor for freq node */
        this.frequency = freq;
    }

    @Override
    public int compareTo(Object n) throws ClassCastException{
        if (!(n instanceof fNode)) {      /* override comparable for fnode */
            return 0;
        }
        int nfreq = ((fNode) n).frequency;
        return nfreq - this.frequency;
    }
}

public class uandf{
    public treeNode[] treeNodes;      /* declare variables for union find */
    public treeNode[] nodestack;
    private Vector<Integer> finalarr = new Vector<>();


    public uandf(int n)
    {
        treeNodes = new treeNode[n];    /*constructor for union find size n*/
        nodestack = new treeNode[n];
    }

    private void make_set(int n)    /*method to make a set for a given int */
    {
        treeNode root = new treeNode(n);
        root.child = new treeNode(n);
        root.child.root = root;
        treeNodes[n] = root.child;
    }

    public void union_sets(int i, int j)  /*method unifies two sets containing  i and j*/
    {
        treeNode a = find_set(i);   /*finds the sets with i and j */
        treeNode b = find_set(j);
        if (a == b) {
            return;
        }
        if (a.getRank() > b.getRank()) {  /* adds the smaller to the larger tree */
            b.child.root = a.child;
            a.setNum(j);
        } else {
            a.child.root = b.child;
            b.setNum(j);
            if (a.getRank() == b.getRank()) { /*increments rank of the tree */
                b.setRank(b.getRank()+1);
            }
        }
    }


    public treeNode find_set(int n)  /* finds the set containing a given int */
    {
        treeNode a = treeNodes[n];
        return find_set_helper(a, n);   /*calls helper method */
    }

    private treeNode find_set_helper(treeNode node, int a)
    {
        if (node == null) {                 /* finds the set containing the given int */
            treeNode rt = new treeNode(a);
            rt.child = new treeNode(a);
            rt.child.root = rt;              /* if node does not exist creates one */
            treeNodes[a] = rt.child;
            return rt;
        }else {
            return finder_set_helper_helper(node);
        }                 /*calls helper method to find the root if node exists */
    }

    private treeNode finder_set_helper_helper(treeNode treeNode)
    {
        int root = 0;       /* helper method finds root for a given treeNode */
        while (treeNode.root.child == null) {
            nodestack[root++] = treeNode;
            treeNode = treeNode.root;
        }
        treeNode rtNd = treeNode;         /* uses stack to traverse tree */
        while (root > 0) {
            treeNode = nodestack[--root];
            treeNode.root = rtNd;
        }
        return rtNd.root;           /*returns root */
    }

    public int final_sets(){
        int count = 0;            /* returns total number of sets and creates final sets, resets representatives */
        finalarr = new Vector<>();
        finalarr.add(count);
        for (treeNode treeNode : treeNodes) {
            boolean ex = false;               /* loops through and adds to new vector */
            if (finalarr.contains(treeNode.getNum())) {
                ex = true;
            }
            if (!ex) {
                finalarr.add(treeNode.getNum());
                count++;
            }
        }
        return count;      /* returns total number of sets */
    }
}
