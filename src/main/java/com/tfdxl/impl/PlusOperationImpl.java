package com.tfdxl.impl;

import com.tfdxl.api.IOperation;

/**
 * Created by tianfeng on 2017/7/20.
 */
public class PlusOperationImpl implements IOperation {
    @Override
    public int operation(int numberA, int numberB) {
        return numberA + numberB;
    }

    public static void main(String[] args) {
        System.out.println(PlusOperationImpl.class.getName());
    }
}
