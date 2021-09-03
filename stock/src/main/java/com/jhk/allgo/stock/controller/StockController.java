package com.jhk.allgo.stock.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.stock.model.dto.TickBusinessDto;
import com.jhk.allgo.stock.model.dto.TickChegDto;
import com.jhk.allgo.stock.model.dto.TickProgramDto;
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
    public ResponseEntity<TickChegDto> findChegBycode(@PathVariable("code") String code){
		
    	return stockService.findChegBycode(code);
    }
	
	@GetMapping("/program/{code}")
    public ResponseEntity<TickProgramDto> findProgramBycode(@PathVariable("code") String code){
		
    	return stockService.findProgramBycode(code);
    }
	
	@GetMapping("/business")
    public ResponseEntity<TickBusinessDto> getBusiness(){
		
    	return stockService.getBusiness();
    }
	
}
