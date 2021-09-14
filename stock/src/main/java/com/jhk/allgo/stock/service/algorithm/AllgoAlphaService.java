package com.jhk.allgo.stock.service.algorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.exception.CommonNotFoundException;
import com.jhk.allgo.stock.model.dto.bean.ChegBeanDto;
import com.jhk.allgo.stock.model.dto.bean.ProgramBeanDto;
import com.jhk.allgo.stock.model.dto.response.StocksResponseDto;
import com.jhk.allgo.stock.model.entity.Score;
import com.jhk.allgo.stock.model.entity.Stocks;
import com.jhk.allgo.stock.repository.StocksRepository;
import com.jhk.allgo.stock.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllgoAlphaService {

	private final HashMap<String, ChegBeanDto> chegBean;
	private final HashMap<String, ProgramBeanDto> programBean;
	
	private final ScoreService scoreService;
	
	private final StocksRepository stocksRepository;
	
	private final String ALPHATYPE = "A";

	public void scoreGenerate() {
		
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
	
	public ResponseEntity<StocksResponseDto> portfolioGenerate(Date date, List<String> holdingList){
		
		StocksResponseDto portfolioStocks = null;
		
		if(holdingList == null){
			holdingList = new ArrayList<String>();
			holdingList.add(""); // dummy
		}
		
		List<Stocks> portfolioList = stocksRepository.allgoAlphaPortfolioGenerate(date);
		
		for(Stocks stocks : portfolioList){
			if(holdingList.contains(stocks.getCode())){
				continue;
			} else{
				portfolioStocks = StocksResponseDto.builder()
						.date(stocks.getDate())
						.code(stocks.getCode())
						.name_kor(stocks.getName_kor())
						.market(stocks.getMarket())
						.build();
				break;
			}
		}
		
		if(portfolioStocks == null){
			throw new CommonNotFoundException();
		}
		
		return ResponseEntity.ok().body(portfolioStocks);
		
	}
	
}
