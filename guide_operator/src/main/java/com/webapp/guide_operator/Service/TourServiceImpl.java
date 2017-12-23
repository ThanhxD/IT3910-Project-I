package com.webapp.guide_operator.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Repository.TourRepository;

@Service
public class TourServiceImpl implements TourService {
	@Autowired
	private TourRepository tourRepository;

	@Override
	public Page<Tour> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tourRepository.findAll(pageable);
	}

	@Override
	public List<Tour> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour findOne(int id) {
		// TODO Auto-generated method stub
		return tourRepository.findOne(id);
	}

	@Override
	public void save(Tour tour) {
		tourRepository.save(tour);

	}

	@Override
	public void delete(int id) {

		try {
			String query = "delete from tour_guide_xref where tourid=" + id + ";";
			Tour_Guide_Xref_Impl.stm.executeUpdate(query);
			query = "delete from tour_location_xref where tourid=" + id + ";";
			Tour_Guide_Xref_Impl.stm.executeUpdate(query);
			query = "delete from operator_tour_xref where tourid=" + id + ";";
			Tour_Guide_Xref_Impl.stm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tourRepository.delete(id);

	}

	@Override
	public List<Tour> findByTourName(String tourName) {
		// TODO Auto-generated method stub
		return tourRepository.findByTourName(tourName);
	}

	@Override
	public List<Tour> findTour(Guide guide, String tourName, String operatorName, String location, String day) {
		String tName, oName, lName, daynight;
		if (tourName != null) {
			tName = " and tourname like '%" + tourName + "%'";
		} else {
			tName = "";
		}
		if (operatorName != null) {
			oName = " and companyNameViet like '%" + operatorName + "%'";
		} else {
			oName = "";
		}
		if (location != null) {
			lName = " and locationName like '%" + location + "%'";
		} else {
			lName = "";
		}
		if (day != null) {
			daynight = " and tourTime like '%" + day + "%'";
		} else {
			daynight = "";
		}
		String query = "select tour.id as tid from tour,tour_operator operator,operator_tour_xref ot,location,tour_location_xref tl where"
				+ " tour.id=ot.tourid and operator.id=ot.operatorid and location.id =tl.locationid and tour.id= tl.tourid "
				+ tName + oName + lName + daynight + " ;";
		System.out.println(query);
		List<Tour> result = new LinkedList<>();
		try {
			ResultSet rs = Tour_Guide_Xref_Impl.stm.executeQuery(query);
			while (rs.next()) {
				Tour tour = tourRepository.findOne(rs.getInt("tid"));
				boolean flag = true;
				for (Tour tTour : guide.getTours()) {
					if (tTour.getId() == tour.getId()) {
						flag = false;
					}
				}
				if (flag)
					result.add(tour);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
