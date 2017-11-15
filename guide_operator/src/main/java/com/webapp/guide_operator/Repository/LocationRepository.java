package com.webapp.guide_operator.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Location;

public interface LocationRepository extends CrudRepository<Location, Integer>{
	
}
