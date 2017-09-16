package com.tfdxl.set;

public class DisjSets {

    private int[] s;

    public DisjSets(int numElements) {
        this.s = new int[numElements];
        for (int i = 0; i < numElements; i++)
            s[i] = -1;
    }

    public int find(int x) {
        if (s[x] < 0)
            return x;
        else return find(s[x]);
    }

    public void union(int root1, int root2) {
        //右边的父指针指向左边
        s[root2] = root1;
    }

    public void unionByRank(int root1, int root2) {
        if (s[root2] < s[root1]) {
            //root2 is deeper
            s[root1] = root2;
        } else {
            if (s[root1] == s[root2]) {
                s[root1]--;
            }
            s[root2] = root1;
        }
    }

}
