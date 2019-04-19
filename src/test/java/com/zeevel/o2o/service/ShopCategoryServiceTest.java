package com.zeevel.o2o.service;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ShopCategoryServiceTest extends BaseTest {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void  testGetShopCategoryList(){
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
        System.out.println(shopCategoryList.size());
    }

}