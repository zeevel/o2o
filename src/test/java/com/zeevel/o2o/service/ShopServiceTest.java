package com.zeevel.o2o.service;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.dto.ImageHolder;
import com.zeevel.o2o.dto.ShopExecution;
import com.zeevel.o2o.entity.Area;
import com.zeevel.o2o.entity.PersonInfo;
import com.zeevel.o2o.entity.Shop;
import com.zeevel.o2o.entity.ShopCategory;
import com.zeevel.o2o.enums.ShopStateEnum;
import com.zeevel.o2o.util.ImageUtil;
import com.zeevel.o2o.util.PathUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop()throws Exception{
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺test");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        File shopImg = new File("C:/Users/Administrator/Desktop/xiaohuang.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder thumbnail = new ImageHolder(shopImg.getName(),is);
        ShopExecution se = shopService.addShop(shop, thumbnail);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }

    @Test
    public void testThumbnail() throws Exception{
        File shopImg = new File("C:/Users/Administrator/Desktop/xiaohuang.jpg");
        InputStream is = new FileInputStream(shopImg);
        String basePath = PathUtil.getImgBasePath();
        String dest = PathUtil.getShopImagePath(1L);
        String realFileName = ImageUtil.getRandomFileName();
        System.out.println(basePath + dest + realFileName + ".jpg");
    }

    @Test
    public void testModifyShop() throws Exception{
        Shop shop = new Shop();
        shop.setShopId(33L);
        shop.setShopName("修改后的店铺名称");

        File shopImg = new File("C:/Users/Administrator/Desktop/xjpic.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder thumbnail = new ImageHolder(shopImg.getName(),is);
        ShopExecution shopExecution = shopService.modifyShop(shop, thumbnail);
        System.out.println("新的图片地址为：" +shopExecution.getShop().getShopImg());
    }

    @Test
    public void testGetShopList(){
        //There is no getter for property shopCategory TODO
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(2L);
        shopCondition.setShopCategory(sc);


        ShopExecution se = shopService.getShopList(shopCondition, 1, 5);
        System.out.println(se.getShopList());
    }

}