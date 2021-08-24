package com.jhk.allgo.allgo.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.allgo.model.dto.response.ScoreResponseListDto;
import com.jhk.allgo.allgo.service.ScoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/score", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScoreController {
	
	private final ScoreService scoreService;

    /*@GetMapping("/{date:[0-9]{8}}")*/
	@GetMapping("/{date}")
    public ResponseEntity<ScoreResponseListDto> findByDate(
    		@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") Date date){
    	
    	return scoreService.findByDate(date);
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
