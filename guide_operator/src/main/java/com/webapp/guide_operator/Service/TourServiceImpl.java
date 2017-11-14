package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Repository.TourRepository;

@Service
public class TourServiceImpl implements TourService{
	@Autowired
	private TourRepository tourRepository;

	@Override
	public Iterable<Tour> findAll() {
		// TODO Auto-generated method stub
		return tourRepository.findAll();
	}

	@Override
	public List<Tour> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour findOne(int id) {
		// TODO Auto-generated method stub
		return tourRepository.findOne(id);
	}

	@Override
	public void save(Tour tour) {
		tourRepository.save(tour);
		
	}

	@Override
	public void delete(int id) {
		tourRepository.delete(id);
		
	}
	
}
