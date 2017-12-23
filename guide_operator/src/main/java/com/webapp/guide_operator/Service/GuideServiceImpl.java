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
import com.webapp.guide_operator.Repository.GuideRepository;
@Service
public class GuideServiceImpl implements GuideService{
	@Autowired
	private GuideRepository guideRepository;
	
	@Override
	public Page<Guide> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return guideRepository.findAll(pageable);
	}

	@Override
	public List<Guide> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guide findOne(int id) {
		// TODO Auto-generated method stub
		return guideRepository.findOne(id);
	}

	@Override
	public void save(Guide guide) {
		guideRepository.save(guide);
		
	}

	@Override
	public void delete(int id) {
		guideRepository.delete(id);
		
	}



	@Override
	public Guide findByUserId(int id) {
		// TODO Auto-generated method stub
		return guideRepository.findByUserId(id);
	}

	@Override
	public Iterable<Guide> findAll() {
		// TODO Auto-generated method stub
		return guideRepository.findAll();
	}
	
	public List<Guide> findGuide(String guidename,Tour tour,String location,boolean isMale,boolean isFeMale,boolean isNoExpGuide,boolean isExpGuide){
		List<Guide> guides = new LinkedList<>();
		try {
			String gender,exp,name;
			if(guidename==null||guidename.equals("")) {
				name="";
				
			}
			else {
				name=" and fullname like '%"+guidename+"%'";
			}
			if(isMale) {
				gender=" and gender='male'";
			}
			else if(isFeMale) {
				gender=" and gender='female'";
			}
			else {
				gender ="";
			}
			if(isExpGuide) {
				exp=" and  cardnumber is not null";
			}
			else if(isNoExpGuide){
				exp=" and  cardnumber is null";
			}
			else {
				exp="";
			}
			String query ="select guide.id as gid from user,guide,guide_location_xref gl,location where user.id=guide.userid and guide.id=gl.guideid and location.id=gl.locationid and location.LocationName='"+location+"'"
					+gender+exp+name+";";
			System.out.println(query);
			ResultSet rs=Tour_Guide_Xref_Impl.stm.executeQuery(query);
			
			while(rs.next()) {
				guides.add(guideRepository.findOne(rs.getInt("gid")));
			}
			for(int i=0;i<guides.size();i++) {
				
				for(int j=0;j<tour.getTourGuideXref().size();j++) {
					if(guides.get(i).getId()==tour.getTourGuideXref().get(j).getGuide().getId()) {
						guides.remove(i);
						i--;
						break;
					}
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return guides;
	}

}
