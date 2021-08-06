package com.jhk.allgo.portfolio.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.portfolio.model.dto.response.PortfolioResponseDto;
import com.jhk.allgo.portfolio.service.PortfolioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioController {
	
	private final PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioResponseDto> findById(@PathVariable("id") Long id){
    	return portfolioService.findById(id);
    }

/*    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto request){
        return productService.create(request);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponseDto> update(@RequestBody ProductRequestDto request){
        return productService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return productService.delete(id);
    }

    @GetMapping("")
    public ResponseEntity<ProductResponseListDto> readByIds(@RequestParam("ids") String productIds) {
        List<Long> productIdList = new ArrayList<>(Arrays.asList(productIds.split(",")))
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return productService.findByIds(productIdList);
    }*/
    
}
