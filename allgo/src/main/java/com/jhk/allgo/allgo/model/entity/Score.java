package com.jhk.allgo.allgo.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.jhk.allgo.allgo.model.entity.id.DateTypeCodePK;

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
@Table(name="score")
@IdClass(DateTypeCodePK.class)
public class Score {
	
	@Id
	private Date date;
	@Id
	private String type;
	@Id
	private String code;
	
	private double score;
	private int rank;
	private LocalDateTime create_time;
	
	
}
