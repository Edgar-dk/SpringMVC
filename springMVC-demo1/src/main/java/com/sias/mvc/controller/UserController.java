package com.sias.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Edgar
 * @create 2022-01-25 13:37
 */
@Controller
public class UserController {
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAlluser(){
        System.out.println("查询所有用户的信息");
        return "success";
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserByid(){
        System.out.println("根据id来查询用户的信息");
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertUser(String username,String password){
        System.out.println("添加用户的信息:"+username+","+password);
        return "success";
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String update(String username,String password){
        System.out.println("修改用户的信息:"+username+","+password);
        return "success";
    }

}
