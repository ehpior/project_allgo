package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.bean.ProgramBeanDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramConsumerTask {
	
	private final HashMap<String, ProgramBeanDto> programBean;
	
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	
	@KafkaListener(topics = "${kafka.topic.program}")
	public void listenTickProgram(@Payload String data){
	    //System.out.println(String.format("received data2 : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			ProgramBeanDto tickProgramDto = ProgramBeanDto.builder()
					.date(sdFormat.parse(jAry.getString(0)))
					.code(jAry.getString(1))
					.sell_volume(jAry.getInt(2))
					.sell_amount(jAry.getInt(3))
					.buy_volume(jAry.getInt(4))
					.buy_amount(jAry.getInt(5))
					.net_buy_volume(jAry.getInt(6))
					.net_buy_amount(jAry.getInt(7))
					.build();
			
			programBean.put(jAry.getString(1), tickProgramDto);
			
			//System.out.println(tickProgram.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
