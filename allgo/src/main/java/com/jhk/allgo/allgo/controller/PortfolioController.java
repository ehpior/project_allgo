package com.jhk.allgo.allgo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.allgo.model.dto.response.PortfolioResponseDto;
import com.jhk.allgo.allgo.model.dto.response.PortfolioResponseListDto;
import com.jhk.allgo.allgo.service.PortfolioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioController {
	
	private final PortfolioService portfolioService;
	
	/*@GetMapping
    public ResponseEntity<PortfolioResponseListDto> findByIds(@RequestParam("ids") @Nullable String portfolioIds) {
    	
    	if(portfolioIds == null){
    		return portfolioService.findAll();
    	}
    	
        List<Long> portfolioIdList = new ArrayList<>(Arrays.asList(portfolioIds.split(",")))
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        
        return portfolioService.findByIds(portfolioIdList);
    }*/

    @GetMapping("/{portfolio_id}")
    public ResponseEntity<PortfolioResponseDto> findById(@PathVariable("portfolio_id") Long portfolio_id){
    	return portfolioService.findById(portfolio_id);
    }
    
    @GetMapping("/allgo-type/{allgo_type}")
    public ResponseEntity<PortfolioResponseListDto> findById(@PathVariable("allgo_type") String allgo_type){
    	return portfolioService.findByAllgoType(allgo_type);
    }
    
    @GetMapping
    public ResponseEntity<PortfolioResponseListDto> findAll(){
    	return portfolioService.findAll();
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PortfolioResponseDto> create(@RequestBody PortfolioRequestDto request){
        return portfolioService.create(request);
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PortfolioResponseDto> update(@RequestBody PortfolioRequestDto request){
        return portfolioService.update(request);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return portfolioService.delete(id);
    }*/
    
    
}
