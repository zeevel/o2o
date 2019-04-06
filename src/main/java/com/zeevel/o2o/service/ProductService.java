package com.zeevel.o2o.service;

import com.zeevel.o2o.dto.ImageHolder;
import com.zeevel.o2o.dto.ProductExecution;
import com.zeevel.o2o.entity.Product;

import java.io.InputStream;
import java.util.List;

public interface ProductService {

    /**
     * 添加商品信息以及图片处理
     * @return
     * @throws RuntimeException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> imageHolderList) throws RuntimeException;
}
