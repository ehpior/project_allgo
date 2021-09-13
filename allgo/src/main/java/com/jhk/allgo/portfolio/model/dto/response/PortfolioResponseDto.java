package com.jhk.allgo.portfolio.model.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class PortfolioResponseDto {

	private Long id;
    private Integer portfolio_id;
    
    private String allgo_type;
    
    private String stock_code;
    private String stock_name;
    
    private Date date;
    private Integer price;
    private Integer target_rate;
    private Integer loss_rate;
    private Integer holding_day;
    private String reason;
    private Integer percent;
    private String type;
    private String status;
    
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    /*public Portfolio responseDto2Entity(){
        return Portfolio.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }*/
}
