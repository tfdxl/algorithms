package com.tfdxl.practice.chapter01;

import java.util.Stack;

/**
 * Created by tianfeng on 2017/7/2.
 * <p>
 * 编写一个类，用两个栈实现队列，支持队列的基本操作(add,poll,peek)
 */
public class TwoStackQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStackQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void add(int newData) {
        this.stackPush.push(newData);
    }

    /**
     * remove the head of the queue and return it
     *
     * @return
     */
    public int poll() {
        reverseIfNec();
        return stackPop.pop();
    }

    /**
     * get the head of the queue and return it
     *
     * @return
     */
    public int peek() {
        reverseIfNec();
        return stackPop.peek();
    }

    private void reverseIfNec() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.empty()) {
            //把push中的元素全部反向导到pop中去
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }
}
