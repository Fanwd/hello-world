package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.common.SpringUtil;

@EnableTransactionManagement
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		SpringApplicationBuilder sab = application.sources(Application.class).bannerMode(Banner.Mode.OFF);

		SpringApplicationBuilder sab = application.sources(Application.class);
		sab.listeners(new ApplicationListener<ContextRefreshedEvent>(){
			Logger LOG = LoggerFactory.getLogger(ApplicationListener.class);
			@Override
			public void onApplicationEvent(ContextRefreshedEvent arg0) {
				System.out.println("1212312321312312312312312312");;
				LOG.info("××××××××××××××××××××初始化开始××××××××××××××××××××");
				LOG.info("-------------------------------------");
				SpringUtil.setApplicationContext(arg0.getApplicationContext());
				LOG.info("××××××××××××××××××××初始化完成××××××××××××××××××××");
				System.out.println("1212312321312312312312312312");;
			}
			
		});
		
		return sab;
	}
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}