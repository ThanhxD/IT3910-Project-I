package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Landscapes;

public interface LandscapesService {
	Iterable<Landscapes> findAll();

    List<Landscapes> search(String q);

    Landscapes findOne(int id);

    void save(Landscapes landscapes);

    void delete(int id);
}
