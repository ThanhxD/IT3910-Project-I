package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Tour;

public interface GuideService {
	Page<Guide> findAll(Pageable pageable);
	Iterable<Guide> findAll();
    List<Guide> search(String q);

    Guide findOne(int id);

    void save(Guide guide);

    void delete(int id);
    Guide findByUserId(int  id);
    List<Guide> findGuide(String guideName,Tour tour,String location,boolean isMale,boolean isFeMale,boolean isNoExpGuide,boolean isExpGuide);
}
