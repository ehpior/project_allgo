package com.jhk.allgo.stock.task;

import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.TickBusinessDto;
import com.jhk.allgo.stock.model.dto.TickChegDto;
import com.jhk.allgo.stock.model.dto.TickProgramDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
	
	private final HashMap<String, TickChegDto> tickCheg;
	
	private final HashMap<String, TickProgramDto> tickProgram;
	
	private final TickBusinessDto tickBusiness;
	
	@KafkaListener(topics = "${kafka.topic.cheg}")
	public void listenTickCheg(@Payload String data){
	    //System.out.println(String.format("received data : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			TickChegDto tickChegDto = TickChegDto.builder()
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
			
			tickCheg.put(jAry.getString(0), tickChegDto);
			
			//System.out.println(tickCheg.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@KafkaListener(topics = "${kafka.topic.program}")
	public void listenTickProgram(@Payload String data){
	    //System.out.println(String.format("received data2 : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			TickProgramDto tickProgramDto = TickProgramDto.builder()
					.code(jAry.getString(0))
					.time(jAry.getString(1))
					.sell_volume(jAry.getInt(2))
					.sell_amount(jAry.getInt(3))
					.buy_volume(jAry.getInt(4))
					.buy_amount(jAry.getInt(5))
					.net_buy_volume(jAry.getInt(6))
					.net_buy_amount(jAry.getInt(7))
					.build();
			
			tickProgram.put(jAry.getString(0), tickProgramDto);
			
			//System.out.println(tickProgram.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@KafkaListener(topics = "${kafka.topic.business}")
	public void listenTickBusiness(@Payload String data){
	    //System.out.println(String.format("received data3 : %s", data));
	    
		try {
			JSONArray jAry = new JSONArray(data);
			
			/*TickBusinessDto tickBusinessDto = TickBusinessDto.builder()
					.time(jAry.getString(0))
					.state(jAry.getString(1))
					.build();*/
			
			tickBusiness.setTime(jAry.getString(0));
			tickBusiness.setState(jAry.getInt(1));
			
			System.out.println(tickBusiness.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
