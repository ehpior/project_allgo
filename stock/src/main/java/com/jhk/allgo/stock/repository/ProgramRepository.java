package com.jhk.allgo.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.stock.model.entity.Program;
import com.jhk.allgo.stock.model.entity.id.DateCodePK;

@Repository
public interface ProgramRepository extends JpaRepository<Program, DateCodePK>{
	
	@Query(value=
			"SELECT * "+
			"FROM program "+
			"WHERE DATE = (SELECT MAX(DATE) FROM program)",
		nativeQuery = true)
	List<Program> init();
	
}
