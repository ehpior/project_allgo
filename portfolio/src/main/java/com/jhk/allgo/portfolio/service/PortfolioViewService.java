package com.jhk.allgo.portfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.portfolio.exception.CommonNotFoundException;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioViewResponseDto;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioViewResponseListDto;
import com.jhk.allgo.portfolio.model.entity.PortfolioView;
import com.jhk.allgo.portfolio.repository.PortfolioViewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioViewService {
	
	private final PortfolioViewRepository portfolioViewRepository;
	
	/**
	 * 전체 포트폴리오 조회
	 * @return
	 */
	public ResponseEntity<PortfolioViewResponseListDto> findAll() {
        List<PortfolioView> list = portfolioViewRepository.findAll();
        
        return ResponseEntity.ok().body(PortfolioViewResponseListDto.builder()
                .portfolioViewResponseDtoList(list.stream().map(portfolioView ->
	                PortfolioViewResponseDto.builder()
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
		                .build())
	            	.collect(Collectors.toList()))
                .build());
    }
	
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
	 * 알고리즘 타입을 통한 포트폴리오 뷰 다중 조회
	 * @param allgo_type
	 * @return
	 */
	public ResponseEntity<PortfolioViewResponseListDto> findByAllgoType(String allgo_type) {
        List<PortfolioView> list = portfolioViewRepository.findByAllgoType(allgo_type);
        
        if(list.size() == 0){
        	throw new CommonNotFoundException();
        }
        
        return ResponseEntity.ok().body(PortfolioViewResponseListDto.builder()
                .portfolioViewResponseDtoList(list.stream().map(portfolioView ->
	                PortfolioViewResponseDto.builder()
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
		                .build())
                	.collect(Collectors.toList()))
                .build());
    }
	
	/**
	 * 알고리즘 타입 및 상태를 통한 포트폴리오 뷰 다중 조회
	 * @param allgo_type, status
	 * @return
	 */
	public ResponseEntity<PortfolioViewResponseListDto> findByAllgoTypeAndStatus(String allgo_type, String status) {
        List<PortfolioView> list = portfolioViewRepository.findByAllgoTypeAndStatus(allgo_type, status);
        
        if(list.size() == 0){
        	throw new CommonNotFoundException();
        }
        
        return ResponseEntity.ok().body(PortfolioViewResponseListDto.builder()
                .portfolioViewResponseDtoList(list.stream().map(portfolioView ->
	                PortfolioViewResponseDto.builder()
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
		                .build())
                	.collect(Collectors.toList()))
                .build());
    }
	
	/**
	 * 상태를 통한 포트폴리오 뷰 다중 조회
	 * @param status
	 * @return
	 */
	public ResponseEntity<PortfolioViewResponseListDto> findByStatus(String status) {
        List<PortfolioView> list = portfolioViewRepository.findByStatus(status);
        
        if(list.size() == 0){
        	throw new CommonNotFoundException();
        }
        
        return ResponseEntity.ok().body(PortfolioViewResponseListDto.builder()
                .portfolioViewResponseDtoList(list.stream().map(portfolioView ->
	                PortfolioViewResponseDto.builder()
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
		                .build())
                	.collect(Collectors.toList()))
                .build());
    }

}
