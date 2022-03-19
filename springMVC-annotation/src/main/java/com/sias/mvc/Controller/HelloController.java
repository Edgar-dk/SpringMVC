package com.sias.mvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Edgar
 * @create 2022-01-28 17:28
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
