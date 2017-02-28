package com.demo.model.fwd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FwdService {

	@Autowired
	JdbcTemplate jdbc = null;
	
	private Logger LOG = LoggerFactory.getLogger(FwdService.class);
	
	@Transactional
	public String insertName(String name)throws Exception{
		String sql = "insert into test values('fwd')";
		jdbc.update(sql);
		jdbc.update(sql);
		return "End";
	}
}
