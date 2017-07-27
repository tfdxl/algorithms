package com.tfdxl.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianfeng on 2017/7/27.
 */

// Given a set of distinct integers, nums, return all possible subsets.

// Note: The solution set must not contain duplicate subsets.

// For example,
// If nums = [1,2,3], a solution is:

// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]
public class Solution01 {

    public static List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        recurse(result, nums, new Stack<>(), 0);
        return result;
    }

    private static void recurse(List<List<Integer>> result, int[] nums, Stack<Integer> path, int position) {

        System.out.println("The current position is " + position);
        if (position == nums.length) {
            System.out.println("the added is " + path);
            result.add
                    (new ArrayList<>(path));
            return;
        }
        System.out.println("The element is pushed " + nums[position]);
        path.push(nums[position]);
        recurse(result, nums, path, position + 1);
        System.out.println("The element is popped "+path.pop());
        recurse(result, nums, path, position + 1);
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6};
        List<List<Integer>> result = subsets(num);
        for (List<Integer> item : result) {
            System.out.println(item.toString());
        }
    }
}
