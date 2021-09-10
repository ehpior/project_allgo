package com.jhk.allgo.stock.task;

import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.ChegDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChegConsumerTask {
	
	private final HashMap<String, ChegDto> chegBean;
	
	@KafkaListener(topics = "${kafka.topic.cheg}")
	public void listenCheg(@Payload String data){
	    //System.out.println(String.format("received data : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			ChegDto tickChegDto = ChegDto.builder()
					.code(jAry.getString(0))
					.time(jAry.getString(1))
					.price(jAry.getInt(2))
					.change_price(jAry.getInt(3))
					.increase_rate((float)jAry.getDouble(4))
					.volume(jAry.getInt(5))
					.cul_volume(jAry.getInt(6))
					.cul_amount(jAry.getInt(7))
					.open(jAry.getInt(8))
					.high(jAry.getInt(9))
					.low(jAry.getInt(10))
					.turn_over((float)jAry.getDouble(11))
					.volume_power((float)jAry.getDouble(12))
					.capitalization(jAry.getInt(13))
					.build();
			
			chegBean.put(jAry.getString(0), tickChegDto);
			
			//System.out.println(tickCheg.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
