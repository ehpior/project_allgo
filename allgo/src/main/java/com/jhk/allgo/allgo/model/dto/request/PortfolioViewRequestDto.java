package com.jhk.allgo.allgo.model.dto.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
public class PortfolioViewRequestDto {
	
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
}
