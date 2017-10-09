package com.tfdxl.javafeatures;

/**
 * Created by tianfeng on 2017/8/24.
 * 枚举不能继承抽象类
 */
public enum TestEnum implements C{
    ;

    @Override
    public void test() {

    }
}

interface C{
    void test();
}
