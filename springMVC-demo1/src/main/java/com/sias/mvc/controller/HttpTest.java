package com.sias.mvc.controller;

import com.sias.mvc.Bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Edgar
 * @create 2022-01-26 16:52
 */

@Controller
public class HttpTest {
    @RequestMapping(value = "/testRequestBody" ,method = RequestMethod.POST)
//  1.RequestBody方法中的注解的作用是获得方法体中请求体的数据
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("RequestBody:"+requestBody);
        return "success";
    }

    @RequestMapping(value = "/testRequestEntity" ,method = RequestMethod.POST)
//  2.RequestEntity方法中的获取请求头和请求体
    public String testRequestEntity(RequestEntity<String>  requestEntity){
        System.out.println("请求头："+requestEntity.getHeaders());
        System.out.println("请求体："+requestEntity.getBody());
//        下面的有返回值是实现页面跳转的，返回值的根本原因是些的string这个参数的作用的影响
        return "success";
    }



//    3.服务器向浏览器响应数据
    @RequestMapping(value = "/testResponse" )
    //  2.RequestEntity方法中的获取请求头和请求体
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().println("hello,response");
    }

//    4.SpringMvc中自带的响应到浏览器上的数据
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "dfjsafjakifhaofhoegegegewgo";
    }
//    响应对象到浏览器
    @RequestMapping("/testResopnseUser")
    @ResponseBody
    public User testResponseUser(){
        return new User(1001,"dads","213",122,"男","djojqwf");
    }


//    axios响应浏览器
    @RequestMapping(value = "/testAxios")
    @ResponseBody
    public String testAxios(String username,String password){
        System.out.println(username+","+password);
        return "hello,axios";
    }





}
