package com.demo.model.fwd;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	/**
	 * 插入数据库(事物测试)
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String insertName(String name)throws Exception{
		Random r = new Random();
		String n = ""+r.nextInt();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		n = format.format(new Date());
		/* 插入测试 */
		String sql = "insert into test values('"+n+"')";
		jdbc.update(sql);
		/* 事务测试 */
		String sql1 = "insert into test values('1')";
		jdbc.update(sql1);
		return "End";
	}
	
	/**
	 * 获取redis值
	 * @param key
	 * @return
	 */
	public String getRedis(String key){
		String str = redisDao.get(key);
		return str;
	}
	/**
	 * 设置redis值
	 * @param key
	 * @param value
	 */
	public void setRedis(String key, String value){
		redisDao.save(key, value);
	}
}
