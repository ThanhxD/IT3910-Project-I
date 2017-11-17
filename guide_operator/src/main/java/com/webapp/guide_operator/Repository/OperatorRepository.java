package com.webapp.guide_operator.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Integer>{
	Page<Operator> findAll(Pageable pageable);
}
