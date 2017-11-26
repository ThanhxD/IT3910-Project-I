package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.guide_operator.Entities.Operator;

public interface OperatorService {
	Iterable<Operator> findAll();
	Page<Operator> findAll(Pageable pageable);
	List<Operator> search(String q);

	Operator findOne(int id);

	Operator findByUserId(int id);

	void save(Operator operator);

	void delete(int id);
}
