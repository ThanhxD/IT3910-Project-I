package com.webapp.guide_operator.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Guide;

public interface GuideRepository extends CrudRepository<Guide, Integer>{
	
	Guide findByUserId(String UserId);
	
}