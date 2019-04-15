package com.zeevel.o2o.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeevel.o2o.BaseTest;
import com.zeevel.o2o.entity.Shop;
import org.junit.Test;

import java.io.IOException;

public class JacksonDatabindTest extends BaseTest {

    @Test
    public void testJackson(){
        ObjectMapper mapper = new ObjectMapper();
        String shopStr = "{\"shopName\":\"asdjfias\",\"shopAddr\":\"sfdaf\",\"phone\":\"12341234231\"," +
                "\"shopDesc\":\"fasdfasd\",\"shopCategory\":{\"shopCategoryId\":20},\"area\":{\"areaId\":5}}";
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr,Shop.class);
        } catch (IOException e) {
            System.out.println("出错");
        }
        System.out.println("ok");
    }

}
