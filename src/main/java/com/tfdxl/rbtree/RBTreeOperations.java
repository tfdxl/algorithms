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
     * 红色树节点的颜色
     */
    @AllArgsConstructor
    enum Color{

        @Getter
        RED(0,"红色"),
        @Getter
        BLACK(1,"黑色");

        private int color;
        private String desc;

    }
}
