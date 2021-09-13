package com.jhk.allgo.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.portfolio.model.dto.request.PortfolioRequestDto;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioResponseDto;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioResponseListDto;
import com.jhk.allgo.portfolio.service.PortfolioService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "allgo.portfolio")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioController {
	
	private final PortfolioService portfolioService;

	@GetMapping
	public ResponseEntity<PortfolioResponseListDto> findAll(){
		return portfolioService.findAll();
	}
	
    @GetMapping("/{portfolio_id}")
    public ResponseEntity<PortfolioResponseDto> findById(@PathVariable("portfolio_id") Long portfolio_id){
    	return portfolioService.findById(portfolio_id);
    }
    
    @GetMapping("/allgo-type/{allgo_type}")
    public ResponseEntity<PortfolioResponseListDto> findByAllgoType(@PathVariable("allgo_type") String allgo_type){
    	return portfolioService.findByAllgoType(allgo_type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PortfolioResponseDto> create(@RequestBody PortfolioRequestDto request){
        return portfolioService.create(request);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PortfolioResponseDto> update(
    		@RequestBody PortfolioRequestDto request, @PathVariable("id") Long id){
        return portfolioService.update(request, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return portfolioService.delete(id);
    }
    
    
}
