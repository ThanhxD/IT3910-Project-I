package com.webapp.guide_operator.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="location")
public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Length(max = 255)
    @Column(name = "Locationname", nullable = false,unique = true)
	private String locationName;
	
	@Length(max = 255)
    @Column(name = "Locationtype", nullable = false)
	private String locationType;
	public Location() {}
	public Location(int id, String locationName, String locationType) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.locationType = locationType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
	
}
