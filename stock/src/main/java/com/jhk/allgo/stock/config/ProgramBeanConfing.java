package com.jhk.allgo.stock.config;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.bean.ProgramBeanDto;
import com.jhk.allgo.stock.model.entity.Program;
import com.jhk.allgo.stock.repository.ProgramRepository;

@Configuration
public class ProgramBeanConfing {
	
	@Autowired 
	private ProgramRepository programRepository;
	
	@Autowired
	HashMap<String, ProgramBeanDto> programBean;
	
	@Bean
	public HashMap<String, ProgramBeanDto> ProgramBean(){
		return new HashMap<String, ProgramBeanDto>();
	}
	
	@PostConstruct
	public void postConstruct(){
		List<Program> programList = programRepository.init();
		
		for(Program program : programList){
			programBean.put(program.getCode(),
					ProgramBeanDto.builder()
						.date(program.getDate())
						.code(program.getCode())
						.sell_volume(program.getSell_volume())
						.sell_amount(program.getSell_amount())
						.buy_volume(program.getBuy_volume())
						.buy_amount(program.getBuy_amount())
						.net_buy_volume(program.getNet_buy_volume())
						.net_buy_amount(program.getNet_buy_amount())
						.build()
			);
		}
		
	}
	
}
