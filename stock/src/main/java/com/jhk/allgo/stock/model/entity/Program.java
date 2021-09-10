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
@Table(name="program")
@IdClass(DateCodePK.class)
public class Program {
	
	@Id
	private Date date;
	@Id
	private String code;
	
	private Integer sell_volume;
	private Integer sell_amount;
	private Integer buy_volume;
	private Integer buy_amount;
	private Integer net_buy_volume;
	private Integer net_buy_amount;
	
	@CreatedDate
    private LocalDateTime createTime;
	
	
}
