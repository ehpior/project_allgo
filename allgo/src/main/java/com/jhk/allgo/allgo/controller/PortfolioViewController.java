package com.jhk.allgo.allgo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.allgo.model.dto.response.PortfolioViewResponseDto;
import com.jhk.allgo.allgo.model.dto.response.PortfolioViewResponseListDto;
import com.jhk.allgo.allgo.service.PortfolioViewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/portfolio-view", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioViewController {
	
	private final PortfolioViewService portfolioViewService;

	@GetMapping
	public ResponseEntity<PortfolioViewResponseListDto> findAll(){
		return portfolioViewService.findAll();
	}
	
	@GetMapping("/{portfolio_id}")
    public ResponseEntity<PortfolioViewResponseDto> findById(@PathVariable("portfolio_id") Long portfolio_id){
    	return portfolioViewService.findById(portfolio_id);
    }
	
	@GetMapping("/allgo-type/{allgo_type}")
    public ResponseEntity<PortfolioViewResponseListDto> findByAllgoType(@PathVariable("allgo_type") String allgo_type){
    	return portfolioViewService.findByAllgoType(allgo_type);
    }
	
	@GetMapping("/allgo-type/{allgo_type}/status/{status}")
    public ResponseEntity<PortfolioViewResponseListDto> findByAllgoTypeAndStatus(
    		@PathVariable("allgo_type") String allgo_type, @PathVariable("status") String status){
    	return portfolioViewService.findByAllgoTypeAndStatus(allgo_type, status);
    }
	
	@GetMapping("/status/{status}")
    public ResponseEntity<PortfolioViewResponseListDto> findByStatus(@PathVariable("status") String status){
    	return portfolioViewService.findByStatus(status);
    }
    
}
