package com.jhk.allgo.stock.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.exception.CommonNotFoundException;
import com.jhk.allgo.stock.model.dto.bean.BusinessBeanDto;
import com.jhk.allgo.stock.model.dto.bean.ChegBeanDto;
import com.jhk.allgo.stock.model.dto.bean.ProgramBeanDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	
	private final HashMap<String, ChegBeanDto> tickCheg;
	
	private final HashMap<String, ProgramBeanDto> tickProgram;
	
	private final BusinessBeanDto tickBusiness;
	
	public ResponseEntity<ChegBeanDto> findChegBycode(String code) {
		
		Optional<ChegBeanDto> option = Optional.ofNullable(tickCheg.get(code));
		
		return option.map(tickCheg -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickCheg))
                .orElseThrow(CommonNotFoundException::new);
		
        /*Optional<Portfolio> option = portfolioRepository.findById(id);
        return option.map(portfolio -> ResponseEntity
                .status(HttpStatus.OK)
                .body(PortfolioResponseDto.builder()
                        .id(portfolio.getId())
                        .description(portfolio.getDescription()).build()))
                .orElseThrow(PortfolioNotFoundException::new);*/
    }
	
	public ResponseEntity<ProgramBeanDto> findProgramBycode(String code) {
		
		Optional<ProgramBeanDto> option = Optional.ofNullable(tickProgram.get(code));
		
		return option.map(tickProgram -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickProgram))
                .orElseThrow(CommonNotFoundException::new);
	}
	
	public ResponseEntity<BusinessBeanDto> getBusiness() {
		
		Optional<BusinessBeanDto> option = Optional.ofNullable(tickBusiness);
		
		return option.map(tickCheg -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickCheg))
                .orElseThrow(CommonNotFoundException::new);
	}
	
	
	
}
