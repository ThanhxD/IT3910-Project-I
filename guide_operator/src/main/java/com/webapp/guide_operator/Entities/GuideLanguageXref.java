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
@Table(name="guide_language_xref")
public class GuideLanguageXref implements Serializable{

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
	@JoinColumn(name = "languageid", referencedColumnName = "id")
	private Language language;
	
	public GuideLanguageXref() {}

	public GuideLanguageXref(int id,Guide guide, Language language) {
		super();
		this.id=id;
		this.guide = guide;
		this.language = language;
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
}
