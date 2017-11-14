package com.webapp.guide_operator.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tour_operator")
public class Operator implements Serializable {

    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    

	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
	
	@Length(max = 255)
    @Column(name = "CompanyNameViet", nullable = true)
	private String companyNameViet;
	
	@Length(max = 255)
    @Column(name = "cardnumber", nullable = true)
	private String CompanyNameEng;
	
	public Operator(){}

	public Operator(int id, User user, String companyNameViet, String companyNameEng) {
		super();
		this.id = id;
		this.user = user;
		this.companyNameViet = companyNameViet;
		CompanyNameEng = companyNameEng;
	}

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
		return CompanyNameEng;
	}

	public void setCompanyNameEng(String companyNameEng) {
		CompanyNameEng = companyNameEng;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
}
