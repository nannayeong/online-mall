package com.mall.nanna.common.exception.handler;

import com.mall.nanna.common.dto.response.ResponseErrorBody;
import com.mall.nanna.common.exception.NoSuchPageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(value = {NoSuchPageException.class})
    public ResponseEntity<ResponseErrorBody> noSuchPageExceptionHandler(NoSuchPageException noSuchPageException) {
        log.info("[noSuchPageExceptionHandler] noSuchPageException: {}", noSuchPageException.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseErrorBody.builder()
                        .errorMessage(noSuchPageException.getMessage())
                        .build());
    }
}
