package com.jhk.allgo.portfolio.task;

import java.util.HashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

	private final HashMap<String, String> tickPrice;

	// 알고리즘 Type="B" 포트폴리오 편입
	@Scheduled(cron = "0 30 9 * * *", zone = "Asia/Seoul")
	public void dataSave() {

		// stock에게 편입 리스트 전달(WebClient)
		// 영업일이 아니다 or 편입 종목 수신

		// 포트폴리오 편입내용 저장
		// signal에게 매매신호 전송(Kafka)

	}

	// 알고리즘 Type=A" 포트폴리오 편입
	@Scheduled(cron = "0 0 10 * * *", zone = "Asia/Seoul")
	public void scoreGenerate() {

		// stock에게 편입 리스트 전달(WebClient)
		// 영업일이 아니다 or 편입 종목 수신

		// 포트폴리오 편입내용 저장
		// signal에게 매매신호 전송(Kafka)

	}

	// 보유일 만료에 따른 청산 수행
	@Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
	public void clearByDate() {

		// stock에게 영업일 확인 (WebClient)

		// 기간만료 포트폴리오 확인 및 청산 수행
		// signal에게 매매신호 전송(Kafka)

	}

}
