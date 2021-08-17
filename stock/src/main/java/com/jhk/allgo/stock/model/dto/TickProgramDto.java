package com.jhk.allgo.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickProgramDto {
	
	private String code = "";
	private String time = "";
	private int sell_volume = 0;
	private int sell_amount = 0;
	private int buy_volume = 0;
	private int buy_amount = 0;
	private int net_buy_volume = 0;
	private int net_buy_amount = 0;
	
	@Override
	public String toString() {
		return "TickProgram [code=" + code + ", time=" + time + ", sell_volume=" + sell_volume + ", sell_amount="
				+ sell_amount + ", buy_volume=" + buy_volume + ", buy_amount=" + buy_amount + ", net_buy_volume="
				+ net_buy_volume + ", net_buy_amount=" + net_buy_amount + "]";
	}
	
	@Builder
	public TickProgramDto(String code, String time, int sell_volume, int sell_amount, int buy_volume, int buy_amount,
			int net_buy_volume, int net_buy_amount) {
		this.code = code;
		this.time = time;
		this.sell_volume = sell_volume;
		this.sell_amount = sell_amount;
		this.buy_volume = buy_volume;
		this.buy_amount = buy_amount;
		this.net_buy_volume = net_buy_volume;
		this.net_buy_amount = net_buy_amount;
	}

}
