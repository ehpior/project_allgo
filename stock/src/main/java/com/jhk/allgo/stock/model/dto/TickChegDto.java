package com.jhk.allgo.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickChegDto {
	
	private String code = "";
	private String time = "";
	private int price = 0;
	private int change_price = 0;
	private float increase_rate = 0;
	private int volume = 0;
	private int cul_volume = 0;
	private int cul_amount = 0;
	private int open = 0;
	private int high = 0;
	private int low = 0;
	private float turn_over = 0;
	private float volume_power = 0;
	private int capitalization = 0;
	
	@Override
	public String toString() {
		return "TickCheg [code=" + code + ", time=" + time + ", price=" + price + ", change_price=" + change_price
				+ ", increase_rate=" + increase_rate + ", volume=" + volume + ", cul_volume=" + cul_volume
				+ ", cul_amount=" + cul_amount + ", open=" + open + ", high=" + high + ", low=" + low + ", turn_over="
				+ turn_over + ", volume_power=" + volume_power + ", capitalization=" + capitalization + "]";
	}

	@Builder
	public TickChegDto(String code, String time, int price, int change_price, float increase_rate, int volume,
			int cul_volume, int cul_amount, int open, int high, int low, float turn_over, float volume_power,
			int capitalization) {
		this.code = code;
		this.time = time;
		this.price = price;
		this.change_price = change_price;
		this.increase_rate = increase_rate;
		this.volume = volume;
		this.cul_volume = cul_volume;
		this.cul_amount = cul_amount;
		this.open = open;
		this.high = high;
		this.low = low;
		this.turn_over = turn_over;
		this.volume_power = volume_power;
		this.capitalization = capitalization;
	}
	
}
