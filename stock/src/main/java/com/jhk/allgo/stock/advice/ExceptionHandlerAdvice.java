package com.jhk.allgo.stock.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jhk.allgo.stock.exception.CommonNotFoundException;
import com.jhk.allgo.stock.model.dto.common.ErrorDto;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(CommonNotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundException(CommonNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .msg("Not Found")
                .build());
    }

    /*@ExceptionHandler(PortfolioConstraintViolationException.class)
    public ResponseEntity<ErrorDto> constraintViolationException(PortfolioConstraintViolationException e){
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .msg("Constraint Violation")
                .build());
    }*/

}
