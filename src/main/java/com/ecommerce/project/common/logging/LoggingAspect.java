package com.ecommerce.project.common.logging;

import com.ecommerce.project.common.constants.RequestConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {
    String LOG_FORMAT = "%s | className=%s, method=%s";
    @Autowired
    private LogFormatter formatter;

    @Around("execution(* com.ecommerce.project.service..*(..))")

    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        // get method name , e.g. listStocks()
        String methodName = joinPoint.getSignature().getName();
        // get class name , e.g. StockService
        String target = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        String requestId = MDC.get(RequestConstants.REQUEST_ID);
        String httpMethod = MDC.get(RequestConstants.HTTP_METHOD);
        String requestPath = MDC.get(RequestConstants.REQUEST_PATH);

        log.info(formatter.logRequest(requestId,target,methodName,httpMethod,requestPath,startTime));

        try {
            // execute the original method logic
            Object result = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            // logging
            log.info(formatter.logResponse(requestId,target,methodName,httpMethod,requestPath,startTime,endTime));

            return result;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();

            log.error(formatter.logError(requestId,target,methodName,httpMethod,requestPath,startTime,endTime));

            throw e;
        }
    }

    @Around("execution(* com.ecommerce.project.repository..*(..))")
    public Object logRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String methodName = joinPoint.getSignature().getName();
        String target = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        String requestId = MDC.get(RequestConstants.REQUEST_ID);
        String httpMethod = MDC.get(RequestConstants.HTTP_METHOD);
        String requestPath = MDC.get(RequestConstants.REQUEST_PATH);
        log.info(formatter.logRequest(requestId,target,methodName,httpMethod,requestPath,startTime));

        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            log.info(formatter.logResponse(requestId,target,methodName,httpMethod,requestPath,startTime,endTime));

            return result;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();

            log.error(formatter.logError(requestId,target,methodName,httpMethod,requestPath,startTime,endTime));

            throw e;
        }
    }

    @Around("execution(* com.ecommerce.project.controller..*(..))")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String methodName = joinPoint.getSignature().getName();
        String target = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        String requestId = MDC.get(RequestConstants.REQUEST_ID);
        String httpMethod = MDC.get(RequestConstants.HTTP_METHOD);
        String requestPath = MDC.get(RequestConstants.REQUEST_PATH);
        log.info(formatter.logRequest(requestId,target,methodName,httpMethod,requestPath,startTime));

        try {
            // execute the original method logic
            Object result = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            // logging
            log.info(formatter.logResponse(requestId,target,methodName,httpMethod,requestPath,startTime,endTime));

            return result;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();

            log.error(formatter.logError(requestId,target,methodName,httpMethod,requestPath,startTime,endTime));

            throw e;
        }
    }

    }

