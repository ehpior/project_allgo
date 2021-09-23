package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jhk.allgo.stock.model.dto.bean.BusinessBeanDto;
import com.jhk.allgo.stock.model.dto.bean.ChegBeanDto;
import com.jhk.allgo.stock.model.dto.bean.ProgramBeanDto;
import com.jhk.allgo.stock.service.BusinessService;
import com.jhk.allgo.stock.service.ChegService;
import com.jhk.allgo.stock.service.ProgramService;
import com.jhk.allgo.stock.service.algorithm.AllgoAlphaService;
import com.jhk.allgo.stock.service.algorithm.AllgoBetaService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
	
	private final HashMap<String, ChegBeanDto> chegBean;
	private final HashMap<String, ProgramBeanDto> programBean;
	private final BusinessBeanDto businessBean;
	
	private final ChegService chegService;
	private final ProgramService programService;
	private final BusinessService businessService;
	
	private final AllgoAlphaService allgoAlphaService; 
	private final AllgoBetaService allgoBetaService; 
	
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
		
		allgoAlphaService.scoreGenerate();
		allgoBetaService.scoreGenerate();
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
	
	
	
	public void dataSaveTest(){
		businessService.insertBeanToDB();
		chegService.insertBeanToDB();
		programService.insertBeanToDB();
	}
	
	public void scoreGenerateTest(){
		allgoAlphaService.scoreGenerate();
		allgoBetaService.scoreGenerate();
	}
	
	
}
