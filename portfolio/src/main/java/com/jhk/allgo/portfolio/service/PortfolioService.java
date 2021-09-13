package com.jhk.allgo.portfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.portfolio.exception.CommonConstraintViolationException;
import com.jhk.allgo.portfolio.exception.CommonNotFoundException;
import com.jhk.allgo.portfolio.model.dto.request.PortfolioRequestDto;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioResponseDto;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioResponseListDto;
import com.jhk.allgo.portfolio.model.entity.Portfolio;
import com.jhk.allgo.portfolio.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioService {
	
	private final PortfolioRepository portfolioRepository;
	
	/**
	 * 전체 포트폴리오 조회
	 * @return
	 */
	public ResponseEntity<PortfolioResponseListDto> findAll() {
        List<Portfolio> list = portfolioRepository.findAll();
        
        return ResponseEntity.ok().body(PortfolioResponseListDto.builder()
                .portfolioResponseDtoList(list.stream().map(portfolio ->
	                PortfolioResponseDto.builder()
		                .id(portfolio.getId())
	                    .portfolio_id(portfolio.getPortfolioId())
	                    .allgo_type(portfolio.getAllgoType())
	                    .stock_code(portfolio.getStockCode())
	                    .stock_name(portfolio.getStockName())
	                    .date(portfolio.getDate())
	                    .price(portfolio.getPrice())
	                    .target_rate(portfolio.getTargetRate())
	                    .loss_rate(portfolio.getLossRate())
	                    .holding_day(portfolio.getHoldingDay())
	                    .reason(portfolio.getReason())
	                    .percent(portfolio.getPercent())
	                    .type(portfolio.getType())
	                    .status(portfolio.getStatus())
	                    .create_time(portfolio.getCreateTime())
	                    .update_time(portfolio.getUpdateTime())
	                    .build())
	            	.collect(Collectors.toList()))
                .build());
    }
	
	/**
	 * ID 통한 포트폴리오 단 건 조회
	 * @param id
	 * @return
	 */
	public ResponseEntity<PortfolioResponseDto> findById(Long id) {
		Optional<Portfolio> option = portfolioRepository.findById(id);
		
		return option.map(portfolio -> ResponseEntity
                .status(HttpStatus.OK)
                .body(PortfolioResponseDto.builder()
                        .id(portfolio.getId())
                        .portfolio_id(portfolio.getPortfolioId())
                        .allgo_type(portfolio.getAllgoType())
                        .stock_code(portfolio.getStockCode())
                        .stock_name(portfolio.getStockName())
                        .date(portfolio.getDate())
                        .price(portfolio.getPrice())
                        .target_rate(portfolio.getTargetRate())
                        .loss_rate(portfolio.getLossRate())
                        .holding_day(portfolio.getHoldingDay())
                        .reason(portfolio.getReason())
                        .percent(portfolio.getPercent())
                        .type(portfolio.getType())
                        .status(portfolio.getStatus())
                        .create_time(portfolio.getCreateTime())
                        .update_time(portfolio.getUpdateTime())
                        .build()))
                .orElseThrow(CommonNotFoundException::new);
    }
	
	/**
	 * 알고리즘 타입을 통한 포트폴리오 다중 조회
	 * @param allgo_type
	 * @return
	 */
	public ResponseEntity<PortfolioResponseListDto> findByAllgoType(String allgo_type) {
        List<Portfolio> list = portfolioRepository.findByAllgoType(allgo_type);
        
        if(list.size() == 0){
        	throw new CommonNotFoundException();
        }
        
        return ResponseEntity.ok().body(PortfolioResponseListDto.builder()
                .portfolioResponseDtoList(list.stream().map(portfolio ->
	                PortfolioResponseDto.builder()
		                .id(portfolio.getId())
	                    .portfolio_id(portfolio.getPortfolioId())
	                    .allgo_type(portfolio.getAllgoType())
	                    .stock_code(portfolio.getStockCode())
	                    .stock_name(portfolio.getStockName())
	                    .date(portfolio.getDate())
	                    .price(portfolio.getPrice())
	                    .target_rate(portfolio.getTargetRate())
	                    .loss_rate(portfolio.getLossRate())
	                    .holding_day(portfolio.getHoldingDay())
	                    .reason(portfolio.getReason())
	                    .percent(portfolio.getPercent())
	                    .type(portfolio.getType())
	                    .status(portfolio.getStatus())
	                    .create_time(portfolio.getCreateTime())
	                    .update_time(portfolio.getUpdateTime())
	                    .build())
                	.collect(Collectors.toList()))
                .build());
    }
	
	public ResponseEntity<PortfolioResponseDto> create(PortfolioRequestDto portfolio) {
        Portfolio newProduct = portfolioRepository.save(
                Portfolio.builder()
	                .portfolioId(portfolio.getPortfolio_id())
	                .allgoType(portfolio.getAllgo_type())
	                .stockCode(portfolio.getStock_code())
	                .stockName(portfolio.getStock_name())
	                .date(portfolio.getDate())
	                .price(portfolio.getPrice())
	                .targetRate(portfolio.getTarget_rate())
	                .lossRate(portfolio.getLoss_rate())
	                .holdingDay(portfolio.getHolding_day())
	                .reason(portfolio.getReason())
	                .percent(portfolio.getPercent())
	                .type(portfolio.getType())
	                .status(portfolio.getStatus())
	                .build());
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PortfolioResponseDto.builder()
                		.id(newProduct.getId())
	                    .portfolio_id(newProduct.getPortfolioId())
	                    .allgo_type(newProduct.getAllgoType())
	                    .stock_code(newProduct.getStockCode())
	                    .stock_name(newProduct.getStockName())
	                    .date(newProduct.getDate())
	                    .price(newProduct.getPrice())
	                    .target_rate(newProduct.getTargetRate())
	                    .loss_rate(newProduct.getLossRate())
	                    .holding_day(newProduct.getHoldingDay())
	                    .reason(newProduct.getReason())
	                    .percent(newProduct.getPercent())
	                    .type(newProduct.getType())
	                    .status(newProduct.getStatus())
	                    .create_time(newProduct.getCreateTime())
	                    .update_time(newProduct.getUpdateTime())
	                    .build()
                );
    }
	
	public ResponseEntity<PortfolioResponseDto> update(PortfolioRequestDto request, Long id) {
        Optional<Portfolio> option = portfolioRepository.findById(id);
        
        return option.map(portfolio -> {
        	portfolio.setPortfolioId(request.getPortfolio_id());
        	portfolio.setAllgoType(request.getAllgo_type());
        	portfolio.setStockCode(request.getStock_code());
        	portfolio.setStockName(request.getStock_name());
        	portfolio.setDate(request.getDate());
        	portfolio.setPrice(request.getPrice());
        	portfolio.setTargetRate(request.getTarget_rate());
        	portfolio.setLossRate(request.getLoss_rate());
        	portfolio.setHoldingDay(request.getHolding_day());
        	portfolio.setReason(request.getReason());
        	portfolio.setPercent(request.getPercent());
        	portfolio.setType(request.getType());
        	portfolio.setStatus(request.getStatus());
        	
        	Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        	
        	return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(PortfolioResponseDto.builder()
                    		.id(savedPortfolio.getId())
    	                    .portfolio_id(savedPortfolio.getPortfolioId())
    	                    .allgo_type(savedPortfolio.getAllgoType())
    	                    .stock_code(savedPortfolio.getStockCode())
    	                    .stock_name(savedPortfolio.getStockName())
    	                    .date(savedPortfolio.getDate())
    	                    .price(savedPortfolio.getPrice())
    	                    .target_rate(savedPortfolio.getTargetRate())
    	                    .loss_rate(savedPortfolio.getLossRate())
    	                    .holding_day(savedPortfolio.getHoldingDay())
    	                    .reason(savedPortfolio.getReason())
    	                    .percent(savedPortfolio.getPercent())
    	                    .type(savedPortfolio.getType())
    	                    .status(savedPortfolio.getStatus())
    	                    .create_time(savedPortfolio.getCreateTime())
    	                    .update_time(savedPortfolio.getUpdateTime())
    	                    .build());
        	})
        		.orElseThrow(CommonNotFoundException::new);
          
    }
	
	public ResponseEntity<String> delete(Long id) {
        Optional<Portfolio> option = portfolioRepository.findById(id);
        try {
            if (option.isPresent()) {
                portfolioRepository.deleteById(option.get().getId());
                return ResponseEntity.ok().body("Successfully deleted");
            } else {
                throw new CommonNotFoundException();
            }
        } catch (DataIntegrityViolationException e) {
            throw new CommonConstraintViolationException();
        }

    }

}
