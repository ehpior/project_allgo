package com.jhk.allgo.portfolio.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ApiModel
@Table(name="portfolio_view")
public class PortfolioView {
	/**
     * 상품 번호
     */
	@Id
    private Long portfolioId;
    
    private String allgoType;
    
    private String stockCode;
    private String stockName;
    
    private Integer holdingDay;
    
    private Integer averageBuyPrice;
    private Integer averageSellPrice;
    private Integer rate;
    
    private Date firstBuyDate;
    private Date lastSellDate;
    
    private String status;
    
}
