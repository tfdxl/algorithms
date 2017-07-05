package com.tfdxl.aqs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by tianfeng on 2017/7/5.
 */
public class BeeperControl {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);


    public void beepForAnHour() {

        final Runnable beeper = () -> System.out.println("beep");

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
        scheduler.schedule((Runnable) () -> beeperHandle.cancel(true), 60 * 60, SECONDS);
    }
}
