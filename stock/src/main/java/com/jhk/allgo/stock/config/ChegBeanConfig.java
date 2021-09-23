package com.jhk.allgo.stock.config;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhk.allgo.stock.model.dto.bean.ChegBeanDto;
import com.jhk.allgo.stock.model.entity.Cheg;
import com.jhk.allgo.stock.repository.ChegRepository;

@Configuration
public class ChegBeanConfig {
	
	@Autowired
	private ChegRepository chegRepository;
	
	@Autowired
	HashMap<String, ChegBeanDto> chegBean;
	
	@Bean
	public HashMap<String, ChegBeanDto> ChegBean(){
		return new HashMap<String, ChegBeanDto>();
	}
	
	@PostConstruct
	public void postConstruct(){
		List<Cheg> chegList = chegRepository.init();
		
		for(Cheg cheg : chegList){
			chegBean.put(cheg.getCode(), 
					ChegBeanDto.builder()
						.date(cheg.getDate())
						.code(cheg.getCode())
						.price(cheg.getPrice())
						.change_price(cheg.getChange_price())
						.increase_rate(cheg.getIncrease_rate())
						.volume(cheg.getVolume())
						.cul_volume(cheg.getCul_volume())
						.cul_amount(cheg.getCul_amount())
						.open(cheg.getOpen())
						.high(cheg.getHigh())
						.low(cheg.getLow())
						.turn_over(cheg.getTurn_over())
						.volume_power(cheg.getVolume_power())
						.capitalization(cheg.getCapitalization())
						.build()
			);
		}
		
	}
}
