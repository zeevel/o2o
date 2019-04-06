package com.zeevel.o2o.dao;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryList(){
        Long shopId = 1L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别树为: " +productCategoryList.size());
    }

    @Test
    public void testBatchInsertProcuctCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别4");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别5");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void deleteProductCategory(){
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for(ProductCategory pc:productCategoryList){
            if("商品类别4".equals(pc.getProductCategoryName())||"商品类别5".equals(pc.getProductCategoryName())){
                int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(),shopId);
                assertEquals(1,effectedNum);
            }
        }
    }

}