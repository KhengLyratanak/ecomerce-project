package com.ecommerce.project.common.logging;


import com.ecommerce.project.common.constants.LoggingConstants;
import org.springframework.stereotype.Component;

@Component
public class LogFormatter {
    private static  final String LOG_FORMAT = "%s:%s | target=%s, method=%s, httpMethod=%s,requestPath=%s,startTime = %s,endTime=%s,executeTime=%sms";
    public String logRequest(String requestId, String target, String method, String httpMethod, String requestPath, Long startTime) {
        return String.format(LOG_FORMAT,
                LoggingConstants.REQUEST,
                requestId,
                target,
                method,
                httpMethod,
                requestPath,
                startTime,
                0,
                0
        );
    }
    public String logResponse (String requestId,String target,String method,String httpMethod,String requestPath,Long startTime,Long endTime){
        return String.format(LOG_FORMAT,
                LoggingConstants.RESPONSE,
                requestId,
                target,
                method,
                httpMethod,
                requestPath,
                startTime,
                endTime,
                endTime-startTime
        );
    }
    public String logError(String requestId,String target,String method,String httpMethod,String requestPath,Long startTime,Long endTime){
        return String.format(LOG_FORMAT,
                LoggingConstants.ERROR,
                requestId,
                target,
                method,
                httpMethod,
                requestPath,
                startTime,
                endTime,
                endTime-startTime
        );
    }
}

