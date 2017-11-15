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
@Table(name="tour_location_xref")
public class TourLocationXref implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="locationid" ,referencedColumnName="id")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name="tourid" ,referencedColumnName="id")
	private Tour tour;
	
	public TourLocationXref() {}

	public TourLocationXref(int id, Location location, Tour tour) {
		super();
		this.id = id;
		this.location = location;
		this.tour = tour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
}
