package com.jiea.bull.common.exception;

import com.jiea.bull.vo.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BullExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BullExceptionHandler.class);

    @ExceptionHandler(BullException.class)
    public Resp handleBullException(BullException e){
        return Resp.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Resp handleIllegalArgumentException(IllegalArgumentException e){
        LOG.error(e.getMessage(), e);
        return Resp.error(1000, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Resp handleException(Exception e){
        LOG.error(e.getMessage(), e);
        return Resp.error();
    }

}
