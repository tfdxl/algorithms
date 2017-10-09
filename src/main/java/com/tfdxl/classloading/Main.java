package com.tfdxl.classloading;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
//        Parent p  = new Parent();
//        Son son = new Son();

        Class.forName("com.tfdxl.classloading.Parent");
    }
}
