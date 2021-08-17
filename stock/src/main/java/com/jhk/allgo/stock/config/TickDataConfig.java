package com.jhk.allgo.stock.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.TickBusinessDto;
import com.jhk.allgo.stock.model.dto.TickChegDto;
import com.jhk.allgo.stock.model.dto.TickProgramDto;

@Configuration
public class TickDataConfig {
	
	@Bean
	public HashMap<String, TickChegDto> TickCheg(){
		System.out.println("tickCheg");
		return new HashMap<String, TickChegDto>();
	}
	
	@Bean
	public HashMap<String, TickProgramDto> TickProgram(){
		System.out.println("tickProgram");
		return new HashMap<String, TickProgramDto>();
	}
	
	@Bean
	public TickBusinessDto TickBusiness(){
		System.out.println("tickBusiness");
		return new TickBusinessDto();
	}
}
