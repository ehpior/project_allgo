package com.jhk.allgo.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(tags = "PING TEST")
@RequestMapping(value = "/${app.ver}")
public class HealthCheckController {
	
	@GetMapping("/ping")
	public ResponseEntity<String> healthCheck(){
		log.info("###health check");
		return ResponseEntity.ok("pong");
	}

}
