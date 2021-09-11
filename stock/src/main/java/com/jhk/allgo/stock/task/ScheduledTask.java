package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	
	@Scheduled(cron = "0 40 15 * * *" , zone = "Asia/Seoul")
	public void dataSave(){
		
		if(!isBusinessDay()){
			return;
		}
		
		//tickCheg -> stock.cheg 저장
		//tickProgram -> stock.program 저
	}
	
	@Scheduled(cron = "0 0 16 * * *" , zone = "Asia/Seoul")
	public void scoreGenerate(){
		
		if(!isBusinessDay()){
			return;
		}
		
		//타입별 알고리즘을 통한 스코어 산출
		
	}
	
	private boolean isBusinessDay(){
		String today = sdFormat.format(new Date());
		String businessDate = sdFormat.format(businessBean.getDate());
		
		if(today.equals(businessDate)){
			return true;
		} else{
			return false;
		}
	}
	
	
}
