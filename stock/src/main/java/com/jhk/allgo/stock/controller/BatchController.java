package com.jhk.allgo.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhk.allgo.stock.service.BusinessService;
import com.jhk.allgo.stock.service.ChegService;
import com.jhk.allgo.stock.service.ProgramService;
import com.jhk.allgo.stock.service.algorithm.AllgoAlphaService;
import com.jhk.allgo.stock.service.algorithm.AllgoBetaService;

import lombok.RequiredArgsConstructor;

//@Api(tags = "allgo")
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/batch")
public class BatchController {
	
	private final AllgoAlphaService allgoAlphaService;
	private final AllgoBetaService allgoBetaService;
	
	private final ChegService chegService;
	private final ProgramService programService;
	private final BusinessService businessService;
	
	@GetMapping("/business")
	public void businessSave(){
		businessService.insertBeanToDB();
	}
	
	@GetMapping("/cheg")
	public void chegSave(){
		chegService.insertBeanToDB();
	}
	
	@GetMapping("/program")
	public void programSave(){
		programService.insertBeanToDB();
	}
	
	@GetMapping("/score/{type}")
	public void scoreGenerate(@PathVariable("type") String type) {
		
		if("A".equals(type)){
			allgoAlphaService.scoreGenerate();
		} else if("B".equals(type)){
			allgoBetaService.scoreGenerate();
		}
    }
	
}
