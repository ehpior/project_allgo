package com.jhk.allgo.portfolio.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceBeanConfig {
	
	@Bean
	public HashMap<String, Integer> PriceBean(){
		return new HashMap<String, Integer>();
	}
	
}
