package com.jhk.allgo.portfolio.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhk.allgo.portfolio.exception.CommonConstraintViolationException;
import com.jhk.allgo.portfolio.exception.CommonNotFoundException;
import com.jhk.allgo.portfolio.model.dto.common.StocksDto;
import com.jhk.allgo.portfolio.service.PortfolioViewService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

	private final HashMap<String, String> tickPrice;
	
	private final PortfolioViewService portfolioViewService;
	
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	
	
	// 알고리즘 Type="A" 포트폴리오 편입
	@Scheduled(cron = "0 0 15 * * WED,FRI", zone = "Asia/Seoul")
	public void portfolioGenerateAlpha() {
		
		WebClient client = WebClient.create();
		
		List<String> holdingList = portfolioViewService.getCodeListByAllgoTypeAndStatus("A", "H");
		
		for(String hk : holdingList){
			System.out.println(hk);
		}
		
		String today = sdFormat.format(new Date());
		
		StocksDto buyStocksDto = client.post()
			.uri("http://localhost:8090/stock/allgo/portfolio/A/" + today)
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(holdingList)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(CommonNotFoundException::new))
			.onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(CommonConstraintViolationException::new))
			.bodyToMono(StocksDto.class)
			.onErrorResume(throwable -> {
			    return Mono.error(throwable);})
			.block();
		
		System.out.println("portfolioGenerateAlpha");
		System.out.println(buyStocksDto.toString());
	}

	// 알고리즘 Type="B" 포트폴리오 편입
	@Scheduled(cron = "0 30 9 * * MON-FRI", zone = "Asia/Seoul")
	public void portfolioGenerateBeta() {
		
		WebClient client = WebClient.create();
		
		List<String> holdingList = portfolioViewService.getCodeListByAllgoTypeAndStatus("B", "H");
		
		for(String hk : holdingList){
			System.out.println(hk);
		}
		
		String today = sdFormat.format(new Date());
		
		StocksDto buyStocksDto = client.post()
			.uri("http://localhost:8090/stock/allgo/portfolio/B/" + today)
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(holdingList)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve().bodyToMono(StocksDto.class).block();
		
		System.out.println("portfolioGenerateBeta");
		System.out.println(buyStocksDto.toString());
	}
	
	// 보유일 만료에 따른 청산 수행
	@Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
	public void clearByDate() {

		// stock에게 영업일 확인 (WebClient)

		// 기간만료 포트폴리오 확인 및 청산 수행
		// signal에게 매매신호 전송(Kafka)

	}

}
