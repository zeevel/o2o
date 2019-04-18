package com.zeevel.o2o.service.impl;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.dto.WechatAuthExecution;
import com.zeevel.o2o.entity.PersonInfo;
import com.zeevel.o2o.entity.WechatAuth;
import com.zeevel.o2o.enums.WechatAuthStateEnum;
import com.zeevel.o2o.service.WechatAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class WechatAuthServiceImplTest extends BaseTest {

    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void testRegister(){
        //新增一条微信账号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        String openId = "abc123";

        personInfo.setCreateTime(new Date());
        personInfo.setName("测试一下...");
        personInfo.setUserType(1);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution wae = wechatAuthService.register(wechatAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wae.getState());

        System.out.println(wechatAuth.getPersonInfo().getUserId());

        wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
        System.out.println(wechatAuth.getPersonInfo().getName());
    }

}