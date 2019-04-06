package com.zeevel.o2o.dao;

import com.zeevel.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /**
     * 批量插入详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
