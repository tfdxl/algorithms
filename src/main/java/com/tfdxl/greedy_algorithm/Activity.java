package com.tfdxl.greedy_algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by tianfeng on 2017/4/9.
 * 表示活动
 */
@Data
@AllArgsConstructor
public class Activity implements Comparable<Activity> {


    private String name;

    private int startTime;

    private int finishTime;

    @Override
    public int compareTo(Activity o) {
        if (this.finishTime > o.getFinishTime()) {
            return 1;
        } else if (this.finishTime < o.getFinishTime()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
