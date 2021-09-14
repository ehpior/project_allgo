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
public class AllgoBetaService {

	private final HashMap<String, ChegBeanDto> chegBean;
	private final HashMap<String, ProgramBeanDto> programBean;
	
	private final ScoreService scoreService;
	
	private final StocksRepository stocksRepository;
	
	private final String BETATYPE = "B";

	public void scoreGenerate() {
		
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
	
	public ResponseEntity<StocksResponseDto> portfolioGenerate(Date date, List<String> holdingList){
		
		StocksResponseDto portfolioStocks = null;
		
		if(holdingList == null){
			holdingList = new ArrayList<String>();
			holdingList.add(""); // dummy
		}
		
		List<Stocks> portfolioList = stocksRepository.allgoBetaPortfolioGenerate(date);
		
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
