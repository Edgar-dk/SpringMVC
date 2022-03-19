package com.sias.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Edgar
 * @create 2022-01-24 15:48
 */
@Controller
public class ViewController {
    @RequestMapping("/testthyelemof")
    public String testthyelemof(){
        return "success";
    }

    //    3.转发到其他的请求
    @RequestMapping("/testforward")
    public String testforward(){
//       这样的写法是可以直接跳转到testapplication这个请求的，前面的参数去掉，是去掉的是thyelemof为前面加上的地址
        return "forward:/testthyelemof";
    }




//    4.重定向
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testthyelemof";
    }
}
