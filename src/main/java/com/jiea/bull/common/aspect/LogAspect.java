package com.jiea.bull.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.jiea.bull.common.annotation.Log;
import com.jiea.bull.common.utils.IPUtils;
import com.jiea.bull.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 日志切面类
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.jiea.bull.common.annotation.Log)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 执行目标方法
        Object proceed = point.proceed();
        // 执行毫秒数
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        // 保存日志
        this.saveLog(point, elapsed);

        return proceed;
    }

    private void saveLog(ProceedingJoinPoint point, long time){
        com.jiea.bull.domain.Log log = new com.jiea.bull.domain.Log();

        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Log annotation = method.getAnnotation(Log.class);
        if(annotation != null){
            // 注解上的描述
            log.setOperation(annotation.value());
        }

        // 请求方法名
        String className = point.getTarget().getClass().getName();
        String methodName = method.getName();
        log.setMethod(className + "." + methodName + "()");

        // 请求参数
        Object[] args = point.getArgs();
        String argsJson = JSON.toJSONString(args[0]);
        // 将登录中的密码参数隐藏
        if(StringUtils.isNotBlank(argsJson) && StringUtils.contains(argsJson, "password")){
            JSONObject jsonObject = JSON.parseObject(argsJson);
            jsonObject.put("password", "**");
            argsJson = JSON.toJSONString(jsonObject);
        }
        log.setParams(argsJson);

        // IP
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.setIp(IPUtils.getIpAddr(request));

        // 用户名
        log.setUsername("ss");

        log.setTime(time);
        log.setCreateDate(new Date());

        // 保存日志
        logService.saveLog(log);
    }

}
