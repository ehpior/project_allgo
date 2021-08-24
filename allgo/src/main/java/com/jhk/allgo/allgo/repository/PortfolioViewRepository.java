package com.jhk.allgo.allgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.allgo.model.entity.PortfolioView;

//@RepositoryDefinition(domainClass=Portfolio.class, idClass=Long.class)
@Repository
public interface PortfolioViewRepository extends JpaRepository<PortfolioView, Long>{
	
	//List<Optional<Portfolio>> findByIdIn(@Param("ids") List<Long> ids);
	
	/*List<PortfolioView> findByStatus(@Param("status") String status);
	List<PortfolioView> findByStatus(@Param("status") String status);
	List<PortfolioView> findByStatus(@Param("status") String status);*/
	
}
