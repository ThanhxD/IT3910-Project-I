package com.webapp.guide_operator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Location;
import com.webapp.guide_operator.Repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService{
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Iterable<Location> findAll() {
		// TODO Auto-generated method stub
		return locationRepository.findAll();
	}

	@Override
	public List<Location> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findOne(int id) {
		// TODO Auto-generated method stub
		return locationRepository.findOne(id);
	}

	@Override
	public void save(Location location) {
		locationRepository.save(location);
		
	}

	@Override
	public void delete(int id) {
		locationRepository.delete(id);
		
	}
	
}
