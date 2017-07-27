package com.tfdxl.test;


import com.tfdxl.api.IOperation;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by tianfeng on 2017/7/20.
 */
public class Main {

    public static void main(String[] args) {
//        IOperation plus = new PlusOperationImpl();
//        IOperation division = new DivisionOpertionImpl();
//        System.out.println(plus.operation(5, 3));
//        System.out.println(division.operation(9, 3));

        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        Iterator<IOperation> operationIterator = operations.iterator();
        System.out.println("classPath:" + System.getProperty("java.class.path"));
        while (operationIterator.hasNext()) {
            IOperation operation = operationIterator.next();
            System.out.println(operation.operation(6, 3));
        }
    }
}
