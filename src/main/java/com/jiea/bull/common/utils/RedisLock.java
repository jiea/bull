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

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    private static final Logger LOG = LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean lock(String key, String value, long expire){
        Assert.isTrue(StringUtils.isNotBlank(key), "key is null");
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Boolean success = redisConnection.set(key.getBytes(), value.getBytes(),
                    Expiration.from(expire, TimeUnit.MILLISECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT);

            if(success){
                lo
            }
            return success;
        });
    }


}
