package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jhk.allgo.stock.model.dto.BusinessDto;
import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.dto.ProgramDto;
import com.jhk.allgo.stock.service.BusinessService;
import com.jhk.allgo.stock.service.ChegService;
import com.jhk.allgo.stock.service.ProgramService;
import com.jhk.allgo.stock.service.algorithm.AllgoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
	
	private final HashMap<String, ChegDto> chegBean;
	private final HashMap<String, ProgramDto> programBean;
	private final BusinessDto businessBean;
	
	private final ChegService chegService;
	private final ProgramService programService;
	private final BusinessService businessService;
	
	private final AllgoService allgoService; 
	
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	
	@Scheduled(cron = "0 40 15 * * *" , zone = "Asia/Seoul")
	public void dataSave(){
		
		if(!isBusinessDay()){
			return;
		}
		
		businessService.insertBeanToDB();
		chegService.insertBeanToDB();
		programService.insertBeanToDB();
	}
	
	@Scheduled(cron = "0 0 16 * * *" , zone = "Asia/Seoul")
	public void scoreGenerate(){
		
		if(!isBusinessDay()){
			return;
		}
		
		allgoService.allgoAlphaGenerate();
		allgoService.allgoBetaGenerate();
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
