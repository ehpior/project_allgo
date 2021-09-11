package com.jhk.allgo.stock.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.stock.model.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Date>{
	
	
	
}
