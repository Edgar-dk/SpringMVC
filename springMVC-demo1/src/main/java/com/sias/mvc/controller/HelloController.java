package com.sias.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Edgar
 * @create 2022-01-22 10:52
 */
/*
* 这是一个控制器，解析这些方法，解析之后这些方法返回一个值，被试图解析器解析之后，跳转到指定的页面
* 这是首页面的跳转
* */
@Controller
public class HelloController {
//    请求的映射匹配到,访问的是上下问路径下的地址，就是先到首页那个地方，在根据首页来跳转到其他的地方
//    @RequestMapping(value = "/")
//    public String index(){
//        return "index";
//    }

    @RequestMapping(value = "target")
    public String totarget(){
        return "target";
    }
    @RequestMapping(value = "/param")
    public String param(){
        return "params";
    }
    @RequestMapping(value = "/test_view")
    public String test_view(){
        return "test_view";
    }
}
