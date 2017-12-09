package com.webapp.guide_operator.Repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Guide;


public interface GuideRepository extends CrudRepository<Guide, Integer>{
	
	Page<Guide> findAll(Pageable pageable);
	
	Guide findByUserId(int id);
	
}
