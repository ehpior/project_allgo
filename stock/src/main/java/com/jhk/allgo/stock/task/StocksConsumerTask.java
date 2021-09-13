package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.bean.StocksBeanDto;
import com.jhk.allgo.stock.service.StocksService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StocksConsumerTask {
	
	private final HashMap<String, StocksBeanDto> stocksBean;
	
	private final StocksService stocksService;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@KafkaListener(topics = "${kafka.topic.stocks}")
	public void listenTickProgram(@Payload String data){
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			StocksBeanDto stocksDto = StocksBeanDto.builder()
					.date(dateFormat.parse(jAry.getString(0)))
					.code(jAry.getString(1))
					.name_kor(jAry.getString(2))
					.market(jAry.getInt(3))
					.build();
			
			stocksBean.put(jAry.getString(1), stocksDto);
			stocksService.create(stocksDto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
