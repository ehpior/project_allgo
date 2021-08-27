package com.jhk.allgo.allgo.model.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
@Builder
public class ScoreResponseDto {
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date date;
	private String type;
	private String code;
	
	private double score;
	
	private LocalDateTime create_time;
    private LocalDateTime update_time;

}
