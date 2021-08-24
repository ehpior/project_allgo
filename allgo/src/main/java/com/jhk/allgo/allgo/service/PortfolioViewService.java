package com.jhk.allgo.allgo.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.allgo.exception.CommonNotFoundException;
import com.jhk.allgo.allgo.model.dto.response.PortfolioViewResponseDto;
import com.jhk.allgo.allgo.model.entity.PortfolioView;
import com.jhk.allgo.allgo.repository.PortfolioViewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioViewService {
	
	private final PortfolioViewRepository portfolioViewRepository;
	
	/**
	 * ID 통한 포트폴리오 뷰 단 건 조회
	 * @param id
	 * @return
	 */
	public ResponseEntity<PortfolioViewResponseDto> findById(Long portfolio_id) {
		Optional<PortfolioView> option = portfolioViewRepository.findById(portfolio_id);
		
		return option.map(portfolioView -> ResponseEntity
                .status(HttpStatus.OK)
                .body(PortfolioViewResponseDto.builder()
                        .portfolio_id(portfolioView.getPortfolioId())
                        .allgo_type(portfolioView.getAllgoType())
                        .stock_code(portfolioView.getStockCode())
                        .stock_name(portfolioView.getStockName())
                        .holding_day(portfolioView.getHoldingDay())
                        .average_buy_price(portfolioView.getAverageBuyPrice())
                        .average_sell_price(portfolioView.getAverageSellPrice())
                        .rate(portfolioView.getRate())
                        .first_buy_date(portfolioView.getFirstBuyDate())
                        .last_sell_date(portfolioView.getLastSellDate())
                        .status(portfolioView.getStatus())
                        .build()))
                .orElseThrow(CommonNotFoundException::new);
    }
	
	/**
	 * 알고리즘 타입을 통한 포트폴리오 다중 조회
	 * @param allgo_type
	 * @return
	 */
	/*public ResponseEntity<PortfolioViewResponseListDto> findByAllgoType(String allgo_type) {
        List<PortfolioView> list = portfolioViewRepository.findByAllgoType(allgo_type);
        
        return ResponseEntity.ok().body(PortfolioViewResponseListDto.builder()
                .portfolioViewResponseDtoList(list.stream().map(portfolioView ->
	                PortfolioViewResponseDto.builder()
		                .id(portfolioView.getId())
	                    .portfolio_id(portfolioView.getPortfolioId())
	                    .allgo_type(portfolioView.getAllgoType())
	                    .stock_code(portfolioView.getStockCode())
	                    .stock_name(portfolioView.getStockName())
	                    .date(portfolioView.getDate())
	                    .price(portfolioView.getPrice())
	                    .target_rate(portfolioView.getTargetRate())
	                    .loss_rate(portfolioView.getLossRate())
	                    .holding_day(portfolioView.getHoldingDay())
	                    .reason(portfolioView.getReason())
	                    .percent(portfolioView.getPercent())
	                    .type(portfolioView.getType())
	                    .status(portfolioView.getStatus())
	                    .create_time(portfolioView.getCreateTime())
	                    .update_time(portfolioView.getUpdateTime())
	                    .build())
                	.collect(Collectors.toList()))
                .build());
    }
	
	*//**
	 * 전체 포트폴리오 조회
	 * @return
	 *//*
	public ResponseEntity<PortfolioViewResponseListDto> findAll() {
        List<PortfolioView> list = portfolioViewRepository.findAll();
        
        return ResponseEntity.ok().body(PortfolioViewResponseListDto.builder()
                .portfolioViewResponseDtoList(list.stream().map(portfolioView ->
	                PortfolioViewResponseDto.builder()
		                .id(portfolioView.getId())
	                    .portfolio_id(portfolioView.getPortfolioId())
	                    .allgo_type(portfolioView.getAllgoType())
	                    .stock_code(portfolioView.getStockCode())
	                    .stock_name(portfolioView.getStockName())
	                    .date(portfolioView.getDate())
	                    .price(portfolioView.getPrice())
	                    .target_rate(portfolioView.getTargetRate())
	                    .loss_rate(portfolioView.getLossRate())
	                    .holding_day(portfolioView.getHoldingDay())
	                    .reason(portfolioView.getReason())
	                    .percent(portfolioView.getPercent())
	                    .type(portfolioView.getType())
	                    .status(portfolioView.getStatus())
	                    .create_time(portfolioView.getCreateTime())
	                    .update_time(portfolioView.getUpdateTime())
	                    .build())
	            	.collect(Collectors.toList()))
                .build());
    }
	
	public ResponseEntity<PortfolioViewResponseDto> create(PortfolioViewRequestDto request) {
        PortfolioView newProduct = portfolioViewRepository.save(
                PortfolioView.builder()
	                .id(request.getId())
	                .portfolioId(request.getPortfolio_id())
	                .allgoType(request.getAllgo_type())
	                .stockCode(request.getStock_code())
	                .stockName(request.getStock_name())
	                .date(request.getDate())
	                .price(request.getPrice())
	                .targetRate(request.getTarget_rate())
	                .lossRate(request.getLoss_rate())
	                .holdingDay(request.getHolding_day())
	                .reason(request.getReason())
	                .percent(request.getPercent())
	                .type(request.getType())
	                .status(request.getStatus())
	                .build());
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PortfolioViewResponseDto.builder()
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
	
	public ResponseEntity<PortfolioViewResponseDto> update(PortfolioViewRequestDto request) {
        Optional<PortfolioView> option = portfolioViewRepository.findById(request.getId());
        
        return option.map(portfolioView -> {
        	portfolioView.setPortfolioId(request.getPortfolio_id());
        	portfolioView.setAllgoType(request.getAllgo_type());
        	portfolioView.setStockCode(request.getStock_code());
        	portfolioView.setStockName(request.getStock_name());
        	portfolioView.setDate(request.getDate());
        	portfolioView.setPrice(request.getPrice());
        	portfolioView.setTargetRate(request.getTarget_rate());
        	portfolioView.setLossRate(request.getLoss_rate());
        	portfolioView.setHoldingDay(request.getHolding_day());
        	portfolioView.setReason(request.getReason());
        	portfolioView.setPercent(request.getPercent());
        	portfolioView.setType(request.getType());
        	portfolioView.setStatus(request.getStatus());
        	
        	portfolioViewRepository.save(portfolioView);
        	
        	return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(PortfolioViewResponseDto.builder()
                    		.id(portfolioView.getId())
    	                    .portfolio_id(portfolioView.getPortfolioId())
    	                    .allgo_type(portfolioView.getAllgoType())
    	                    .stock_code(portfolioView.getStockCode())
    	                    .stock_name(portfolioView.getStockName())
    	                    .date(portfolioView.getDate())
    	                    .price(portfolioView.getPrice())
    	                    .target_rate(portfolioView.getTargetRate())
    	                    .loss_rate(portfolioView.getLossRate())
    	                    .holding_day(portfolioView.getHoldingDay())
    	                    .reason(portfolioView.getReason())
    	                    .percent(portfolioView.getPercent())
    	                    .type(portfolioView.getType())
    	                    .status(portfolioView.getStatus())
    	                    .create_time(portfolioView.getCreateTime())
    	                    .update_time(portfolioView.getUpdateTime())
    	                    .build());
        	})
        		.orElseThrow(CommonNotFoundException::new);
          
    }
	
	public ResponseEntity<String> delete(Long id) {
        Optional<PortfolioView> option = portfolioViewRepository.findById(id);
        try {
            if (option.isPresent()) {
            	portfolioViewRepository.deleteById(option.get().getId());
                return ResponseEntity.ok().body("Successfully deleted");
            } else {
                throw new CommonNotFoundException();
            }
        } catch (DataIntegrityViolationException e) {
            throw new CommonConstraintViolationException();
        }

    }*/

}
