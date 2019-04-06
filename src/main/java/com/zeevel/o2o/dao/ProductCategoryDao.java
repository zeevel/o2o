package com.zeevel.o2o.dao;

import com.zeevel.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * 通过shopId查询商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(Long shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                              @Param("shopId") long shopId);

}
