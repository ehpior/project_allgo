package com.jhk.allgo.allgo.model.dto.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class PortfolioResponseListDto {
	private List<PortfolioResponseDto> portfolioResponseDtoList;
}
