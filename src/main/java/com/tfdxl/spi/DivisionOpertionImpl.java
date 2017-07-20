package com.tfdxl.spi;

import com.tfdxl.api.IOperation;

/**
 * Created by tianfeng on 2017/7/20.
 */
public class DivisionOpertionImpl implements IOperation {
    @Override
    public int operation(int numberA, int numberB) {
        return numberA / numberB;
    }
}
