package com.tfdxl.bst;

import lombok.Data;

/**
 * Created by tianfeng on 2017/3/26.
 */
public class BinarySearchTree {


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
