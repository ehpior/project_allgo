package com.jhk.allgo.allgo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.allgo.exception.CommonConstraintViolationException;
import com.jhk.allgo.allgo.exception.CommonNotFoundException;
import com.jhk.allgo.allgo.model.dto.request.PortfolioRequestDto;
import com.jhk.allgo.allgo.model.dto.response.PortfolioResponseDto;
import com.jhk.allgo.allgo.model.dto.response.PortfolioResponseListDto;
import com.jhk.allgo.allgo.model.entity.Portfolio;
import com.jhk.allgo.allgo.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioService {
	
	private final PortfolioRepository portfolioRepository;
	
	
	
	public ResponseEntity<PortfolioResponseDto> findById(Long id) {
        Optional<Portfolio> option = portfolioRepository.findById(id);
        
        return option.map(portfolio -> ResponseEntity
                .status(HttpStatus.OK)
                .body(PortfolioResponseDto.builder()
                        .id(portfolio.getId())
                        .description(portfolio.getDescription()).build()))
                .orElseThrow(CommonNotFoundException::new);
    }
	
	public ResponseEntity<PortfolioResponseListDto> findByIds(List<Long> ids) {
        List<Optional<Portfolio>> options = portfolioRepository.findByIdIn(ids);
        System.out.println(options);
        if (options.size() != ids.size()) {
            throw new CommonNotFoundException();
        }
        
        return ResponseEntity.ok().body(PortfolioResponseListDto.builder()
                .portfolioResponseDtoList(options.stream().map(Optional::get).map(portfolio ->
                        PortfolioResponseDto.builder()
                                .id(portfolio.getId())
                                .description(portfolio.getDescription())
                                .build()).collect(Collectors.toList()))
                .build());
    }
	
	public ResponseEntity<PortfolioResponseListDto> findAll() {
        List<Portfolio> list = portfolioRepository.findAll();
        
        return ResponseEntity.ok().body(PortfolioResponseListDto.builder()
                .portfolioResponseDtoList(list.stream().map(portfolio ->
                        PortfolioResponseDto.builder()
                                .id(portfolio.getId())
                                .description(portfolio.getDescription())
                                .build()).collect(Collectors.toList()))
                .build());
    }
	
	public ResponseEntity<PortfolioResponseDto> create(PortfolioRequestDto request) {
        Portfolio newProduct = portfolioRepository.save(
                Portfolio.builder()
                        .description(request.getDescription())
                        .build());
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PortfolioResponseDto.builder()
                        .id(newProduct.getId())
                        .description(newProduct.getDescription())
                        .build()
                );
    }
	
	public ResponseEntity<PortfolioResponseDto> update(PortfolioRequestDto request) {
        Optional<Portfolio> option = portfolioRepository.findById(request.getId());
        
        return option.map(portfolio -> {
            portfolio.setDescription(request.getDescription());
            portfolioRepository.save(portfolio);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(PortfolioResponseDto.builder()
                            .id(portfolio.getId())
                            .description(portfolio.getDescription())
                            .build());
        })
        		.orElseThrow(CommonNotFoundException::new);
    }
	
	public ResponseEntity<String> delete(Long id) {
        Optional<Portfolio> option = portfolioRepository.findById(id);
        try {
            if (option.isPresent()) {
                portfolioRepository.deleteById(option.get().getId());
                return ResponseEntity.ok().body("Successfully deleted");
            } else {
                throw new CommonNotFoundException();
            }
        } catch (DataIntegrityViolationException e) {
            throw new CommonConstraintViolationException();
        }

    }

}
