package com.sias.mvc.Config;

import com.sias.mvc.Intercept.TestIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * @author Edgar
 * @create 2022-01-27 21:46
 */


/*
* 代替springMVC的配置文件，这个类里面是可以些上很多的注解的*/

@Configuration//是一个配置类信息
@ComponentScan("com.sias.mvc")//扫描组件
@EnableWebMvc//注解驱动
public class WebConfig implements WebMvcConfigurer {


//    1.开启静态资源，例如在static中的图片就是静态资源default-servlet-handler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    2.开启拦截器（拦截控制方法之前和之后）,是拦截所有的控制器中的方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TestIntercept testIntercept=new TestIntercept();
        registry.addInterceptor(testIntercept).addPathPatterns("/**");
    }
//    3.浏览器可以直接跳转到这个指定的页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("success");
    }

//    4.文件上传解析器,在配置文件中是一个bean管理的方式，所以在这个里面也是一个bean管理的方式
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }



//    5.异常处理
    @Override//        创建好异常的信息之后，需要去创建一个可能会发生的异常对象，最后在把这个对象放到创建好的异常信息中，就是放到list集合中
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        下面是自定义的异常（可能会发生的）
        SimpleMappingExceptionResolver exceptionResolver=new SimpleMappingExceptionResolver();
//        可能会发生的异常，发生之后，会跳转到指定的页面，可能会发生的异常，需要用相应的对象中的一个方法去接收的，下面的就是这个方法
        Properties prop =new Properties();
        prop.setProperty("java.lang.ArithmeticException","error");
//        自定义的异常信息，去接收跳转的地址，跳转到这个地址，之后，在去把发生的什么异常的信息传递过去
        exceptionResolver.setExceptionMappings(prop);
        exceptionResolver.setExceptionAttribute("ec");
//        最后在把所有的信息放到集合中
        resolvers.add(exceptionResolver);

//        simpleMappingExceptionResolver.
//        创建异常解析器，最后在放到list集合中
    }




    //6.配置是试图解析器
    //配置生成模板解析器（第一层）
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //生成模板引擎并为模板引擎注入模板解析器（第二层，需要有上面的一层）
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //生成视图解析器并未解析器注入模板引擎（第三层，需要有上面两层）
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
}
