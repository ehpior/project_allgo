package com.jhk.allgo.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.portfolio.model.entity.Portfolio;

//@RepositoryDefinition(domainClass=Portfolio.class, idClass=Long.class)
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{
	
	//List<Optional<Portfolio>> findByIdIn(@Param("ids") List<Long> ids);
	
	//List<Portfolio> findByStatus(@Param("status") String status);
	
	List<Portfolio> findByAllgoType(@Param("allgo_type") String allgo_type);
	
	@Query(value="select ifnull(max(portfolio_id)+1, 0) from portfolio",
		nativeQuery=true)
	Integer getMaxPortfolioId();
	
}
