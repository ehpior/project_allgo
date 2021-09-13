package com.jhk.allgo.stock.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.stock.exception.CommonConstraintViolationException;
import com.jhk.allgo.stock.service.algorithm.AllgoAlphaService;
import com.jhk.allgo.stock.service.algorithm.AllgoBetaService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "allgo")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/allgo", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllgoController {
	
	private final AllgoAlphaService allgoAlphaService;
	private final AllgoBetaService allgoBetaService;

	@GetMapping("/{type}")
    public ResponseEntity<String> findBytype(@RequestParam("type") String type, @RequestBody List<String> holdingList){
		
		if("A".equals(type)){
			return allgoAlphaService.portfolioGenerate(holdingList);
		} else if("B".equals(type)){
			return allgoBetaService.portfolioGenerate(holdingList);
		} else{
			throw new CommonConstraintViolationException();
		}
		
    }
	
}
