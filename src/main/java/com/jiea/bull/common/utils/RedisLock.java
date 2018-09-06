package com.jiea.bull.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    private static final Logger LOG = LoggerFactory.getLogger(RedisLock.class);

    @Resource
    private RedisTemplate redisTemplate;

    public Boolean lock(String key, String value, long expire){
        return set(key, value, expire);
    }

    public Boolean lockBlock(String key, String value, long expire){
        while (true) {
            if(set(key, value, expire)){
                return true;
            }
            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            LOG.info("获取Redis锁休眠被中断", e);
        }
    }

    public void unLock(String key, String value){
        Assert.isTrue(StringUtils.isNotBlank(key), "key must not be blank");
        Assert.isTrue(StringUtils.isNotBlank(value), "value must not be blank");
        if(redisTemplate.hasKey(key) && value.equals(redisTemplate.opsForValue().get(key))){
            redisTemplate.delete(key);
        }
    }

    public Boolean set(String key, String value, long expire){
        Assert.isTrue(StringUtils.isNotBlank(key), "key must not be blank");
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Boolean success = redisConnection.set(key.getBytes(), value.getBytes(),
                    Expiration.from(expire, TimeUnit.MILLISECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT);
            if(success){
                LOG.info("获取Redis锁成功, 时间: {}", System.currentTimeMillis());
            }
            return success;
        });
    }


}
