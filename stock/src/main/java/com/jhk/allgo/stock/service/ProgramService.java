package com.jhk.allgo.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.dto.ProgramDto;
import com.jhk.allgo.stock.model.entity.Cheg;
import com.jhk.allgo.stock.model.entity.Program;
import com.jhk.allgo.stock.repository.ProgramRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramService {
	
	private final ProgramRepository programRepository;
	private final HashMap<String, ProgramDto> programBean;
	
public void insertBeanToDB(){
		
		List<Program> programList = new ArrayList<Program>();
		
		programBean.values().forEach((programBeanDto) -> {
			programList.add(Program.builder()
				.date(programBeanDto.getDate())
				.code(programBeanDto.getCode())
				.sell_volume(programBeanDto.getSell_volume())
				.sell_amount(programBeanDto.getSell_amount())
				.buy_volume(programBeanDto.getBuy_volume())
				.buy_amount(programBeanDto.getBuy_amount())
				.net_buy_volume(programBeanDto.getNet_buy_volume())
				.net_buy_amount(programBeanDto.getNet_buy_amount())
				.build()
			);
		});
		
		programRepository.saveAll(programList);
		
	}
	
	public void create(ProgramDto programDto) {
		Program newProgram = programRepository.save(
			Program.builder()
				.date(programDto.getDate())
				.code(programDto.getCode())
				.sell_volume(programDto.getSell_volume())
				.sell_amount(programDto.getSell_amount())
				.buy_volume(programDto.getBuy_volume())
				.buy_amount(programDto.getBuy_amount())
				.net_buy_volume(programDto.getNet_buy_volume())
				.net_buy_amount(programDto.getNet_buy_amount())
				.build()
		);
        
    }
	
}
