package com.zeevel.o2o.dao;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.Area;
import com.zeevel.o2o.entity.PersonInfo;
import com.zeevel.o2o.entity.Shop;
import com.zeevel.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    @Ignore
    public void testInsertShop(){
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
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testQueryByShopId(){
        Shop shop = shopDao.queryByShopId(1L);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getOwner().getUserId());
        System.out.println(shop.getOwner().getName());
        System.out.println(shop.getShopCategory().getShopCategoryId());
        System.out.println(shop.getShopCategory().getShopCategoryName());
    }

    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();

        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);

        Area area = new Area();
        area.setAreaId(3);
        shopCondition.setArea(area);

        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(2L);
        shopCondition.setShopCategory(sc);

        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        System.out.println("店铺列表的大小: " +shopList.size());
        int total = shopDao.queryShopCount(shopCondition);
        System.out.println(total);
    }
}