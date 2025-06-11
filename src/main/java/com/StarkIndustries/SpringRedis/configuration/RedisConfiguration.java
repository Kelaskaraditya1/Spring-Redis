package com.StarkIndustries.SpringRedis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    /* we write this configuration:
    * 1)to set the type of serialization and de-serialization we want from our redis.
    * 2)to connect to the particular redis port and database
    * 3)we are using StringRedisSerializable for serialization and de-serialization for Key, and GenericJackson2... for serialization and de-serialization*/

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory();
    }


        @Bean
        public RedisTemplate<String, Object> getRedisTemplate(){
            RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory());

            // Key and hash key serializers as Strings
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());

            // Value and hash value serializers as JSON (can handle all object types)
            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

            return redisTemplate;
        }

}
