package com.zyf.t31.core.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author zhengweimin
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    @Pointcut("execution(public * com.kaikeba.t31.*.controller..*.*(..))")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes  == null){
            return ;
        }
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("  URL:" + request.getRequestURL().toString());
        log.info("HTTP_METHOD:" + request.getMethod());
        log.info("IP:" + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            log.info("name:" + name + ",value:" + request.getParameter(name));
        }
    }

    @AfterReturning(returning="ret",pointcut="logPointCut()")
    public void doAfterReturning(Object ret)throws Throwable {
        //处理完请求，返回内容
        log.info("RESPONSE："+ret);
    }
}
