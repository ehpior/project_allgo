package com.jhk.allgo.stock.task;

import java.util.HashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jhk.allgo.stock.model.dto.TickBusinessDto;
import com.jhk.allgo.stock.model.dto.TickChegDto;
import com.jhk.allgo.stock.model.dto.TickProgramDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
	
	private final HashMap<String, TickChegDto> tickCheg;
	
	private final HashMap<String, TickProgramDto> tickProgram;
	
	private final TickBusinessDto tickBusiness;
	
	@Scheduled(cron = "0 40 15 * * *" , zone = "Asia/Seoul")
	public void dataSave(){
		int status = tickBusiness.getState();
		
		if(status == 9){
			return;
		}
		
		//tickCheg -> stock.cheg 저장
		//tickProgram -> stock.program 저
	}
	
	@Scheduled(cron = "0 0 16 * * *" , zone = "Asia/Seoul")
	public void scoreGenerate(){
		int status = tickBusiness.getState();
		
		if(status == 9){
			return;
		}
		
		//타입별 알고리즘을 통한 스코어 산출
		
	}
	
	
}
