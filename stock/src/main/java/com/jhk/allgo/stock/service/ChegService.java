package com.jhk.allgo.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.entity.Cheg;
import com.jhk.allgo.stock.repository.ChegRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChegService {
	
	private final ChegRepository chegRepository;
	private final HashMap<String, ChegDto> chegBean;
	
	public void insertBeanToDB(){
		
		List<Cheg> chegList = new ArrayList<Cheg>();
		
		chegBean.values().forEach((chegBeanDto) -> {
			chegList.add(Cheg.builder()
				.date(chegBeanDto.getDate())
				.code(chegBeanDto.getCode())
				.price(chegBeanDto.getPrice())
				.change_price(chegBeanDto.getChange_price())
				.increase_rate(chegBeanDto.getIncrease_rate())
				.volume(chegBeanDto.getVolume())
				.cul_volume(chegBeanDto.getCul_volume())
				.cul_amount(chegBeanDto.getCul_amount())
				.open(chegBeanDto.getOpen())
				.high(chegBeanDto.getHigh())
				.low(chegBeanDto.getLow())
				.turn_over(chegBeanDto.getTurn_over())
				.volume_power(chegBeanDto.getVolume_power())
				.capitalization(chegBeanDto.getCapitalization())
				.build()
			);
		});
		
		chegRepository.saveAll(chegList);
		
	}
	
	public void create(ChegDto chegDto) {
		Cheg newCheg = chegRepository.save(
			Cheg.builder()
				.date(chegDto.getDate())
				.code(chegDto.getCode())
				.price(chegDto.getPrice())
				.change_price(chegDto.getChange_price())
				.increase_rate(chegDto.getIncrease_rate())
				.volume(chegDto.getVolume())
				.cul_volume(chegDto.getCul_volume())
				.cul_amount(chegDto.getCul_amount())
				.open(chegDto.getOpen())
				.high(chegDto.getHigh())
				.low(chegDto.getLow())
				.turn_over(chegDto.getTurn_over())
				.volume_power(chegDto.getVolume_power())
				.capitalization(chegDto.getCapitalization())
				.build()
		);
        
    }
	
}
