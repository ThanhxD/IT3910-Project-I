package com.webapp.guide_operator.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Landscapes;

public interface LandscapesRepositoy extends CrudRepository<Landscapes, Integer>{
	Page<Landscapes> findAll(Pageable pageable);
}
