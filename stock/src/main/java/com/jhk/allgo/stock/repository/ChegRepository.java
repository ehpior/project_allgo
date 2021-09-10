package com.jhk.allgo.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.stock.model.entity.Cheg;
import com.jhk.allgo.stock.model.entity.id.DateCodePK;

@Repository
public interface ChegRepository extends JpaRepository<Cheg, DateCodePK>{
	

	
}
