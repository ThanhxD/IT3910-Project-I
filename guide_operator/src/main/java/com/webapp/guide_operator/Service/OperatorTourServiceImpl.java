package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.OperatorTourXref;
import com.webapp.guide_operator.Repository.OperatorTourXrefRepository;

@Service
public class OperatorTourServiceImpl implements OperatorTourXrefService{
	@Autowired
	private OperatorTourXrefRepository operatorTourXrefRepository;

	@Override
	public Iterable<OperatorTourXref> findAll() {
		// TODO Auto-generated method stub
		return operatorTourXrefRepository.findAll();
	}

	@Override
	public List<OperatorTourXref> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperatorTourXref findOne(int id) {
		// TODO Auto-generated method stub
		return operatorTourXrefRepository.findOne(id);
	}

	@Override
	public void save(OperatorTourXref operatorTourXref) {
		operatorTourXrefRepository.save(operatorTourXref);
		
	}

	@Override
	public void delete(int id) {
		operatorTourXrefRepository.delete(id);
		
	}

	
	
}
