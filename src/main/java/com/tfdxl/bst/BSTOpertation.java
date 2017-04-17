package com.tfdxl.bst;

/**
 * Created by tianfeng on 2017/3/26.
 */
public interface BSTOpertation {

    /**
     * 根据key查找
     *
     * @param key
     * @return
     */
    WrappedData btreeSearch(int key);

    /**
     * 删除节点
     *
     * @param key
     * @return
     */
    WrappedData delete(int key);

    /**
     * 获取key最小的节点
     *
     * @return
     */
    WrappedData treeMinimum();

    /**
     * 获取key最大的节点
     *
     * @return
     */
    WrappedData treeMaximum();

    /**
     * 获取x的前驱节点
     *
     * @param x
     * @return
     */
    WrappedData treeSuccessor(WrappedData x);

    /**
     * 获取x的后继节点
     *
     * @param x
     * @return
     */
    WrappedData treePredecessor(WrappedData x);
}
