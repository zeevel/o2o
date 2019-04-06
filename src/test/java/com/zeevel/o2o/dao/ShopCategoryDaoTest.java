package com.zeevel.o2o.dao;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(2,shopCategories.size());
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParent(parentCategory);
        shopCategories = shopCategoryDao.queryShopCategory(testCategory);
        assertEquals(1,shopCategories.size());
    }

}