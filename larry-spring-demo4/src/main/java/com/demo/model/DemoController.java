package com.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.fwd.FwdService;

@RestController
public class DemoController {
	
	@Autowired
	JdbcTemplate jdbc = null;
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@Transactional
	public void insertData() throws Exception {
//		try{
			String sql = "insert into test(name) values('buzhidao')";
			int num = jdbc.update(sql);
			log.info("更新条数:"+num);
			num = jdbc.update(sql);
			log.info("更新条数:"+num);
//		}catch(Exception ex){
//			log.error("更新异常", ex);
//			throw ex;
//		}
	}
	
	@Autowired
	FwdService fwdService = null;
	
	@ResponseBody
	@RequestMapping("/test")
	public String test() throws Exception{
		System.out.println("hello");
		log.info("123123123");
		String str = fwdService.insertName("123");
		log.info(str);
		return "Hello World";
	}
	@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
