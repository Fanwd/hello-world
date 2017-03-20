package com.demo.model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test{
	
	@Autowired
	JdbcTemplate jdbc  = null;
	
	private static String driverClassName = "com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://127.0.0.1:3306/webtestdb?useUnicode=true&characterEncoding=utf8&useSSL=true";
	private static String user = "root";
	private static String password = "123456";
	
	private static Logger LOG = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args){
		try{
			JdbcTemplate jdbc = new Test().jdbc;
			List<Map<String, Object>> list = jdbc.queryForList("select * from test");
			LOG.info(list.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 数据库连接测试
	 */
	public static void test(){
		try{
//			DataSource dataSource = new DataSource();
//			dataSource.setUrl(url);
//			dataSource.setDriverClassName(driverClassName);
//			dataSource.setUsername(user);
//			dataSource.setPassword(password);
//			dataSource.setMaxActive(10);
//			dataSource.setMaxIdle(maxIdle);
//			int maxActive = dataSource.getMaxActive();
//			int maxIdle = dataSource.getMaxIdle();
//			LOG.info("maxActive:"+maxActive);
//			LOG.info("MaxIdle:"+maxIdle);
//			JdbcTemplate jdbc = new JdbcTemplate(dataSource);
//			String sql = "select * from test where name=?";
//			Object[] arg = new Object[1];
//			arg[0] = "fanweidong";
//			List<Map<String, Object>> list = jdbc.queryForList(sql, arg);
//			LOG.info(list.toString());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection(url, user, password);
//			con.setAutoCommit(false);
//			String sql = "select * from test where name=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, "fanweidong");
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				String str = rs.getString(1);
//				LOG.info(str);
//			}
//			Statement st = con.createStatement();
//			String sql = "insert into test(name) values('123')";
//			int ret = st.executeUpdate(sql);
//			sql = "select * from test";
//			ResultSet rs = st.executeQuery(sql);
//			while(rs.next()){
//				String name = rs.getString(1);
//				LOG.info("name:"+name);
//			}
//			st.close();
			LOG.info("start");
		}catch(Exception ex){
			LOG.error("异常", ex);
		}
	}
}