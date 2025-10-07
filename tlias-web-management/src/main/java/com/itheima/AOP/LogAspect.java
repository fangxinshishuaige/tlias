package com.itheima.AOP;

import com.itheima.mapper.LogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class LogAspect {

    @Autowired
    HttpServletRequest request;
    @Autowired
    LogMapper logMapper;

    @Around("@annotation(com.itheima.AOP.MyLog)") //好像会匹配到登陆log。而登陆log时还没token。故会出错。
    public Object writeLog(ProceedingJoinPoint pjp) throws Throwable {
        long time1 = System.currentTimeMillis();
        Object res = pjp.proceed();
        long time2 = System.currentTimeMillis();
        long time = time2 - time1;
        log.info("执行时间为" + time +"ms"); //""+1 -> 转字符串最方便的写法
        String token = request.getHeader("token");
        log.info("-----logtoken---"+token);
        if (token == null || token.length() == 0){
            return res;
        }
        Claims claim = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claim.get("id");
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        //String methodParams = pjp.getArgs().toString();//只能得到哈希值。没有重写toString方法。
        String methodParams = Arrays.toString(pjp.getArgs());
        String returnValue = res.getClass().toString();
        OperateLog ol = new OperateLog(null, operateUser, LocalDateTime.now(),className,methodName,methodParams,returnValue,time);
        log.info(ol.toString());
        logMapper.insertLog(ol);
        return res;
    }
}
