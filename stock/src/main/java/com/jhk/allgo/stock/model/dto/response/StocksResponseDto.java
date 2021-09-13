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
public class StocksResponseDto {
	
	private Date date;
	private String code;
	private String name_kor;
	private Integer market;
	
}
