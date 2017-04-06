package com.tfdxl.btree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by tianfeng on 2017/4/6.
 */
public class BTree {


    public BTree(int minDegree) {
        init();
        this.minDegree = minDegree;
    }

    //根节点
    private Node root;
    //最小度数
    private int minDegree;


    private RNode bTreeSearch(Node node, int k) {

        int i = 0;
        while (i < node.getNumOfKeys() && k > node.keys[i])
            i++;
        if (i < node.getNumOfKeys() && k == node.keys[i])
            return new RNode(node, i);
        else if (node.isLeaf)
            return null;
        else {
            loadDataFromDisk(node, i);
            return bTreeSearch(node.childrenNodes[i], k);
        }
    }

    public void init() {
        this.root = bTreeCreate();
    }

    public Node bTreeCreate() {
        Node node = allocateNode();
        writeToDisk(node);
        return node;
    }

    private Node allocateNode() {
        Node node = new Node();
        node.isLeaf = true;
        node.setChildrenNodes(new Node[0]);
        node.setKeys(new int[0]);
        node.height = 0;
        node.data = 0;
        node.setNumOfKeys(0);
        return node;
    }

    @Data
    private static class Node {

        //关键字个数
        private int numOfKeys;
        //关键字 n个,关键字对存储在各个子树的关键字加以分割
        private int[] keys;
        //是否是叶子节点
        private boolean isLeaf;
        //高度
        private int height;
        //子节点的引用
        private Node[] childrenNodes;
        //data
        private int data;
    }

    public void writeToDisk(Node node) {

    }

    private void loadDataFromDisk(Node node, int index) {

    }

    @Data
    @AllArgsConstructor
    private static class RNode {

        //key所在的节点
        private Node node;
        //k所在节点的下标
        private int index;
    }
}
