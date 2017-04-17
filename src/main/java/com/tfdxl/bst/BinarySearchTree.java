package com.tfdxl.bst;

import lombok.Data;

/**
 * Created by tianfeng on 2017/3/26.
 */
public class BinarySearchTree implements BSTOpertation {

    private Node root;


    @Override
    public WrappedData btreeSearch(int key) {
        return null;
    }

    @Override
    public WrappedData delete(int key) {
        Node node = searchBykey(key);
        if(node.getLeft() == null){
            transplant(node,node.getRight());
        }else if(node.getRight() == null){
            transplant(node,node.getLeft());
        }else {

        }
        return node.getData();
    }

    @Override
    public WrappedData treeMinimum() {
        return null;
    }

    @Override
    public WrappedData treeMaximum() {
        return null;
    }

    @Override
    public WrappedData treeSuccessor(WrappedData x) {
        return null;
    }

    @Override
    public WrappedData treePredecessor(WrappedData x) {
        return null;
    }



    /**
      用一颗以v为根的子树替换一个以u为根的子树
     *
     * @param u
     * @param v
     */
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            this.root = u;
        } else if (u == u.parent.getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    /**
     * 根据key查找节点
     * @param key
     * @return
     */
    private Node searchBykey(int key){
        return null;
    }


    /**
     * 内部节点定义
     */
    @Data
    static final class Node {


        public Node() {
        }

        public Node(Node left, Node right, Node parent, WrappedData data) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.data = data;
            if (data != null) {
                key = data.getKey();
            }
        }

        public Node(int key) {
            this.key = key;
        }

        public Node(WrappedData data) {
            if (data != null) {
                this.key = data.getKey();
            }
        }


        private Node left;

        private Node right;

        private Node parent;

        private int key;

        private WrappedData data;
    }
}
