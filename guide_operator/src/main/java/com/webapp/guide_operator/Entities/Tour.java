package com.webapp.guide_operator.Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Tour")
public class Tour implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private int id;

	@Length(max = 255)
	@Column(name = "Tourname", nullable = false)
	private String tourName;
	
	@Column(name = "status", nullable = false)
	private int status;
	
	@Length(max = 255)
	@Column(name = "Tourtime", nullable = false)
	private String tourTime;

	@Column(name = "Tourprice", nullable = true)
	private int tourPrice;
	@ManyToMany
	@JoinTable(name = "tour_location_xref", joinColumns = @JoinColumn(name = "tourid"), inverseJoinColumns = @JoinColumn(name = "locationid"))
	private Set<Location> locations;

	@ManyToMany
	@JoinTable(name = "operator_tour_xref", joinColumns = @JoinColumn(name = "tourid"), inverseJoinColumns = @JoinColumn(name = "operatorid"))
	private Set<Operator> operators;

	@ManyToMany
	@JoinTable(name = "tour_guide_xref", joinColumns = @JoinColumn(name = "tourid"), inverseJoinColumns = @JoinColumn(name = "guideid"))
	private Set<Guide> guides;

	@OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
	private List<Tour_Guide_Xref> tourGuideXref;
	public Tour() {
	}

	public List<Tour_Guide_Xref> getTourGuideXref() {
		return tourGuideXref;
	}

	public void setTourGuideXref(List<Tour_Guide_Xref> tourGuideXref) {
		this.tourGuideXref = tourGuideXref;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getTourTime() {
		return tourTime;
	}

	public void setTourTime(String tourTime) {
		this.tourTime = tourTime;
	}

	public int getTourPrice() {
		return tourPrice;
	}

	public void setTourPrice(int tourPrice) {
		this.tourPrice = tourPrice;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public Set<Guide> getGuides() {
		return guides;
	}

	public void setGuides(Set<Guide> guides) {
		this.guides = guides;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Tour(String tourName, String tourTime, int tourPrice) {
		super();
		this.tourName = tourName;
		this.tourTime = tourTime;
		this.tourPrice = tourPrice;
	}

}
