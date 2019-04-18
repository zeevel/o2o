package com.zeevel.o2o.service;

import com.zeevel.o2o.dto.WechatAuthExecution;
import com.zeevel.o2o.entity.WechatAuth;
import org.springframework.beans.factory.annotation.Autowired;

public interface WechatAuthService {

    /**
     * 通过openId查找平台对应的微信账号
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);


    WechatAuthExecution register(WechatAuth wechatAuth) throws RuntimeException;


}
