package com.jhk.allgo.stock.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.exception.CommonNotFoundException;
import com.jhk.allgo.stock.model.dto.TickBusinessDto;
import com.jhk.allgo.stock.model.dto.TickChegDto;
import com.jhk.allgo.stock.model.dto.TickProgramDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	
	private final HashMap<String, TickChegDto> tickCheg;
	
	private final HashMap<String, TickProgramDto> tickProgram;
	
	private final TickBusinessDto tickBusiness;
	
	public ResponseEntity<TickChegDto> findChegBycode(String code) {
		
		Optional<TickChegDto> option = Optional.ofNullable(tickCheg.get(code));
		
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
	
	public ResponseEntity<TickProgramDto> findProgramBycode(String code) {
		
		Optional<TickProgramDto> option = Optional.ofNullable(tickProgram.get(code));
		
		return option.map(tickProgram -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickProgram))
                .orElseThrow(CommonNotFoundException::new);
	}
	
	public ResponseEntity<TickBusinessDto> getBusiness() {
		
		Optional<TickBusinessDto> option = Optional.ofNullable(tickBusiness);
		
		return option.map(tickCheg -> ResponseEntity
                .status(HttpStatus.OK)
                .body(tickCheg))
                .orElseThrow(CommonNotFoundException::new);
	}
	
	
	
	
	
	
	
}
