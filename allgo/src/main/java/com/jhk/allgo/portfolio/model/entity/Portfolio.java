package com.jhk.allgo.portfolio.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
//@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Table(name="portfolio")
public class Portfolio {
	
	/**
     * 상품 번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer portfolioId;
    
    private String allgoType;
    
    private String stockCode;
    private String stockName;
    
    private Date date;
    private Integer price;
    private Integer targetRate;
    private Integer lossRate;
    private Integer holdingDay;
    private String reason;
    private Integer percent;
    private String type;
    private String status;
    
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
    

}
