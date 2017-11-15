package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Language;

public interface LanguageService {
	Iterable<Language> findAll();

    List<Language> search(String q);

    Language findOne(int id);

    void save(Language language);

    void delete(int id);
}
