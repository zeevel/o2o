package com.zeevel.o2o.dao;

import com.zeevel.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return
     */
    List<Area> queryArea();
}
