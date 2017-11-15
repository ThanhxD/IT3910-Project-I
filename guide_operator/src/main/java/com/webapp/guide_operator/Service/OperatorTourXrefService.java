package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.OperatorTourXref;

public interface OperatorTourXrefService {
	Iterable<OperatorTourXref> findAll();

    List<OperatorTourXref> search(String q);

    OperatorTourXref findOne(int id);

    void save(OperatorTourXref operatorTourXref);

    void delete(int id);
}
