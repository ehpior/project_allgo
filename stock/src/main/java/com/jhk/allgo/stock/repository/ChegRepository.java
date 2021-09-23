package com.jhk.allgo.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.stock.model.entity.Cheg;
import com.jhk.allgo.stock.model.entity.id.DateCodePK;

@Repository
public interface ChegRepository extends JpaRepository<Cheg, DateCodePK>{
	
	@Query(value=
			"SELECT * "+
			"FROM cheg "+
			"WHERE DATE = (SELECT MAX(DATE) FROM cheg)",
		nativeQuery = true)
	List<Cheg> init();
}
