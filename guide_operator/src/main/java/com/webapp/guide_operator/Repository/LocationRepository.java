package com.webapp.guide_operator.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Location;

public interface LocationRepository extends CrudRepository<Location, Integer>{
	Page<Location> findAll(Pageable pageable);
	Location findByLocationName (String name);
}
