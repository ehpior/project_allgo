package com.jhk.allgo.portfolio.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceConsumerTask {

	private final HashMap<String, Integer> priceBean;

	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");

	@KafkaListener(topics = "${kafka.topic.cheg}")
	public void listenCheg(@Payload String data) {
		// System.out.println(String.format("received data : %s", data));

		try {
			JSONArray jAry = new JSONArray(data);

			priceBean.put(jAry.getString(0), jAry.getInt(1));

			// System.out.println(tickCheg.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
