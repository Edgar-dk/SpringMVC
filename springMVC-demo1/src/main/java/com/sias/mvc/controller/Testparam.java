package com.sias.mvc.controller;

import com.sias.mvc.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edgar
 * @create 2022-01-23 12:09
 */

/*
* 获取请求的参数*/
@Controller
public class Testparam {



//  1.原生的方式去获取参数
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "target";
    }

//    2.params形参获取参数
    @RequestMapping("/testparams")
//    在多个参数的时候，可以有两种形式去接受数据，一种是字符类型去接受，一种是数组的形式去接收
    public String testparams(
            @RequestParam(value = "user_name" ) String username,
            String password,
            String hobby,
            @RequestHeader("Host") String host
            ){
        System.out.println("username:"+username+",password:"+password+",hobby:"+hobby);
        System.out.println("host:"+host);
        return "success";
    }


//    3.获取多个参数
    @RequestMapping("/testBean")
    public String testBean01(User user){
        System.out.println(user);
        return "target";
    }

}
