package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.GuideLocationXref;
import com.webapp.guide_operator.Repository.GuideLocationXrefRepository;

@Service
public class GuideLocationXrefServiceImpl implements GuideLocationXrefService{
	@Autowired
	private GuideLocationXrefRepository guideLocationXrefRepository;

	@Override
	public Iterable<GuideLocationXref> findAll() {
		// TODO Auto-generated method stub
		return guideLocationXrefRepository.findAll();
	}

	@Override
	public List<GuideLocationXref> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideLocationXref findOne(int id) {
		// TODO Auto-generated method stub
		return guideLocationXrefRepository.findOne(id);
	}

	@Override
	public void save(GuideLocationXref guideLocationXref) {
		// TODO Auto-generated method stub
		guideLocationXrefRepository.save(guideLocationXref);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		guideLocationXrefRepository.delete(id);
	}
	
}
