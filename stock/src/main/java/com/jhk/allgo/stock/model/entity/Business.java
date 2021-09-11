package com.jhk.allgo.stock.model.entity;

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
@Table(name="business")
public class Business {
	
	@Id
	private Date date;
	
	private Integer status;
	
}
