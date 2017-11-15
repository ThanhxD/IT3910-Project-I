package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.GuideLocationXref;

public interface GuideLocationXrefService {
	Iterable<GuideLocationXref> findAll();

    List<GuideLocationXref> search(String q);

    GuideLocationXref findOne(int id);

    void save(GuideLocationXref guideLocationXref);

    void delete(int id);
}
