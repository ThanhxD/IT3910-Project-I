package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Tour_Guide_Xref;


public interface Tour_Guide_Xref_Service {
	Iterable<Tour_Guide_Xref> findAll();

    List<Tour_Guide_Xref> search(String q);

    Tour_Guide_Xref findOne(int id);

    void save(Tour_Guide_Xref tourguidexref);

    void delete(int id);
}
