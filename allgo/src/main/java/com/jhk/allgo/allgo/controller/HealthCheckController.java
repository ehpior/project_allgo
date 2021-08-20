package com.jhk.allgo.allgo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HealthCheckController {
	
	@GetMapping("/ping")
	public ResponseEntity<String> healthCheck(){
		log.info("###health check");
		return ResponseEntity.ok("pong");
	}

}
