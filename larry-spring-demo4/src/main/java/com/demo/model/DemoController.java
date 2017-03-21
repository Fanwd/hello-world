package com.demo.model;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	
	@Autowired
	HttpServletRequest request = null;
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
	
	/**
	 * 项目测试路径
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test")
	public String test() throws Exception{
		System.out.println("hello");
		log.info("123123123");
//		String str = fwdService.insertName("123");
//		log.info(str);
		return "Hello World";
	}
	
	@Autowired
	FwdService fwdService = null;

	/**
	 * 测试插入数据库
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insert")
	public String insert()throws Exception{
		log.info("insert start");
		String str = fwdService.insertName("fwd");
		log.info("str:"+str);
		log.info("insert end");
		return "end";
	}
	
	/**
	 * 测试redis插入数据
	 * @return
	 */
	@RequestMapping("/setredis")
	public String setRedis(){
		String value = request.getParameter("value");
		fwdService.setRedis("fwd", value);
		return "ok";
	}
	
	/**
	 * 测试redis取数据
	 * @return
	 */
	@RequestMapping("/getredis")
	public String getRedis(){
		return fwdService.getRedis("fwd");
	}
	
	/**
	 * 打印事务管理类名
	 * @param platformTransactionManager
	 * @return
	 */
	@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
