package com.zeevel.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
    @RequestMapping(value="/index",method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }

    @RequestMapping(value="/shoplist",method = RequestMethod.GET)
    private String shopList(){
        return "frontend/shoplist";
    }

    @RequestMapping(value="/shopdetail",method = RequestMethod.GET)
    private String shopDetail(){
        return "frontend/shopdetail";
    }
}
