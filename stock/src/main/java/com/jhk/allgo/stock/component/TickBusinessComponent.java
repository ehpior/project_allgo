package com.jhk.allgo.stock.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.TickBusinessDto;

@Configuration
public class TickBusinessComponent {
	
	@Bean
	public TickBusinessDto TickBusiness(){
		System.out.println("tickBusiness");
		return new TickBusinessDto();
	}
}