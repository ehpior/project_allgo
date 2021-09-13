package com.jhk.allgo.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.bean.BusinessBeanDto;

@Configuration
public class BusinessBeanConfig {
	
	@Bean
	public BusinessBeanDto BusinessBean(){
		return new BusinessBeanDto();
	}
}
