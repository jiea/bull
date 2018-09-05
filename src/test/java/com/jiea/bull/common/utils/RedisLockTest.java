package com.jiea.bull.common.utils;

import com.jiea.bull.BullApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BullApplication.class})
public class RedisLockTest {

    @Autowired
    private RedisLock redisLock;

    @Test
    public void testLock(){
        redisLock.lock("qqqq", "qqq", 50L);
    }
}
