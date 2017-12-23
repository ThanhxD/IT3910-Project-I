package com.webapp.guide_operator.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.Language;

public interface LanguageRepository extends CrudRepository<Language, Integer>{
	Page<Language> findAll(Pageable pageable);
	Language findByLanguage(String language);
}
