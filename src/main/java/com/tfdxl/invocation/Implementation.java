package com.tfdxl.invocation;

public class Implementation implements Interface{

    public static void main(String[] args) {
        System.err.println(Interface.class.isAssignableFrom(Implementation.class));
        System.err.println(int.class.toString());
    }
}
