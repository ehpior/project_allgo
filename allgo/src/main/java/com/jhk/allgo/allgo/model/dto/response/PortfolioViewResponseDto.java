package com.jhk.allgo.allgo.model.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class PortfolioViewResponseDto {

	private Long portfolio_id;
    
    private String allgo_type;
    
    private String stock_code;
    private String stock_name;
    
    private Integer holding_day;
    
    private Integer average_buy_price;
    private Integer average_sell_price;
    private Integer rate;
    
    private Date first_buy_date;
    private Date last_sell_date;
    
    private String status;

    /*public Portfolio responseDto2Entity(){
        return Portfolio.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }*/
}
