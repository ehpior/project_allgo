package com.jhk.allgo.stock.model.dto.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class ScoreResponseListDto {
	private List<ScoreResponseDto> scoreResponseDtoList;
}
