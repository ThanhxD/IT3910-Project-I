package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.guide_operator.Entities.Guide;

public interface GuideService {
	Page<Guide> findAll(Pageable pageable);

    List<Guide> search(String q);

    Guide findOne(int id);

    void save(Guide guide);

    void delete(int id);
    Guide findByUserId(int  id);
}
