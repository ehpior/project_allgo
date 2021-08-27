package com.jhk.allgo.allgo.model.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
public class ScoreRequestDto {
	
	@DateTimeFormat(pattern="yyyyMMdd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
	private Date date;
	private String type;
	private String code;
	
	private double score;
}
