package com.zeevel.o2o.service;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.dto.ImageHolder;
import com.zeevel.o2o.dto.ProductExecution;
import com.zeevel.o2o.entity.Product;
import com.zeevel.o2o.entity.ProductCategory;
import com.zeevel.o2o.entity.Shop;
import com.zeevel.o2o.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws Exception{
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1描述");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        //创建缩略图文件流
        File thumbnailFile = new File("C:\\Users\\Administrator\\Desktop\\校园商铺\\images\\item\\shopcategory\\2017060420315183203.png");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is);
        //创建两个商品详情图文件流并将他们添加到详情图片中
        File productImg1 = new File("C:\\Users\\Administrator\\Desktop\\校园商铺\\images\\item\\shopcategory\\2017060420322333745.png");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:\\Users\\Administrator\\Desktop\\校园商铺\\images\\item\\shopcategory\\2017060420372391702.png");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        //添加商品并验证
        ProductExecution pe = productService.addProduct(product,thumbnail,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }

}