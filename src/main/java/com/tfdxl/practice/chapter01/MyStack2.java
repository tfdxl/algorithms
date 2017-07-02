package com.tfdxl.practice.chapter01;

import java.util.Stack;

/**
 * Created by tianfeng on 2017/7/2.
 */
public class MyStack2 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack2() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newData) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newData);
        } else if (newData < this.getMin()) {
            this.stackMin.push(newData);
        } else {
            int newMin = this.stackMin.peek();
            this.stackMin.push(newMin);
        }

        this.stackData.push(newData);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }


    public int getMin() {
        if (this.stackMin.isEmpty())
            throw new RuntimeException("Your stack is empty");
        return this.stackMin.peek();
    }

}
