package com.tfdxl.rbtree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by tianfeng on 2017/3/18.
 */
@Data
@AllArgsConstructor
public class WrappedData<T> {

    private int key;
    private T data;

    @Override
    public boolean equals(Object that){
        return false;
    }
}
