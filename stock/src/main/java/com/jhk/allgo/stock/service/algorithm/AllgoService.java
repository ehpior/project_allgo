package com.jhk.allgo.stock.service.algorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.ChegDto;
import com.jhk.allgo.stock.model.dto.ProgramDto;
import com.jhk.allgo.stock.model.entity.Score;
import com.jhk.allgo.stock.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllgoService {

	private final HashMap<String, ChegDto> chegBean;
	private final HashMap<String, ProgramDto> programBean;
	
	private final ScoreService scoreService;
	
	private final String ALPHATYPE = "A";
	private final String BETATYPE = "B";

	public void allgoAlphaGenerate() {
		
		List<Score> scoreList = new ArrayList<Score>();

		chegBean.values().forEach((chegBeanDto) -> {

			double score = 0;

			Date date = chegBeanDto.getDate();
			String code = chegBeanDto.getCode();
			double close = chegBeanDto.getPrice();
			double open = chegBeanDto.getOpen();
			double high = chegBeanDto.getHigh();
			double low = chegBeanDto.getLow();
			double vp = chegBeanDto.getVolume_power();
			double increase_rate = chegBeanDto.getIncrease_rate();
			double today_increase_rate = (close - open) / open * 100;

			if (0 < vp && vp < 20) {
				vp = 20;
			} else if (180 < vp && vp <= 450) {
				vp = 180;
			}

			double var = (vp + 20) / 40;

			if (today_increase_rate < 0) {
				score = (-1 / (22 - 2 * var)) * Math.pow(today_increase_rate + 1 - var, 2) + 1 - 0.5 * var;
			} else if (0 <= today_increase_rate && today_increase_rate < 4) {
				score = (-1 / (9 + var)) * Math.pow(today_increase_rate - 3, 2) + 1 + Math.abs(-5 + 1.5 * var);
			} else {
				score = (-1 / (16 + var)) * Math.pow(today_increase_rate - 3, 2) + 1 + Math.abs(-5 + 1.5 * var);
			}

			if (vp < 15 || vp > 450) {
				score = -10;
			}

			if (today_increase_rate >= 0 && increase_rate < 0) {
				score *= 0.5;
			}

			if (high != low) {
				score = Math.pow(score * 0.5 * 1.5, Math.abs((close - open) / (high - low)));
			} else {
				score *= 0.5;
			}
			
			scoreList.add(Score.builder()
					.date(date)
					.type(ALPHATYPE)
					.code(code)
					.score(score)
					.build()
			);
		});
		
		scoreService.insertAll(scoreList);
	}
	
	public void allgoBetaGenerate() {
		
		List<Score> scoreList = new ArrayList<Score>();

		programBean.values().forEach((programBeanDto) -> {

			double score = 0;

			Date date = programBeanDto.getDate();
			String code = programBeanDto.getCode();
			int net_buy_amount = programBeanDto.getNet_buy_amount();
			int capitalization = chegBean.get(code).getCapitalization();
			
			score = net_buy_amount / (double) capitalization * 100;
			
			scoreList.add(Score.builder()
					.date(date)
					.type(BETATYPE)
					.code(code)
					.score(score)
					.build()
			);
		});
		
		scoreService.insertAll(scoreList);
	}
}
