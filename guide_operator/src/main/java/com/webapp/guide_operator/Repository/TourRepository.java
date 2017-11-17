package com.webapp.guide_operator.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Tour;

public interface TourRepository extends CrudRepository<Tour,Integer>{
	Page<Tour> findAll(Pageable pageable);
}
