package com.jhk.allgo.stock.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.exception.CommonConstraintViolationException;
import com.jhk.allgo.stock.exception.CommonNotFoundException;
import com.jhk.allgo.stock.model.dto.request.ScoreRequestDto;
import com.jhk.allgo.stock.model.dto.response.ScoreResponseDto;
import com.jhk.allgo.stock.model.dto.response.ScoreResponseListDto;
import com.jhk.allgo.stock.model.entity.Score;
import com.jhk.allgo.stock.model.entity.id.DateTypeCodePK;
import com.jhk.allgo.stock.repository.ScoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService {
	
	private final ScoreRepository scoreRepository;
	
	public ResponseEntity<ScoreResponseListDto> findByDate(Date date) {
        List<Optional<Score>> options = scoreRepository.findByDate(date);
        
        return ResponseEntity.ok().body(ScoreResponseListDto.builder()
                .scoreResponseDtoList(options.stream().map(Optional::get).map(score ->
                        ScoreResponseDto.builder()
                                .date(score.getDate())
                                .type(score.getType())
                                .code(score.getCode())
                                .score(score.getScore())
                                .create_time(score.getCreateTime())
                                .update_time(score.getUpdateTime())
                                .build()).collect(Collectors.toList()))
                .build());
    }
	
	public ResponseEntity<ScoreResponseListDto> findByCode(String code) {
        List<Optional<Score>> options = scoreRepository.findByCode(code);
        
        return ResponseEntity.ok().body(ScoreResponseListDto.builder()
                .scoreResponseDtoList(options.stream().map(Optional::get).map(score ->
                        ScoreResponseDto.builder()
                                .date(score.getDate())
                                .type(score.getType())
                                .code(score.getCode())
                                .score(score.getScore())
                                .create_time(score.getCreateTime())
                                .update_time(score.getUpdateTime())
                                .build()).collect(Collectors.toList()))
                .build());
    }
	
	public ResponseEntity<ScoreResponseDto> create(DateTypeCodePK dateTypeCodePK, ScoreRequestDto request) {
        Score newScore = scoreRepository.save(
                Score.builder()
                	.date(dateTypeCodePK.getDate())
                	.type(dateTypeCodePK.getType())
                	.code(dateTypeCodePK.getCode())
                	.score(request.getScore())
                	.build());
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ScoreResponseDto.builder()
                    	.date(newScore.getDate())
                    	.type(newScore.getType())
                    	.code(newScore.getCode())
                    	.score(newScore.getScore())
                        .create_time(newScore.getCreateTime())
                        .update_time(newScore.getUpdateTime())
                    	.build());
        
    }
	
	public ResponseEntity<ScoreResponseDto> update(DateTypeCodePK dateTypeCodePK, ScoreRequestDto request) {
        Optional<Score> option = scoreRepository.findById(dateTypeCodePK);
        
        return option.map(score -> {
        	score.setScore(request.getScore());
        	
        	Score savedScore = scoreRepository.save(score);
        	
        	return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ScoreResponseDto.builder()
                    		.date(savedScore.getDate())
                        	.type(savedScore.getType())
                        	.code(savedScore.getCode())
                        	.score(savedScore.getScore())
                            .create_time(savedScore.getCreateTime())
                            .update_time(savedScore.getUpdateTime())
    	                    .build());
        	})
        		.orElseThrow(CommonNotFoundException::new);
          
    }
	
	public ResponseEntity<String> delete(DateTypeCodePK dateTypeCodePK) {
        Optional<Score> option = scoreRepository.findById(dateTypeCodePK);
        try {
            if (option.isPresent()) {
            	scoreRepository.deleteById(dateTypeCodePK);
                return ResponseEntity.ok().body("Successfully deleted");
            } else {
                throw new CommonNotFoundException();
            }
        } catch (DataIntegrityViolationException e) {
            throw new CommonConstraintViolationException();
        }
    }
	
	public ResponseEntity<String> deleteByDateAndType(DateTypeCodePK dateTypeCodePK) {
        try {
            scoreRepository.deleteByDateAndType(dateTypeCodePK.getDate(), dateTypeCodePK.getType());
            return ResponseEntity.ok().body("Successfully deleted");
        } catch (DataIntegrityViolationException e) {
            throw new CommonConstraintViolationException();
        }
    }
	
	public ResponseEntity<String> deleteByDate(DateTypeCodePK dateTypeCodePK) {
        try {
            scoreRepository.deleteByDate(dateTypeCodePK.getDate());
            return ResponseEntity.ok().body("Successfully deleted");
        } catch (DataIntegrityViolationException e) {
            throw new CommonConstraintViolationException();
        }
    }
	
}
