package com.jiea.bull.common.utils;

import com.jiea.bull.BullApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BullApplication.class})
public class RedisLockTest {

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private RedisTemplate redisTemplate;


    private static int s = 0;

    private static int threadCount = 4;
    @Test
    public void testLock(){
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0; i<threadCount; i++){
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object o = redisTemplate.opsForValue().get("-1157793070");

                System.out.println(o);

            }).start();
            countDownLatch.countDown();
        }
    }

    @Test
    public void test(){

        Boolean a = redisTemplate.hasKey("a");
        String o = (String) redisTemplate.opsForValue().get("a");
        System.out.println(a);
        System.out.println(o);
        redisLock.unLock("a", "a");
    }
}
