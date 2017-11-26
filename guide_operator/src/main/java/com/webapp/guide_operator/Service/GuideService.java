package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Guide;

public interface GuideService {
	Iterable<Guide> findAll();

    List<Guide> search(String q);

    Guide findOne(int id);

    void save(Guide guide);

    void delete(int id);
}
