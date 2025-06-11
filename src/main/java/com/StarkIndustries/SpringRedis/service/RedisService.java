package com.StarkIndustries.SpringRedis.service;

import com.StarkIndustries.SpringRedis.model.SimpleKeyValueModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.security.Key;

@Service
public class RedisService {

    /* 1) adding object: add normally using opsForValue().set(key,value);
       2) while fetching: we have to use the generic class <T> since we donot know what is the type of the object which will be fetched m so we could not
       store it in an declared class during buffer.
       so use Object Mapper object to convert it to the required class.
    * */

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    public RedisTemplate<String,Object> redisTemplate;

    public boolean addValue(SimpleKeyValueModel simpleKeyValueModel){

//        try{
//            Class<?> tClass = Class.forName(simpleKeyValueModel.getClassName());
//            ObjectMapper objectMapper = new ObjectMapper();
//            if(tClass==String.class && !simpleKeyValueModel.getValue().startsWith("\""))
//                simpleKeyValueModel.setValue("\"" +simpleKeyValueModel.getValue()+"\"");
//            T value = (T) objectMapper.readValue(simpleKeyValueModel.getValue(),tClass);
//            this.redisTemplate.opsForValue().set(simpleKeyValueModel.getKey(),value);
//            return value;
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println(e.getLocalizedMessage());
//        }
//        return null;

        this.redisTemplate.opsForValue().set(simpleKeyValueModel.getKey(), simpleKeyValueModel.getValue());
        return true;

    }

    public<T> T getValue(String key,Class<T> entityClass) {

        try{
            Object object = this.redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            if(object!=null){
                log.info(object.toString());
                return objectMapper.convertValue(object,entityClass);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public boolean deleteValue(String key){
        this.redisTemplate.delete(key);
        return true;
    }
}
