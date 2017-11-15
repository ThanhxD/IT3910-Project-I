package com.webapp.guide_operator.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="guide_location_xref")
public class GuideLocationXref implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "guideid", referencedColumnName = "id")
	private Guide guide;
	
	@ManyToOne
	@JoinColumn(name="locationid" ,referencedColumnName="id")
	private Location location;
	
	public GuideLocationXref() {}

	public GuideLocationXref(int id, Guide guide, Location location) {
		super();
		this.id = id;
		this.guide = guide;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
	
	
}
