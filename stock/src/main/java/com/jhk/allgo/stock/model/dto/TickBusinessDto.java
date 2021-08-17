package com.jhk.allgo.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class TickBusinessDto {
	
	private String time = "";
	private String state = "";
	
	@Override
	public String toString() {
		return "TickBusiness [time=" + time + ", state=" + state + "]";
	}
	
	@Builder
	public TickBusinessDto(String time, String state) {
		this.time = time;
		this.state = state;
	}

}
