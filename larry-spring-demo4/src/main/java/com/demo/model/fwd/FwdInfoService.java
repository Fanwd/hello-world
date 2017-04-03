package com.demo.model.fwd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * redis 缓存测试
 * Created by guoxiaomin on 2017/4/3.
 */
@Service
public class FwdInfoService {

    @Autowired
    JdbcTemplate jdbcTemplate = null;

    private static Logger LOG = LoggerFactory.getLogger(FwdInfoService.class);

    /**
     * redis缓存
     * @param name
     * @return
     */
    @Cacheable(value="fwdInfo")
    public String getAgeByName(String name){
        String sql = "select age from fwd where name=?";
        Object[] obj = new Object[]{name};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, obj);
        LOG.info("query size:"+list.size());
        if(!list.isEmpty()){
            return list.get(0).get("age")+"";
        }
        return "0";
    }

    @Cacheable(value="fwdBakInfo")
    public String getAgeBak(String name){
        String sql = "select age from fwdbak where name=?";
        Object[] obj = new Object[]{name};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, obj);
        LOG.info("query size bak:"+list.size());
        if(!list.isEmpty()){
            return list.get(0).get("age")+"";
        }
        return "0";
    }

}
