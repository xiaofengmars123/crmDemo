
package com.hwua.config;

import com.hwua.pojo.Syslog;
import com.hwua.pojo.Users;
import com.hwua.service.SyslogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Component
@Aspect
public class AopConfig {
    @Autowired
    private SyslogService syslogService;

    @Pointcut("execution(* com.hwua.controller.*.*(..))")
    public void p1(){

    }

    //环绕通知，每次执行controller中的方法，记录日志信息
    @Around("p1()")
    public Object log(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Object obj = null;
        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        try {
            Timestamp visitTime = new Timestamp(new Date().getTime());
            String url = request.getRequestURL().toString();
            String ip = request.getRemoteAddr();
            String method = pjp.getSignature().getName();
            long start = System.currentTimeMillis();//记录开始时间
            obj = pjp.proceed(args);//执行业务层方法
            long end = System.currentTimeMillis();//记录结束时间
            //创建一个日志对象并保存
            Syslog syslog = new Syslog();
            syslog.setVisitTime(visitTime);
            Users user = (Users) SecurityUtils.getSubject().getPrincipal();
            syslog.setUsername(user.getUsername());
            syslog.setIp(ip);
            syslog.setUrl(url);
            syslog.setExecutionTime((int) (end-start));
            syslog.setMethod(method);
            //保存日志信息
            syslogService.insert(syslog);
            System.out.println(syslog);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}

