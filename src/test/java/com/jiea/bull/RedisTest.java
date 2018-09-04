package com.jiea.bull;

import com.jiea.bull.domain.User;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BullApplication.class})
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString(){
        Long ss = redisTemplate.opsForValue().increment("ss", 1L);
        System.out.println(ss);
    }

    @Test
    public void testHash(){
        User user = new User();
        user.setId(10L);
        user.setUsername("zhang");
        HashOperations<String, Long, User> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("aaa", user.getId(), user);

        User user1 = hashOperations.get("aaa", user.getId());

        System.out.println(user1.getUsername());
    }

    @Test
    public void testList(){
        ListOperations<String, Integer> listOperations = redisTemplate.opsForList();
        Long l = listOperations.leftPush("list", 1);
        System.out.println(l);

        Long l1 = listOperations.leftPushAll("list", 2, 3, 4, 5);
        System.out.println(l1);

        List<Integer> list = listOperations.range("list", 0, -1);
        System.out.println(list);

        Long size = listOperations.size("list");
        System.out.println(size);



        Integer i = listOperations.leftPop("list");
        System.out.println(i);
    }

    @Test
    public void testSet(){
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
    }

    @Test
    public void testZSet(){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

    }



}
