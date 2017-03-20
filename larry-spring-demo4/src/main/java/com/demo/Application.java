package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cache.configuration.RedisConfigure;
import com.demo.common.SpringUtil;

@EnableTransactionManagement
@SpringBootApplication
@Import({RedisConfigure.class})
public class Application extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		SpringApplicationBuilder sab = application.sources(Application.class).bannerMode(Banner.Mode.OFF);
		SpringApplicationBuilder sab = application.sources(Application.class);
		sab.listeners(new ApplicationListener<ContextRefreshedEvent>(){
			@Override
			public void onApplicationEvent(ContextRefreshedEvent arg0) {
				logger.info("××××××××××××××××××××初始化开始××××××××××××××××××××");
				logger.info("-------------------------------------");
				SpringUtil.setApplicationContext(arg0.getApplicationContext());
				logger.info("××××××××××××××××××××初始化完成××××××××××××××××××××");
				System.out.println("1212312321312312312312312312");;
			}
		});
		return sab;
	}
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}