package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.OperatorTourXref;
import com.webapp.guide_operator.Entities.TourLocationXref;

public interface TourLocationXrefService {
	Iterable<TourLocationXref> findAll();

    List<TourLocationXref> search(String q);

    TourLocationXref findOne(int id);

    void save(TourLocationXref tourLocationXref);

    void delete(int id);
}
