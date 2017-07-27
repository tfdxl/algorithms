package com.tfdxl.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianfeng on 2017/7/28.
 */
// Given an array of integers, return indices of the two numbers such that they add up to a specific target.

// You may assume that each input would have exactly one solution, and you may not use the same element twice.

// Example:
// Given nums = [2, 7, 11, 15], target = 9,

// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].
public class TwoSums {

    public static int[] twoSums(int[] sums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sums.length; i++) {

            if (map.containsKey(target - sums[i])) {
                result[0] = map.get(target - sums[i]);
                result[1] = i;
                return result;
            }
            map.put(sums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(twoSums(nums, 9)[0]);
        System.out.println(twoSums(nums, 9)[1]);
    }
}
