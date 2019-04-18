package com.zeevel.o2o.service.impl;

import com.zeevel.o2o.dao.PersonInfoDao;
import com.zeevel.o2o.dao.WechatAuthDao;
import com.zeevel.o2o.dto.WechatAuthExecution;
import com.zeevel.o2o.entity.PersonInfo;
import com.zeevel.o2o.entity.WechatAuth;
import com.zeevel.o2o.enums.WechatAuthStateEnum;
import com.zeevel.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private static Logger log = LoggerFactory.getLogger(WechatAuthService.class);
    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws RuntimeException {
        //控制判断
        if(wechatAuth==null||wechatAuth.getOpenId()==null){
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try{
            //设置创建时间
            wechatAuth.setCreateTime(new Date());
            //如果微信账号里夹带着用户信息并且用户Id为空，则认为该用户第一次使用平台(且通过微信登录)
            //则自动创建用户信息
            if(wechatAuth.getPersonInfo()!=null&&wechatAuth.getPersonInfo().getUserId()==null){
                try{
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);   //personInfo和wechatAuth.personInfo都会获得userId值
                    if(effectedNum<=0){
                        throw new RuntimeException("添加用户信息失败");
                    }
                }catch (Exception e){
                    log.error("insertPersonInfo error:" +e.toString());
                    throw new RuntimeException("insertPersonInfo error:" + e.getMessage());
                }
            }
            //创建专属于本平台的微信账号
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if(effectedNum <= 0){
                throw new RuntimeException("账号创建失败");
            }else{
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,wechatAuth);
            }
        }catch(Exception e){
            log.error("insertWechatAuth error:" +e.toString());
            throw new RuntimeException("insertWechatAuth error: " +e.getMessage());
        }
    }

}
