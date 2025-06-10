package com.StarkIndustries.SpringRedis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Key;

@Service
public class RedisService {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    public boolean addValue(String key,String value){
        this.stringRedisTemplate.opsForValue().set(key,value);
        return true;
    }
}
