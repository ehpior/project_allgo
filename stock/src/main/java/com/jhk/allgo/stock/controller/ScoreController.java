package com.jhk.allgo.stock.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.stock.exception.CommonConstraintViolationException;
import com.jhk.allgo.stock.model.dto.request.ScoreRequestDto;
import com.jhk.allgo.stock.model.dto.response.ScoreResponseDto;
import com.jhk.allgo.stock.model.dto.response.ScoreResponseListDto;
import com.jhk.allgo.stock.model.entity.id.DateTypeCodePK;
import com.jhk.allgo.stock.service.ScoreService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "allgo.score")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/score", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScoreController {
	
	private final ScoreService scoreService;

    /*@GetMapping("/{date:[0-9]{8}}")*/
	@GetMapping("/type/{type}")
    public ResponseEntity<ScoreResponseListDto> findByDate(
    		@RequestParam("date") @Nullable @DateTimeFormat(pattern = "yyyyMMdd") Date date,
    		@RequestParam("code") @Nullable String code){
		
		if(date == null && code != null){
			return scoreService.findByDate(date);
		} else if(date != null && code == null){
			return scoreService.findByCode(code);
		} else{
			throw new CommonConstraintViolationException();
		}
		
    }
	
    @PostMapping("/date/{date}/type/{type}/code/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ScoreResponseDto> create(DateTypeCodePK dateTypeCode, @RequestBody ScoreRequestDto request){
        return scoreService.create(dateTypeCode, request);
    }

    @PutMapping("/date/{date}/type/{type}/code/{code}")
    public ResponseEntity<ScoreResponseDto> update(DateTypeCodePK dateTypeCode, @RequestBody ScoreRequestDto request){
        return scoreService.update(dateTypeCode, request);
    }

    @DeleteMapping("/date/{date}/type/{type}/code/{code}")
    public ResponseEntity<String> delete(DateTypeCodePK dateTypeCode){
        return scoreService.delete(dateTypeCode);
    }
    
    @DeleteMapping("/date/{date}/type/{type}")
    public ResponseEntity<String> deleteByDateAndType(DateTypeCodePK dateTypeCode){
        return scoreService.deleteByDateAndType(dateTypeCode);
    }
    
    @DeleteMapping("/date/{date}")
    public ResponseEntity<String> deleteByDate(DateTypeCodePK dateTypeCode){
        return scoreService.deleteByDate(dateTypeCode);
    }
    
}
