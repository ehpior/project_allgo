package com.jhk.allgo.stock.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.bean.ProgramBeanDto;

@Configuration
public class ProgramBeanConfing {
	
	@Bean
	public HashMap<String, ProgramBeanDto> ProgramBean(){
		return new HashMap<String, ProgramBeanDto>();
	}
	
}
