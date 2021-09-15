package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.bean.ChegBeanDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChegConsumerTask {
	
	private final HashMap<String, ChegBeanDto> chegBean;
	
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	
	@KafkaListener(topics = "${kafka.topic.cheg}")
	public void listenCheg(@Payload String data){
	    //System.out.println(String.format("received data : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			ChegBeanDto tickChegDto = ChegBeanDto.builder()
					.date(sdFormat.parse(jAry.getString(0)))
					.code(jAry.getString(1))
					.price(jAry.getInt(2))
					.change_price(jAry.getInt(3))
					.increase_rate(jAry.getDouble(4))
					.volume(jAry.getInt(5))
					.cul_volume(jAry.getInt(6))
					.cul_amount(jAry.getInt(7))
					.open(jAry.getInt(8))
					.high(jAry.getInt(9))
					.low(jAry.getInt(10))
					.turn_over(jAry.getDouble(11))
					.volume_power(jAry.getDouble(12))
					.capitalization(jAry.getInt(13))
					.build();
			
			chegBean.put(jAry.getString(1), tickChegDto);
			
			//System.out.println(ChegBeanDto.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
