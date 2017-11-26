package com.webapp.guide_operator.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Contract;

public interface ContractRepository extends CrudRepository<Contract, Integer>{
	Page<Contract> findAll(Pageable pageable);
}
