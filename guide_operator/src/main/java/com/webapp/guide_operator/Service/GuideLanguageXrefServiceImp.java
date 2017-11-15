package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.GuideLanguageXref;
import com.webapp.guide_operator.Repository.GuideLanguageXrefRepository;

@Service
public class GuideLanguageXrefServiceImp implements GuideLanguageXrefService{
	@Autowired
	private GuideLanguageXrefRepository guideLanguageXrefRepository;

	@Override
	public Iterable<GuideLanguageXref> findAll() {
		// TODO Auto-generated method stub
		return guideLanguageXrefRepository.findAll();
	}

	@Override
	public List<GuideLanguageXref> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideLanguageXref findOne(int id) {
		// TODO Auto-generated method stub
		return guideLanguageXrefRepository.findOne(id);
	}

	@Override
	public void save(GuideLanguageXref guideLanguageXref) {
		guideLanguageXrefRepository.save(guideLanguageXref);
		
	}

	@Override
	public void delete(int id) {
		guideLanguageXrefRepository.delete(id);
		
	}
	
}
