package com.trainh.tcookbe.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate ) {
        this.redisTemplate = redisTemplate;
    }

    private static final long CACHE_EXPIRATION = 60;

    public void save(String key, Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonValue);
    }

    public Object find(String key) throws JsonProcessingException {
        String jsonValue = (String) redisTemplate.opsForValue().get(key);
        if (jsonValue != null) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonValue, Object.class);
        }
        return null;
    }
}
