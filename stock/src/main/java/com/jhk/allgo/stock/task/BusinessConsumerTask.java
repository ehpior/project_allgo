package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.BusinessDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessConsumerTask {
	
	private final BusinessDto businessBean;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@KafkaListener(topics = "${kafka.topic.business}")
	public void listenTickBusiness(@Payload String data){
	    //System.out.println(String.format("received data3 : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			/*TickBusinessDto tickBusinessDto = TickBusinessDto.builder()
					.time(jAry.getString(0))
					.state(jAry.getString(1))
					.build();*/
			
			businessBean.setDate(dateFormat.parse(jAry.getString(0)));
			businessBean.setState(jAry.getInt(1));
			
			System.out.println(businessBean.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}