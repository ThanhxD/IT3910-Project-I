package com.webapp.guide_operator.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.guide_operator.Entities.Tour_Guide_Xref;
import com.webapp.guide_operator.Repository.Tour_Guide_Xref_Repository;

@Service
public class Tour_Guide_Xref_Impl implements Tour_Guide_Xref_Service{
	static Connection con;
	static Statement stm;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour_guide?useSSL=false&zeroDateTimeBehavior=convertToNull","root","khailinh1997");
			stm=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	private Tour_Guide_Xref_Repository tour_Guide_Repository;
	@Override
	public Iterable<Tour_Guide_Xref> findAll() {
		// TODO Auto-generated method stub
		return tour_Guide_Repository.findAll();
	}

	@Override
	public List<Tour_Guide_Xref> search(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour_Guide_Xref findOne(int id) {
		// TODO Auto-generated method stub
		return tour_Guide_Repository.findOne(id);
	}

	@Override
	public void save(Tour_Guide_Xref tourguidexref) {
		tour_Guide_Repository.save(tourguidexref);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		tour_Guide_Repository.delete(id);
	}

	@Override
	public int recommendByLocation(int tourid, int locationid,int languageid) {
		String query="insert into tour_guide_xref (tourid,guideid,status) "
				+ " select "+ tourid+",glo.guideid,3 from guide_location_xref glo,guide_language_xref glang where  glo.guideid=glang.guideid and glo.locationid="+locationid+" and glang.languageid="+languageid+
				" and glo.guideid not in ( select guideid from tour_guide_xref where tourid="+tourid+")"+";";
		try {
			
			stm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0; 
	}

	@Override
	public void operatorRequest(int tourid, int guideid) {
		String query = "insert into tour_guide_xref(tourid,guideid,status) values ("+tourid+","+guideid+",2);";
		
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
