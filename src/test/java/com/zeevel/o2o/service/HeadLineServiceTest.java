package com.zeevel.o2o.service;

import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class HeadLineServiceTest extends BaseTest {

    @Autowired
    private HeadLineService headLineService;

    @Test
    public void testGetHeadLineList() throws IOException {
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        List<HeadLine> headLineList = headLineService.getHeadLineList(headLine);
        System.out.println(headLineList.size());
    }

}