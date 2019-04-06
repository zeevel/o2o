package com.zeevel.o2o.service;

import com.zeevel.o2o.dto.ShopExecution;
import com.zeevel.o2o.entity.Shop;
import com.zeevel.o2o.exceptions.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

    /**
     * 注册店铺信息，包括图片处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;

    /**
     * 更新店铺信息，包括图片处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;


    /**
     * 通过店铺Id获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 根据shopCondition分页返回shop数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
