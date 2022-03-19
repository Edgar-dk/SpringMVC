package com.sias.mvc.DomainObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Edgar
 * @create 2022-01-24 11:31
 */
@Controller
public class ScopeController {

    /*
    *1.原始的方式传递数据
    *  既然是传递数据，那么就需要页面之间的互动，页面之间靠的是作用域
    *  来共享数据（就是传递数据），作用域，有几个，
    *  request,session,application
     */
    @RequestMapping("/testRequestServletAPI")
    public String te (HttpServletRequest request){
//        想要用HttpServletRequest，这个类的时候需要把这个类传递过来，传递的时候，是可以直接在
//        括号中去创建对象的（个人的理解），然后在用创建好对象的名字去调用相关的方法，例如下面的方法
        request.setAttribute("testRequestScope","test");
        return "success";
    }




//    2.SpringMVC中的共享数据的方法
//    testModelAndView
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mav =new ModelAndView();
        mav.addObject("testModelAndView","hello,testModelAndView");
        mav.setViewName("success");
        return mav;
    }
//Model方式
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testRequestScope","hello,djs");
        /*这种方式的共享数据，会吧hello,djs，共享到
        * success的页面上。在success页面上，也会有特定的方式去接收
        * addAttribute的第一个是按照这个名字接受，后面的数据，在
        * 用文本的形式打印在页面上*/
        return "success";
    }
//    session的方式
    @RequestMapping("/testsession")
    public String testsession(HttpSession session){
        session.setAttribute("testSessionScope","hello.password");
        return "success";
    }
//    Application的方式接收数据
    @RequestMapping("/testapplication")
    public String testapplication(HttpSession session){
        ServletContext application  = session.getServletContext();
        application.setAttribute("testApplicationScope","hello.application");
//        return的是转发的一步，可以转发到页面或者是转发到请求，
        return "success";
    }






}
