package com.tfdxl.greedy_algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianfeng on 2017/4/9.
 * <p>
 * 贪心算法通常做出的是自顶向下的设计：做出一个选择，然后求解剩余的子问题
 * ，而不是自底向上求解出很多的子问题，然后做出选择
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {

        Activity[] activities = new Activity[12];
        Activity dummy = new Activity("dummy-actity-0", 0, 0);
        activities[0] = dummy;
        Activity activity1 = new Activity("act-1", 1, 4);
        Activity activity2 = new Activity("act-2", 3, 5);
        Activity activity3 = new Activity("act-3", 0, 6);
        Activity activity4 = new Activity("act-4", 5, 7);
        Activity activity5 = new Activity("act-5", 3, 9);

        Activity activity6 = new Activity("act-6", 5, 9);
        Activity activity7 = new Activity("act-7", 6, 10);
        Activity activity8 = new Activity("act-8", 8, 11);
        Activity activity9 = new Activity("act-9", 8, 12);
        Activity activity10 = new Activity("act-10", 2, 14);

        Activity activity11 = new Activity("act-11", 12, 16);

        activities[1] = activity1;
        activities[2] = activity2;
        activities[3] = activity3;
        activities[4] = activity4;
        activities[5] = activity5;

        activities[6] = activity6;
        activities[7] = activity7;
        activities[8] = activity8;
        activities[9] = activity9;
        activities[10] = activity10;

        activities[11] = activity11;

        for (Activity activity : greedyActivitySelector(activities)) {
            System.out.println(activity.toString());
        }
    }

    /**
     * 递归版本
     *
     * @param activities
     * @param k
     * @param n
     * @return
     */
    public static Deque<Activity> recursiveActivitySelector(Activity[] activities, int k, int n) {
        int m = k + 1;
        //找到第一个和a[k]兼容的活动
        while (m <= n && activities[m].getStartTime() < activities[k].getFinishTime()) {
            m++;
        }

        //找到了如果还在问题规模范围内，就把这个放在双端队列头
        if (m <= n) {
            Deque<Activity> deque = new ArrayDeque<>();
            for (Activity activity : recursiveActivitySelector(activities, m, n)) {
                deque.addLast(activity);
            }
            deque.addFirst(activities[m]);
            return deque;
        } else {
            return new ArrayDeque<Activity>();
        }
    }

    /**
     * 迭代版本
     *
     * @return
     */
    public static Deque<Activity> greedyActivitySelector(Activity[] activities) {
        //假设第一个活动为伪活动
        if (activities == null || activities.length <= 0) {
            return null;
        }
        int length = activities.length;
        Deque<Activity> deque = new ArrayDeque<>();
        deque.addLast(activities[1]);
        int k = 1;

        for (int m = 1; m < length; m++) {
            if (activities[m].getStartTime() >= activities[k].getFinishTime()) {
                deque.addLast(activities[m]);
                k = m;
            }
        }

        return deque;
    }


}
