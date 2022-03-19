package com.sias.mvc.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Edgar
 * @create 2022-01-27 21:02
 */
@ControllerAdvice//功能是有Controller的也是有异常的功能
public class NoteException {
    @ExceptionHandler(value = {ArithmeticException.class,NullPointerException.class})
    public String testException(Exception ec, Model model){
        model.addAttribute("ec",ec);
        return "error";
    }
}
