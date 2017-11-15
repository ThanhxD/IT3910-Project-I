package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.TourLocationXref;
import com.webapp.guide_operator.Repository.TourLocationXrefRepository;

@Service
public class TourLocationXrefServiceImp implements TourLocationXrefService{
	@Autowired
	private TourLocationXrefRepository tourLocationXrefRepository;

	@Override
	public Iterable<TourLocationXref> findAll() {
		// TODO Auto-generated method stub
		return tourLocationXrefRepository.findAll();
	}

	@Override
	public List<TourLocationXref> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TourLocationXref findOne(int id) {
		// TODO Auto-generated method stub
		return tourLocationXrefRepository.findOne(id);
	}

	@Override
	public void save(TourLocationXref tourLocationXref) {
		// TODO Auto-generated method stub
		tourLocationXrefRepository.save(tourLocationXref);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		tourLocationXrefRepository.delete(id);
	}


	
}
