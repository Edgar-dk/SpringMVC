package com.sias.mvc.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Edgar
 * @create 2022-01-27 20:23
 */
@Controller
public class ConfigurationException {
    @RequestMapping(value = "/testException")
    public String testException(){
        System.out.println(1/0);
        return "success";
    }
}
