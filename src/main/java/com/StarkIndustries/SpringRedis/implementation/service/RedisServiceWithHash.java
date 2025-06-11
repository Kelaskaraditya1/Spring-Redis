package com.StarkIndustries.SpringRedis.implementation.service;

import com.StarkIndustries.SpringRedis.implementation.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class RedisServiceWithHash {

    /* In Ops for hash there is a general/global hash/Key under which all the objects are stored
    *   Like Hash ---> (Key1,Value)
    *        Hash ---> (Key2,Value)
    *   above " PERSONS_COLLECTION " is the Hash/Key under which all the Key/Value pairs are Stored.
    * */

    @Autowired
    public RedisTemplate<String,Object> redisTemplate;

    public static final String REDIS_KEY="PERSONS_COLLECTION";

    public Person addValue(Person person){
        person.setPersonId(UUID.randomUUID().toString());
        this.redisTemplate.opsForHash().put(REDIS_KEY, person.getPersonId(), person);
        return person;
    }

    @Cacheable(value = "person",key = "#personId")
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
