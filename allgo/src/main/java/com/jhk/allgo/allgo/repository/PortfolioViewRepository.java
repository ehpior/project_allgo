package com.jhk.allgo.allgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.allgo.model.entity.PortfolioView;

//@RepositoryDefinition(domainClass=Portfolio.class, idClass=Long.class)
@Repository
public interface PortfolioViewRepository extends JpaRepository<PortfolioView, Long>{
	
	//List<Optional<Portfolio>> findByIdIn(@Param("ids") List<Long> ids);
	
	/*List<PortfolioView> findByStatus(@Param("status") String status);
	List<PortfolioView> findByStatus(@Param("status") String status);
	List<PortfolioView> findByStatus(@Param("status") String status);*/
	
	List<PortfolioView> findByAllgoType(@Param("allgo_type") String allgo_type);
	List<PortfolioView> findByAllgoTypeAndStatus(
			@Param("allgo_type") String allgo_type, @Param("status") String status);
	
	List<PortfolioView> findByStatus(@Param("status") String status);
	
}
