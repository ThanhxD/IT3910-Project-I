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
@Table(name = "operator_tour_xref")
public class OperatorTourXref implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "operatorid", referencedColumnName = "id")
	private Operator operator;
	
	@ManyToOne
	@JoinColumn(name = "tourid", referencedColumnName = "id")
	private Tour tour;
	
	public OperatorTourXref() {}

	public OperatorTourXref(int id, Operator operator, Tour tour) {
		super();
		this.id = id;
		this.operator = operator;
		this.tour = tour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
}
