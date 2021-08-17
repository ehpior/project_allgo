package com.jhk.allgo.portfolio.model.dto.response;

import com.jhk.allgo.portfolio.model.entity.Portfolio;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class PortfolioResponseDto {
	/**
     * 상품 번호
     */
    private Long id;
    /**
     * 상품 설명
     */
    private String description;

    public Portfolio responseDto2Entity(){
        return Portfolio.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }
}
