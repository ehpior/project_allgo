package com.jhk.allgo.allgo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.allgo.model.entity.Portfolio;

//@RepositoryDefinition(domainClass=Portfolio.class, idClass=Long.class)
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{
	
	List<Optional<Portfolio>> findByIdIn(@Param("ids") List<Long> ids);
	
}
