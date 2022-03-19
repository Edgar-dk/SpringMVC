package com.sias.mvc.Intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edgar
 * @create 2022-01-27 17:52
 */






/*
*
* 1.这个拦截器虽然写了，但是springmvc是不知道这是拦截器的功能的，还是需要让springmvc知道这
* 是一个拦截器的办法也是有的，就是在springmvc中去配置拦截器的配置文件之后，写上地址之后，springmvc就
* 知道这个是一个拦截器了，在controller的前面和后面以及页面渲染完之后在去执行，
* 还有一点的需要注意的是，创建的类去实现一个接口之后，才可以去实现拦截器的功能*/


//还有一个问题就是，在控制器的方法执行之前去执行和执行之后去执行，页面执行完后去执行，这个方法是哪一个呢？,就是上面写的TestIntercept


/*
* 2.上面的是第一种方式去拦截，还有一种，就是用Bean管理的方式去做拦截器,<ref bean="firstIntercept"></ref>,@Component
* 只是需要在拦截器上面写上相应的Bean管理的注解
* */

@Component
public class firstIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("firstIntercept----->preHandle控制器方法执行之前");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("firstIntercept----->postHandle控制器方法执行之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("firstIntercept----->afterCompletion页面渲染试图之后");
    }
}
