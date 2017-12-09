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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tour_operator")
public class Operator implements Serializable {

    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    

	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
	
	@Length(max = 255)
    @Column(name = "Companynameviet", nullable = false)
	private String companyNameViet;
	
	@Length(max = 255)
    @Column(name = "Companynameeng", nullable = true)
	private String companyNameEng;
	
	@OrderBy("id ASC")
	@ManyToMany
	@JoinTable(
	            name = "operator_tour_xref",
	            joinColumns = @JoinColumn(name = "operatorid"),
	            inverseJoinColumns = @JoinColumn(name = "tourid")
	    )
    private Set<Tour> tours;
	public Operator(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCompanyNameViet() {
		return companyNameViet;
	}
	public void setCompanyNameViet(String companyNameViet) {
		this.companyNameViet = companyNameViet;
	}
	public String getCompanyNameEng() {
		return companyNameEng;
	}
	public void setCompanyNameEng(String companyNameEng) {
		this.companyNameEng = companyNameEng;
	}
	public Set<Tour> getTours() {
		return tours;
	}
	public void setTours(Set<Tour> tours) {
		this.tours = tours;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Operator(User user, String companyNameViet, String companyNameEng) {
		super();
		this.user = user;
		this.companyNameViet = companyNameViet;
		this.companyNameEng = companyNameEng;
		this.tours = tours;
	}
	
	
	
}
