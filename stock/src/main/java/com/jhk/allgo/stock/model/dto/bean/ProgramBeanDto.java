package com.jhk.allgo.stock.model.dto.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ProgramBeanDto {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	private String code;
	private Integer sell_volume;
	private Integer sell_amount;
	private Integer buy_volume;
	private Integer buy_amount;
	private Integer net_buy_volume;
	private Integer net_buy_amount;
	
}
