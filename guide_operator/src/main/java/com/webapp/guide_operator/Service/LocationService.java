package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Location;

public interface LocationService {
	Location findByLocationName(String name);
	Iterable<Location> findAll();

    List<Location> search(String q);

    Location findOne(int id);

    void save(Location location);

    void delete(int id);
}
