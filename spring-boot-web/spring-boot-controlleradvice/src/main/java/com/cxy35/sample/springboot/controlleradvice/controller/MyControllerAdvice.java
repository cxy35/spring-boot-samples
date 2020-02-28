package com.cxy35.sample.springboot.controlleradvice.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public void globalException(ArrayIndexOutOfBoundsException e, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write("出错了：globalException");
        out.flush();
        out.close();
    }

    /*@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ModelAndView globalException2(ArrayIndexOutOfBoundsException e) throws IOException {
        ModelAndView mv = new ModelAndView("myerror");
        mv.addObject("error", "出错了：globalException2");
        return mv;
    }*/

    @ModelAttribute(value = "dataKey")
    public Map<String,Object> globalData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "cxy35");
        map.put("address", "https://cxy35.com");
        return map;
    }

    @InitBinder("a")
    public void initPreParamA(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }

    @InitBinder("b")
    public void initPreParamB(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
}