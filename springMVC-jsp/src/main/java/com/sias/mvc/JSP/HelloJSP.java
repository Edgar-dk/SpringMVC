package com.sias.mvc.JSP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Edgar
 * @create 2022-01-25 11:45
 */
@Controller
public class HelloJSP {
    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}
