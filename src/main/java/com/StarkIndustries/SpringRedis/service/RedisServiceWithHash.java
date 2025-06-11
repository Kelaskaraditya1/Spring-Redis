package com.StarkIndustries.SpringRedis.service;

import com.StarkIndustries.SpringRedis.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Map;
import java.util.UUID;

@Service
public class RedisServiceWithHash {

    @Autowired
    public RedisTemplate<String,Object> redisTemplate;

    public static final String REDIS_KEY="PERSONS_COLLECTION";

    public Person addValue(Person person){
        person.setPersonId(UUID.randomUUID().toString());
        this.redisTemplate.opsForHash().put(REDIS_KEY, person.getPersonId(), person);
        return person;
    }

    public Person getValue(String personId){
        return (Person) this.redisTemplate.opsForHash().get(REDIS_KEY,personId);
    }

    public Map<Object,Object> getValues(){
        return this.redisTemplate.opsForHash().entries(REDIS_KEY);
    }

    public boolean deleteValue(String personId){
        this.redisTemplate.opsForHash().delete(REDIS_KEY,personId);
        return true;
    }

}
