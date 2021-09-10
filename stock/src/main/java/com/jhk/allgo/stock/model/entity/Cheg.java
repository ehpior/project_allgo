package com.jhk.allgo.stock.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jhk.allgo.stock.model.entity.id.DateCodePK;

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
@EntityListeners(AuditingEntityListener.class)
@Table(name="cheg")
@IdClass(DateCodePK.class)
public class Cheg {
	
	@Id
	private Date date;
	@Id
	private String code;
	
	private Integer price;
	private Integer change_price;
	private Double increase_rate;
	private Integer volume;
	private Integer cul_volume;
	private Integer cul_amount;
	private Integer open;
	private Integer high;
	private Integer low;
	private Double turn_over;
	private Double volume_power;
	private Integer capitalization;
	
	@CreatedDate
    private LocalDateTime createTime;
	
	
}
