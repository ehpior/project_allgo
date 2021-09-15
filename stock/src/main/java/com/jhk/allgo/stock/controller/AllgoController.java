package com.jhk.allgo.stock.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.stock.exception.CommonConstraintViolationException;
import com.jhk.allgo.stock.model.dto.response.StocksResponseDto;
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
	
	@GetMapping("/portfolio/{type}/{date}")
	public ResponseEntity<StocksResponseDto> portfolioGenerate(
			@PathVariable("type") String type,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") Date date,
			@RequestBody @Nullable List<String> holdingList) {
		
		if("A".equals(type)){
			return allgoAlphaService.portfolioGenerate(date, holdingList);
		} else if("B".equals(type)){
			return allgoBetaService.portfolioGenerate(date, holdingList);
		} else{
			throw new CommonConstraintViolationException();
		}
    }
	
}
