package com.tfdxl.escapeanalysis;

/**
 * Created by tianfeng on 2017/2/20.
 */
class Main {

    public static void main(String[] args) {
        example();
    }

    public static void example() {
        //两个对象没有逃逸出example,两个对象均在栈中分配内存

        Foo foo = new Foo();
        Bar bar = new Bar();
        bar.setFoo(foo);
    }
}

class Foo {

}

class Bar {
    private Foo foo;

    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
    }
}