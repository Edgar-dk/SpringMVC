package com.sias.mvc.Intercept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Edgar
 * @create 2022-01-27 17:48
 */
@Controller
public class TestIntercept {
    @RequestMapping(value = "/testIntercept")
    public String testIntercept(){
        return "success";
    }

}
