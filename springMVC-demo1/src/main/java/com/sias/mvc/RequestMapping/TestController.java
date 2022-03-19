package com.sias.mvc.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Edgar
 * @create 2022-01-22 14:48
 */
/*
* 在首页的基础上跳转到其他的页面上，这个控制器是主要的一步*/
@Controller
//@RequestMapping("/hello")
public class TestController {

//    0.整体的形式去注解
    @RequestMapping(
            value = {"/test","/success"},
            method = {RequestMethod.GET,RequestMethod.POST},
            params = {"username"}
    )
    public String success(){
        return "success";
    }

//    1.GetMapping注解
    @GetMapping("/a?a/GetMapping")
    public  String GetTest(){
        return "success";
    }
//    2.PostMapping注解

    @PostMapping("/PostMapping")
    public String PostTest(){
        return "success";
    }


//    3.参数的形式传递修改数据
        /*在括号总的参数是依据，传递过来的数据定的，类型和注解来接受*/
    @RequestMapping("/testpath/{id}/{username}")
    public String testpath(@PathVariable("id")Integer id,@PathVariable("username") String username){
//        需要注意一点的就是，在输出的时候，双引号是输出的值，想要输出的值需要在前面和后面加上+的形式
        System.out.println("id:"+id+",username:"+username);
        return "success";
    }
}
