package com.jhk.allgo.stock.task;

import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.ProgramDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramConsumerTask {
	
	private final HashMap<String, ProgramDto> programBean;
	
	@KafkaListener(topics = "${kafka.topic.program}")
	public void listenTickProgram(@Payload String data){
	    //System.out.println(String.format("received data2 : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			ProgramDto tickProgramDto = ProgramDto.builder()
					.code(jAry.getString(0))
					.time(jAry.getString(1))
					.sell_volume(jAry.getInt(2))
					.sell_amount(jAry.getInt(3))
					.buy_volume(jAry.getInt(4))
					.buy_amount(jAry.getInt(5))
					.net_buy_volume(jAry.getInt(6))
					.net_buy_amount(jAry.getInt(7))
					.build();
			
			programBean.put(jAry.getString(0), tickProgramDto);
			
			//System.out.println(tickProgram.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
