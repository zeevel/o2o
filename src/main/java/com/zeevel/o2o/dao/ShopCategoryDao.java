package com.zeevel.o2o.dao;

import java.util.List;

import com.zeevel.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import com.zeevel.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
    /**
     *
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> queryShopCategory(
            @Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

}
