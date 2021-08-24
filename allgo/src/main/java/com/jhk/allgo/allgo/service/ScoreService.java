package com.jhk.allgo.allgo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.allgo.model.dto.response.ScoreResponseDto;
import com.jhk.allgo.allgo.model.dto.response.ScoreResponseListDto;
import com.jhk.allgo.allgo.model.entity.Score;
import com.jhk.allgo.allgo.repository.ScoreRepository;

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
                                .rank(score.getRank())
                                .build()).collect(Collectors.toList()))
                .build());
    }
	
}
