package com.jhk.allgo.portfolio.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jhk.allgo.portfolio.exception.PortfolioNotFoundException;
import com.jhk.allgo.portfolio.model.dto.response.PortfolioResponseDto;
import com.jhk.allgo.portfolio.model.entity.Portfolio;
import com.jhk.allgo.portfolio.repository.PortfolioRepository;

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
                .orElseThrow(PortfolioNotFoundException::new);
    }

    /*public ResponseEntity<ProductResponseDto> findById(Long id) {
        Optional<Product> option = productRepository.findById(id);
        return option.map(product -> ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseDto.builder()
                        .id(product.getId())
                        .description(product.getDescription()).build()))
                .orElseThrow(ProductNotFoundException::new);
    }

    public ResponseEntity<ProductResponseListDto> findByIds(List<Long> ids) {
        List<Optional<Product>> options = productRepository.findByIdIn(ids);
        System.out.println(options);
        if (options.size() != ids.size()) {
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok().body(ProductResponseListDto.builder()
                .productResponseDtoList(options.stream().map(Optional::get).map(product ->
                        ProductResponseDto.builder()
                                .id(product.getId())
                                .description(product.getDescription())
                                .build()).collect(Collectors.toList()))
                .build());
    }

    public ResponseEntity<ProductResponseDto> create(ProductRequestDto request) {
        Product newProduct = productRepository.save(
                Product.builder()
                        .description(request.getDescription())
                        .build());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductResponseDto.builder()
                        .id(newProduct.getId())
                        .description(newProduct.getDescription())
                        .build()
                );
    }

    public ResponseEntity<ProductResponseDto> update(ProductRequestDto request) {
        Optional<Product> option = productRepository.findById(request.getId());
        return option.map(product -> {
            product.setDescription(request.getDescription());
            productRepository.save(product);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ProductResponseDto.builder()
                            .id(product.getId())
                            .description(product.getDescription())
                            .build());
        })
                .orElseThrow(ProductNotFoundException::new);
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Product> option = productRepository.findById(id);
        try {
            if (option.isPresent()) {
                productRepository.deleteById(option.get().getId());
                return ResponseEntity.ok().body("Successfully deleted");
            } else {
                throw new ProductNotFoundException();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ProductConstraintViolationException();
        }

    }*/
}
