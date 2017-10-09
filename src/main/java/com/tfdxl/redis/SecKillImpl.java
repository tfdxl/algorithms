package com.tfdxl.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianfeng on 2017/10/9.
 */
public class SecKillImpl implements SeckillInterface {

    static Map<Long, Long> inventory;

    static {
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000L);
        inventory.put(10000002L, 10000L);
    }

    @Override
    public void secKill(String userId, @LockedObject Long commidityID) {
        System.out.println(userId);
        this.reduceInventory(commidityID);
    }

    /**
     * 模拟减库存操作
     *
     * @param commidityId
     * @return
     */
    private Long reduceInventory(Long commidityId) {
        inventory.put(commidityId, inventory.get(commidityId) - 1);
        return inventory.get(commidityId);
    }
}
