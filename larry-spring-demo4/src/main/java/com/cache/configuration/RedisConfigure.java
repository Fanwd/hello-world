package com.cache.configuration;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.cache.RedisDao;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfigure {
	
	private Logger LOG = LoggerFactory.getLogger(RedisConfigure.class);
	
	@Bean
	public KeyGenerator wiselyKeyGenerator(){
		
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object arg0, Method arg1, Object... arg2) {
				StringBuilder sb = new StringBuilder();
				String str = arg0.getClass().getName();
				LOG.info("generate:Object:"+str);
				sb.append(arg0.getClass().getName());
				str = arg1.getName();
				LOG.info("generate:method:"+str);
				sb.append(arg1.getName());
				for(Object obj:arg2){
					LOG.info("arg2:"+obj.toString());
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
	
	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate){
		return new RedisCacheManager(redisTemplate);
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	@Bean
	public RedisDao redisDao(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate){
		RedisDao redisDao = new RedisDao();
		redisDao.setRedisTemplate((StringRedisTemplate)redisTemplate);
		return redisDao;
	}
}
