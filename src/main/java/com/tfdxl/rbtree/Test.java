package com.tfdxl.rbtree;

import java.util.Random;

/**
 * Created by tianfeng on 2017/3/19.
 */
public class Test {

    public static void main(String[] args) {
        int totalNumber = 15;
        RBTreeOperations rbTreeOperations = new RBTree();
        for(int j = 0;j<totalNumber;j++){
            rbTreeOperations.insert(new WrappedData<Object>(new Random().nextInt(),new Object()));
        }

        System.out.print(rbTreeOperations);
    }
}
