package com.jhk.allgo.stock.component;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.TickProgramDto;

@Configuration
public class TickProgramComponent {

	@Bean
	public HashMap<String, TickProgramDto> TickProgram(){
		System.out.println("tickProgram");
		return new HashMap<String, TickProgramDto>();
	}
}
