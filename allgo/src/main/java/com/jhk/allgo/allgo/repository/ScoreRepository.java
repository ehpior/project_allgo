package com.jhk.allgo.allgo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.allgo.model.entity.Score;
import com.jhk.allgo.allgo.model.entity.id.DateTypeCodePK;

//@RepositoryDefinition(domainClass=Portfolio.class, idClass=Long.class)
@Repository
public interface ScoreRepository extends JpaRepository<Score, DateTypeCodePK>{
	
	List<Optional<Score>> findByDate(@Param("date") Date date);
	
	List<Optional<Score>> findByCode(@Param("code") String code);
	
	void deleteByDateAndType(@Param("date") Date date, @Param("type") String type);
	void deleteByDate(@Param("date") Date date);
	
}
