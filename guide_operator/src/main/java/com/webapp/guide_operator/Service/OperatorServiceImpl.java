package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Repository.OperatorRepository;

@Service
public class OperatorServiceImpl implements OperatorService{
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Override
	public Iterable<Operator> findAll() {
		// TODO Auto-generated method stub
		return operatorRepository.findAll();
	}

	@Override
	public List<Operator> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operator findOne(int id) {
		// TODO Auto-generated method stub
		return operatorRepository.findOne(id);
	}

	@Override
	public void save(Operator operator) {
		operatorRepository.save(operator);
		
	}

	@Override
	public void delete(int id) {
		operatorRepository.delete(id);
		
	}
}
