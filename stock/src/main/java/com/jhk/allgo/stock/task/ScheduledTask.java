package com.jhk.allgo.stock.task;

import java.util.HashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jhk.allgo.stock.model.dto.BusinessDto;
import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.dto.ProgramDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
	
	private final HashMap<String, ChegDto> chegBean;
	
	private final HashMap<String, ProgramDto> programBean;
	
	private final BusinessDto businessBean;
	
	@Scheduled(cron = "0 40 15 * * *" , zone = "Asia/Seoul")
	public void dataSave(){
		int status = businessBean.getState();
		
		if(status == 9){
			return;
		}
		
		//tickCheg -> stock.cheg 저장
		//tickProgram -> stock.program 저
	}
	
	@Scheduled(cron = "0 0 16 * * *" , zone = "Asia/Seoul")
	public void scoreGenerate(){
		int status = businessBean.getState();
		
		if(status == 9){
			return;
		}
		
		//타입별 알고리즘을 통한 스코어 산출
		
	}
	
	
}
