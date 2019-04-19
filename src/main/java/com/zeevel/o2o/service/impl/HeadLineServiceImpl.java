package com.zeevel.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeevel.o2o.cache.JedisUtil;
import com.zeevel.o2o.dao.HeadLineDao;
import com.zeevel.o2o.entity.HeadLine;
import com.zeevel.o2o.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static String HLLISTKEY = "headlinelist";
    private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        String key = HLLISTKEY;
        List<HeadLine> headLineList = null;
        ObjectMapper mapper = new ObjectMapper();
        if(headLineCondition!=null&&headLineCondition.getEnableStatus()!=null){
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        if(!jedisKeys.exists(key)){
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            String jsonString;
            try{
                jsonString = mapper.writeValueAsString(headLineList);
            }catch (JsonProcessingException e){
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
            jedisStrings.set(key,jsonString);
        }else{
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,HeadLine.class);
            try{
                headLineList = mapper.readValue(jsonString,javaType);
            }catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return headLineList;
    }
}
