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
@Table(name = "Tour")
public class Tour implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;
	
	@Length(max = 255)
    @Column(name = "TourName", nullable = false)
	private String tourName;
	
	@Length(max = 255)
    @Column(name = "TourTime", nullable = false)
	private String tourTime;
	
	@Column(name="TourPrice", nullable=true)
	private int tourPrice;
	
	public Tour() {}

	public Tour(int id, String tourName, String tourTime, int tourPrice) {
		super();
		this.id = id;
		this.tourName = tourName;
		this.tourTime = tourTime;
		this.tourPrice = tourPrice;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
}
