package com.tfdxl.practice.chapter01;

import java.util.Stack;

/**
 * Created by tianfeng on 2017/7/2.
 * <p>
 * 实现一个栈，在实现栈的基础功能上，再实现返回栈的最小元素的操作.
 * <p>
 * 可以实现现有的栈结构；pop,push,getMin的时间复杂度都是O(1)
 */
public class MyStack {

    /**
     * 使用两个栈保存数据，一个栈保存当前栈中的元素，一个保存每一步的最小值
     */

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newData) {
        if (stackMin.isEmpty()) {
            this.stackMin.push(newData);
        } else if (newData < this.getMin()) {
            this.stackMin.push(newData);
        }
        this.stackData.push(newData);
    }

    public int pop() {
        if (this.stackData.isEmpty())
            throw new RuntimeException("Your stack is empty");
        int value = this.stackData.pop();
        if (value == this.getMin())
            this.stackMin.pop();
        return value;
    }

    public int getMin() {
        if (this.stackMin.isEmpty())
            throw new RuntimeException("Your stack is empty");
        return this.stackMin.peek();
    }

}
