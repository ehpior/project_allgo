package com.jhk.allgo.stock.task;

import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.bean.BusinessBeanDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessConsumerTask {
	
	private final BusinessBeanDto businessBean;
	
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	
	@KafkaListener(topics = "${kafka.topic.business}")
	public void listenTickBusiness(@Payload String data){
	    //System.out.println(String.format("received data3 : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			/*TickBusinessDto tickBusinessDto = TickBusinessDto.builder()
					.time(jAry.getString(0))
					.state(jAry.getString(1))
					.build();*/
			
			businessBean.setDate(sdFormat.parse(jAry.getString(0)));
			businessBean.setStatus(jAry.getInt(1));
			
			System.out.println(businessBean.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
