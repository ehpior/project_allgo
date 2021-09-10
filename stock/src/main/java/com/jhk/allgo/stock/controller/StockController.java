package com.jhk.allgo.stock.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.stock.model.dto.BusinessDto;
import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.dto.ProgramDto;
import com.jhk.allgo.stock.service.StockService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "stock")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {
	
	private final StockService stockService;
	
	@GetMapping("/cheg/{code}")
    public ResponseEntity<ChegDto> findChegBycode(@PathVariable("code") String code){
		
    	return stockService.findChegBycode(code);
    }
	
	@GetMapping("/program/{code}")
    public ResponseEntity<ProgramDto> findProgramBycode(@PathVariable("code") String code){
		
    	return stockService.findProgramBycode(code);
    }
	
	@GetMapping("/business")
    public ResponseEntity<BusinessDto> getBusiness(){
		
    	return stockService.getBusiness();
    }
	
}
