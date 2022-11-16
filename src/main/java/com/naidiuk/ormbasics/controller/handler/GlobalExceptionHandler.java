package com.naidiuk.ormbasics.controller.handler;

import com.naidiuk.ormbasics.controller.response.ErrorResponse;
import com.naidiuk.ormbasics.error.GroupNotFoundException;
import com.naidiuk.ormbasics.error.StudentNotFoundException;
import com.naidiuk.ormbasics.error.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            StudentNotFoundException.class,
            GroupNotFoundException.class,
            TeacherNotFoundException.class
    })
    public ResponseEntity<?> handle(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
