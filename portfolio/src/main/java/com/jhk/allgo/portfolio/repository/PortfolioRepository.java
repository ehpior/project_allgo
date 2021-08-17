package com.jhk.allgo.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.portfolio.model.entity.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{
	
	List<Optional<Portfolio>> findByIdIn(@Param("ids") List<Long> portfolioIds);
	
}
