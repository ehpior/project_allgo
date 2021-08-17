package com.jhk.allgo.stock.component;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.TickChegDto;

@Configuration
public class TickChegComponent {
	
	@Bean
	public HashMap<String, TickChegDto> TickCheg(){
		System.out.println("tickCheg");
		return new HashMap<String, TickChegDto>();
	}
}
