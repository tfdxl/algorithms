package com.tfdxl.rbtree;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by tianfeng on 2017/3/18.
 *
 * 定义对红黑树操作的接口
 */
public interface RBTreeOperations<T> {

    /**
     * 获取节点的个数
     * @return
     */
    int size();

    /**
     * 是否存在该节点
     * @param data
     * @return
     */
    boolean exists(WrappedData data);


    /**
     * 向红黑树插入数据
     * @param data
     * @return
     */
    boolean insert(WrappedData<T> data);

    /**
     * 从红黑树删除数据
     * @param key
     * @return
     */
    WrappedData<T> delete(int key);

    /**
     * 根据key查找数据
     * @param key
     * @return
     */
    WrappedData<T> search(int key);

    /**
     * 如果key不存在就插入，否则更新
     * @param data
     * @return
     */
    boolean update(WrappedData<T> data);

    /**
     * 按照层级结构打印红黑树的所有的节点，并标注节点的颜色
     */
    void printAllNodes();

    /**
     * 红黑树节点的颜色
     */
    @AllArgsConstructor
    enum Color{

        RED(0,"红色"),
        BLACK(1,"黑色");

        @Getter
        private int color;
        @Getter
        private String desc;

    }
}
