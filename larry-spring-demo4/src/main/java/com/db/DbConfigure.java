package com.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DbConfigure{
	
	Logger LOG = LoggerFactory.getLogger(DbConfigure.class);
	
//	@Bean
//	public DataSource dataSource(Environment env){
//		LOG.info("初始化数据源");
//		DataSource dataSource = new DataSource();
//		dataSource.setUrl(env.getProperty("spring.fwd.url"));
//		dataSource.setUsername(env.getProperty("spring.fwd.username"));
//		dataSource.setPassword(env.getProperty("spring.fwd.password"));
//		dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.fwd.maxActive")));
//		return dataSource;
//	}
}