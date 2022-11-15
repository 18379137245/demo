package ding.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice //切面类
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public boolean handleDuplicateEntry(RuntimeException ex) {
        log.error("出现MySQL约束异常", ex);
        return false;
    }

}
