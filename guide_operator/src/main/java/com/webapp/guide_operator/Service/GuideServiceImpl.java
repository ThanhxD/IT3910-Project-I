package com.webapp.guide_operator.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Repository.GuideRepository;
@Service
public class GuideServiceImpl implements GuideService{
	@Autowired
	private GuideRepository guideRepository;
	
	@Override
	public Page<Guide> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return guideRepository.findAll(pageable);
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



	@Override
	public Guide findByUserId(int id) {
		// TODO Auto-generated method stub
		return guideRepository.findByUserId(id);
	}

}
