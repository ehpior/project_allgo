package com.jhk.allgo.stock.model.dto.response;

import com.jhk.allgo.stock.model.dto.TickChegDto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class CommonResponseDto {
	private int id=0;
	private TickChegDto hk;
}
