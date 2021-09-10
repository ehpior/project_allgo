package com.jhk.allgo.stock.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChegDto {
	
	private String code;
	private String time;
	private Integer price;
	private Integer change_price;
	private Float increase_rate;
	private Integer volume;
	private Integer cul_volume;
	private Integer cul_amount;
	private Integer open;
	private Integer high;
	private Integer low;
	private Float turn_over;
	private Float volume_power;
	private Integer capitalization;
	
}
