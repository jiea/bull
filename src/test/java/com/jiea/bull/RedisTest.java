package com.jiea.bull;

import com.jiea.bull.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

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
        Long add1 = setOperations.add("set", "a", "b", "c");
        System.out.println(add1);

        Long size = setOperations.size("set");
        System.out.println(size);

        Set<String> set = setOperations.members("set");
        System.out.println(set);

        Long add2 = setOperations.add("set1", "a", "c", "d", "c");
        Set<String> union = setOperations.union("set", "set1");
        Set<String> difference = setOperations.difference("set1", "set");

        System.out.println("union: " + union);
        System.out.println("difference: " + difference);
    }

    @Test
    public void testZSet(){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset", "1", 1.0);
        zSetOperations.add("zset", "2", 2.0);
        Boolean b = zSetOperations.add("zset", "3", 3.0);
        Boolean b1 = zSetOperations.add("zset", "3", 3.0);
        zSetOperations.add("zset", "4", 0);
        Long size = zSetOperations.size("zset");
        System.out.println(b);
        System.out.println(b1);
        System.out.println(size);

        Set<String> zset = zSetOperations.range("zset", 0, -1);
        System.out.println(zset);

    }



}
