package com.tfdxl.rbtree;

import lombok.Data;

/**
 * Created by tianfeng on 2017/3/18.
 */
@Data
public class WrappedData<T> {

    private int key;
    private T data;
}
