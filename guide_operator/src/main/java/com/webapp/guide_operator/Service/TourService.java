package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.guide_operator.Entities.Tour;

public interface TourService {
	Page<Tour> findAll(Pageable pageable);

    List<Tour> search(String q);

    Tour findOne(int id);

    void save(Tour tour);

    void delete(int id);
}
