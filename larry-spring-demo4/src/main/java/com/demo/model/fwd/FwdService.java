package com.demo.model.fwd;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cache.RedisDao;

@Service
public class FwdService {
	
	@Autowired
	StringRedisTemplate redis = null;
	
	@Autowired
	RedisDao redisDao = null;

	@Autowired
	JdbcTemplate jdbc = null;
	
	private Logger LOG = LoggerFactory.getLogger(FwdService.class);
	
	@Transactional
	public String insertName(String name)throws Exception{
		String sql = "insert into test values('fwd')";
		Random r = new Random();
		String n = ""+r.nextInt();
		LOG.info("n:"+n);
		redis.opsForValue().set("fwd", n);
		jdbc.update(sql);
		return "End";
	}
	
	public String getRedis(String key){
		String str = redisDao.get(key);
		return str;
	}
	public void setRedis(String key, String value){
		redisDao.save(key, value);
	}
}
