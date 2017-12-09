package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Tour_Guide_Xref;
import com.webapp.guide_operator.Repository.Tour_Guide_Xref_Repository;
@Service
public class Tour_Guide_Xref_Impl implements Tour_Guide_Xref_Service{
	@Autowired
	private Tour_Guide_Xref_Repository tour_Guide_Repository;
	@Override
	public Iterable<Tour_Guide_Xref> findAll() {
		// TODO Auto-generated method stub
		return tour_Guide_Repository.findAll();
	}

	@Override
	public List<Tour_Guide_Xref> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour_Guide_Xref findOne(int id) {
		// TODO Auto-generated method stub
		return tour_Guide_Repository.findOne(id);
	}

	@Override
	public void save(Tour_Guide_Xref tourguidexref) {
		tour_Guide_Repository.save(tourguidexref);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		tour_Guide_Repository.delete(id);
	}

}
