package com.zeevel.o2o.dao;

import com.zeevel.o2o.entity.Product;
import com.zeevel.o2o.entity.ProductImg;

import java.util.List;

public interface ProductDao {

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

}
