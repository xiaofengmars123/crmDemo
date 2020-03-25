package com.hwua.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

    @ControllerAdvice
    public class AllExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ModelAndView handlerException(Exception ex){
            System.out.println("全局异常处理方法");
            SysException se=null;
            if(ex instanceof SysException){
                se=(SysException)ex;
            }else {
                String message = ex.getMessage();
                se=new SysException(message);
            }
            ModelAndView mv=new ModelAndView();
            mv.addObject("info",se);
            mv.setViewName("403");
            return mv;
        }
    }
