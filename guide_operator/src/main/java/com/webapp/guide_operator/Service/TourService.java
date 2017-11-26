package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Tour;

public interface TourService {
	Iterable<Tour> findAll();

    List<Tour> search(String q);

    Tour findOne(int id);

    void save(Tour tour);

    void delete(int id);
}
