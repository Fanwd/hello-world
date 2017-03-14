package com.cache;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisDao {
	
	private StringRedisTemplate redisTemplate = null;
	
	public void save(String key, String value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	public String get(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	public void setRedisTemplate(StringRedisTemplate redis){
		redisTemplate = redis;
	}
	
	public StringRedisTemplate getRedisTemplate(){
		return redisTemplate;
	}

}
