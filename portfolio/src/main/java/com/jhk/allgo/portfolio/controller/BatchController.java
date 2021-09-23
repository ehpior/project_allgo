package com.jhk.allgo.portfolio.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhk.allgo.portfolio.task.ScheduledTask;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "allgo.portfolio")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${app.ver}/batch", produces = MediaType.APPLICATION_JSON_VALUE)
public class BatchController {
	
	private final ScheduledTask taskhk;
	
	@GetMapping("/A")
	public void testA(){
		taskhk.portfolioGenerateAlpha();
	}
	@GetMapping("/B")
	public void testB(){
		taskhk.portfolioGenerateBeta();
	}
	
}
