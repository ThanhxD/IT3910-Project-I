package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Language;
import com.webapp.guide_operator.Repository.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService{
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Iterable<Language> findAll() {
		// TODO Auto-generated method stub
		return languageRepository.findAll();
	}

	@Override
	public List<Language> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language findOne(int id) {
		// TODO Auto-generated method stub
		return languageRepository.findOne(id);
	}

	@Override
	public void save(Language language) {
		languageRepository.save(language);
		
	}

	@Override
	public void delete(int id) {
		languageRepository.delete(id);
		
	}

	@Override
	public Language findByLanguage(String language) {
		// TODO Auto-generated method stub
		return languageRepository.findByLanguage(language);
	}
	
	
}
