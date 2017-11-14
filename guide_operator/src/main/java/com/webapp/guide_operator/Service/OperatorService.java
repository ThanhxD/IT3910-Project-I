package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Operator;

public interface OperatorService {
	Iterable<Operator> findAll();

    List<Operator> search(String q);

    Operator findOne(int id);

    void save(Operator operator);

    void delete(int id);
}
