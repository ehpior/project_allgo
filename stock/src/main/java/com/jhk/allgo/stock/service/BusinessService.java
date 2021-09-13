package com.jhk.allgo.stock.service;

import org.springframework.stereotype.Service;

import com.jhk.allgo.stock.model.dto.bean.BusinessBeanDto;
import com.jhk.allgo.stock.model.entity.Business;
import com.jhk.allgo.stock.repository.BusinessRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessService {
	
	private final BusinessRepository businessRepository;
	private final BusinessBeanDto businessBean;
	
	public void insertBeanToDB() {
		
        businessRepository.save(
            Business.builder()
            	.date(businessBean.getDate())
            	.status(businessBean.getStatus())
            	.build()
        );
    }
	
	public void create(BusinessBeanDto businessDto) {
        Business newBusiness = businessRepository.save(
            Business.builder()
            	.date(businessDto.getDate())
            	.status(businessDto.getStatus())
            	.build()
        );
    }
	
}
