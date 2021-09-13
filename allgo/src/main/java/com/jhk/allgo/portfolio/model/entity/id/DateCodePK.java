package com.jhk.allgo.portfolio.model.entity.id;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateCodePK implements Serializable{
	
	private Date date;
	private String code;

}
