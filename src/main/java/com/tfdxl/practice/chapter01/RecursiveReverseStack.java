package com.tfdxl.practice.chapter01;

import java.util.Stack;

/**
 * Created by tianfeng on 2017/7/2.
 */
public class RecursiveReverseStack<E> {

    public E getAndRemoveLastElement(Stack<E> stack) {
        E result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            E last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public void reverse(Stack<E> stack) {
        if (stack.isEmpty())
            return;
        E i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        RecursiveReverseStack recursiveTask = new RecursiveReverseStack<Integer>();
        recursiveTask.reverse(stack);

        System.out.println(stack);
    }
}
