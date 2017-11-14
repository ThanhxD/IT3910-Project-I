package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Contract;
import com.webapp.guide_operator.Repository.ContractRepository;
@Service
public class ContractServiceImpl implements ContractService{
	@Autowired
	private ContractRepository contractRepository;

	@Override
	public Iterable<Contract> findAll() {
		// TODO Auto-generated method stub
		return contractRepository.findAll();
	}

	@Override
	public List<Contract> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contract findOne(int id) {
		// TODO Auto-generated method stub
		return contractRepository.findOne(id);
	}

	@Override
	public void save(Contract Contract) {
		contractRepository.save(Contract);
		
	}

	@Override
	public void delete(int id) {
		contractRepository.delete(id);
		
	}
	
	
}
