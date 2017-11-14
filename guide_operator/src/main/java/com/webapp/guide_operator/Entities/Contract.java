package com.webapp.guide_operator.Entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "contract")
public class Contract implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "guideid", referencedColumnName = "id")
	private Guide guide;
	
	@ManyToOne
	@JoinColumn(name = "operatorid", referencedColumnName = "id")
	private Operator operator;
	
	@ManyToOne
	@JoinColumn(name = "tourid", referencedColumnName = "id")
	private Tour tour;
	
	@Column(name="DepartureDay", nullable=false)
	private Time departureDay;
	
	@Column(name="salary")
	private int salary;
	
	public Contract() {}

	public Contract(int id, Guide guide, Operator operator, Tour tour, Time departureDay, int salary) {
		super();
		this.id = id;
		this.guide = guide;
		this.operator = operator;
		this.tour = tour;
		this.departureDay = departureDay;
		this.salary = salary;
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

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Time getDepartureDay() {
		return departureDay;
	}

	public void setDepartureDay(Time departureDay) {
		this.departureDay = departureDay;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
}
