package com.jhk.allgo.stock.service;

import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.bean.StocksBeanDto;
import com.jhk.allgo.stock.model.entity.Stocks;
import com.jhk.allgo.stock.repository.StocksRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StocksService {
	
	private final StocksRepository stocksRepository;
	
	public void create(StocksBeanDto stocksDto) {
        Stocks newStock = stocksRepository.save(
                Stocks.builder()
                	.date(stocksDto.getDate())
                	.code(stocksDto.getCode())
                	.name_kor(stocksDto.getName_kor())
                	.market(stocksDto.getMarket())
                	.build());
        
    }
	
}