package com.jhk.allgo.stock.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.exception.CommonNotFoundException;
import com.jhk.allgo.stock.model.dto.BusinessDto;
import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.dto.ProgramDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	
	private final HashMap<String, ChegDto> tickCheg;
	
	private final HashMap<String, ProgramDto> tickProgram;
	
	private final BusinessDto tickBusiness;
	
	public ResponseEntity<ChegDto> findChegBycode(String code) {
		
		Optional<ChegDto> option = Optional.ofNullable(tickCheg.get(code));
		
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
	
	public ResponseEntity<ProgramDto> findProgramBycode(String code) {
		
		Optional<ProgramDto> option = Optional.ofNullable(tickProgram.get(code));
		
		return option.map(tickProgram -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickProgram))
                .orElseThrow(CommonNotFoundException::new);
	}
	
	public ResponseEntity<BusinessDto> getBusiness() {
		
		Optional<BusinessDto> option = Optional.ofNullable(tickBusiness);
		
		return option.map(tickCheg -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickCheg))
                .orElseThrow(CommonNotFoundException::new);
	}
	
	
	
	
	
	
	
}
