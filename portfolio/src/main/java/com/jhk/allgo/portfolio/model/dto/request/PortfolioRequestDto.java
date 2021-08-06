package com.jhk.allgo.portfolio.model.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
public class PortfolioRequestDto {
	/**
     * 상품 번호
     */
    private Long id;
    /**
     * 상품 설명
     */
    private String description;
}
