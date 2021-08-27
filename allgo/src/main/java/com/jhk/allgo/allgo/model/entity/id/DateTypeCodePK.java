package com.jhk.allgo.allgo.model.entity.id;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTypeCodePK implements Serializable{
	
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date date;
	private String type;
	private String code;
	
}
