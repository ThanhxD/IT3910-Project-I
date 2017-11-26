package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Repository.GuideRepository;
@Service
public class GuideServiceImpl implements GuideService{
	@Autowired
	private GuideRepository guideRepository;
	
	@Override
	public Iterable<Guide> findAll() {
		// TODO Auto-generated method stub
		return guideRepository.findAll();
	}

	@Override
	public List<Guide> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guide findOne(int id) {
		// TODO Auto-generated method stub
		return guideRepository.findOne(id);
	}

	@Override
	public void save(Guide guide) {
		guideRepository.save(guide);
		
	}

	@Override
	public void delete(int id) {
		guideRepository.delete(id);
		
	}

}
