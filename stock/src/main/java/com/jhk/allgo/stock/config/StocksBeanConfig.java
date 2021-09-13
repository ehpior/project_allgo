package com.jhk.allgo.stock.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.bean.StocksBeanDto;

@Configuration
public class StocksBeanConfig {
	
	@Bean
	public HashMap<String, StocksBeanDto> StocksBean(){
		return new HashMap<String, StocksBeanDto>();
	}
	
}
