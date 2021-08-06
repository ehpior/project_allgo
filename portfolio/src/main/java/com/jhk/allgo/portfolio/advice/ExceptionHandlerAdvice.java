package com.jhk.allgo.portfolio.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jhk.allgo.portfolio.exception.PortfolioConstraintViolationException;
import com.jhk.allgo.portfolio.exception.PortfolioNotFoundException;
import com.jhk.allgo.portfolio.model.dto.common.ErrorDto;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(PortfolioNotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundException(PortfolioNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .msg("Portfolio Not Found")
                .build());
    }

    @ExceptionHandler(PortfolioConstraintViolationException.class)
    public ResponseEntity<ErrorDto> constraintViolationException(PortfolioConstraintViolationException e){
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .msg("Constraint Violation")
                .build());
    }
}
