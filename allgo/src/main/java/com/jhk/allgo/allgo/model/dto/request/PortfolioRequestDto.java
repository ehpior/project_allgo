package com.jhk.allgo.allgo.model.dto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
public class PortfolioRequestDto {
	
	private Long id;
    private Integer portfolio_id;
    
    private String allgo_type;
    
    private String stock_code;
    private String stock_name;
    
    @JsonFormat(pattern="yyyyMMdd")
    private Date date;
    
    private Integer price;
    private Integer target_rate;
    private Integer loss_rate;
    private Integer holding_day;
    private String reason;
    private Integer percent;
    private String type;
    private String status;
    
}
