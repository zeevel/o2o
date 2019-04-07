package com.zeevel.o2o.dao;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.Product;
import com.zeevel.o2o.entity.ProductCategory;
import com.zeevel.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsertProduct(){
        Product product = new Product();
        product.setProductName("商品1");
        product.setProductDesc("商品1描述");
        product.setImgAddr("缩略图片");
        product.setNormalPrice("111");
        product.setPromotionPrice("123");
        product.setPriority(2);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setProductCategory(productCategory);

        Shop shop = new Shop();
        shop.setShopId(1L);
        product.setShop(shop);

        int effectedNum = productDao.insertProduct(product);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testQueryProductByProductId(){
        Product product = productDao.queryProductByProductId(1L);
        System.out.println(product);
    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product();

        Shop shop = new Shop();
        shop.setShopId(1L);
        product.setShop(shop);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setProductCategory(productCategory);

        product.setProductId(1L);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1描述");
        product.setLastEditTime(new Date());
        product.setNormalPrice("100");
        product.setPromotionPrice("80");

        int res = productDao.updateProduct(product);
        assertEquals(1,res);
    }

}