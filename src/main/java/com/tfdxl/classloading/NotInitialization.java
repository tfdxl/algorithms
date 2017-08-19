package com.tfdxl.classloading;

/**
 *
 *
 * 对于静态字段，只有直接定义这个字段的类才会被初始化，
 * 因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
 */
class SSClass {
    static {
        System.out.println("SSClass");
    }
}

class SuperClass extends SSClass {
    static {
        System.out.println("Super class init ");
    }

    public static int value = 123456;

    public SuperClass() {
        System.out.println("init superclass");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("Sub Class init ");
    }

    static int a;

    public SubClass() {
        System.out.println("init sub class");
    }
}

public class NotInitialization {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
