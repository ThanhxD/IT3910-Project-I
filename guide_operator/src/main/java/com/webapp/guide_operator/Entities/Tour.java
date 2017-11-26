package com.webapp.guide_operator.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Length(max = 255)
    @Column(name = "Tourtime", nullable = false)
	private String tourTime;
	
	@Column(name="Tourprice", nullable=true)
	private int tourPrice;
	@ManyToMany
	@JoinTable(
	            name = "tour_location_xref",
	            joinColumns = @JoinColumn(name = "tourid"),
	            inverseJoinColumns = @JoinColumn(name = "locationid")
	    )
    private Set<Location> locations;
	
	@ManyToMany
	@JoinTable(
	            name = "operator_tour_xref",
	            joinColumns = @JoinColumn(name = "tourid"),
	            inverseJoinColumns = @JoinColumn(name = "operatorid")
	    )
    private Set<Operator> operators;
	public Tour() {}
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
	public Set<Operator> getOperators() {
		return operators;
	}
	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Tour(int id, String tourName, String tourTime, int tourPrice, Set<Location> locations,
			Set<Operator> operators) {
		super();
		this.id = id;
		this.tourName = tourName;
		this.tourTime = tourTime;
		this.tourPrice = tourPrice;
		this.locations = locations;
		this.operators = operators;
	}
	

	

	

	

	

	
	
}
