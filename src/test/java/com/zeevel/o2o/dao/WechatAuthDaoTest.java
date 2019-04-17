package com.zeevel.o2o.dao;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.PersonInfo;
import com.zeevel.o2o.entity.WechatAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class WechatAuthDaoTest extends BaseTest {
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testInsertWechatAuth() throws Exception{
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(3L);
        //给微信账号上绑定上用户信息
        wechatAuth.setPersonInfo(personInfo);
        //随意设置上openId
        wechatAuth.setOpenId("datafadsfasd");
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testQueryWechatAuthByOpenId() throws Exception{
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("datafadsfasd");
        System.out.println(wechatAuth);
    }

}