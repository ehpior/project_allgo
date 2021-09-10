package com.jhk.allgo.stock.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.ChegDto;

@Configuration
public class ChegBeanConfig {
	
	@Bean
	public HashMap<String, ChegDto> ChegBean(){
		return new HashMap<String, ChegDto>();
	}
	
}
