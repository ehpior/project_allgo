package com.jhk.allgo.stock.model.dto.response;

import java.util.Date;

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
public class ChegResponseDto {
	
	private Date date;
	private String code;
	private Integer price;
	private Integer change_price;
	private Double increase_rate;
	private Integer volume;
	private Integer cul_volume;
	private Integer cul_amount;
	private Integer open;
	private Integer high;
	private Integer low;
	private Double turn_over;
	private Double volume_power;
	private Integer capitalization;
	
}
