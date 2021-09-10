package com.jhk.allgo.stock.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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
@Table(name="stocks")
@IdClass(DateCodePK.class)
public class Stocks {
	
	@Id
	private Date date;
	@Id
	private String code;
	
	private String name_kor;
	private Integer market;
	
}
