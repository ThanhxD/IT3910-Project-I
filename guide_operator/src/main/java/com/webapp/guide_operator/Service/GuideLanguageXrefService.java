package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.GuideLanguageXref;


public interface GuideLanguageXrefService {
	Iterable<GuideLanguageXref> findAll();

    List<GuideLanguageXref> search(String q);

    GuideLanguageXref findOne(int id);

    void save(GuideLanguageXref guideLanguageXref);

    void delete(int id);
}
