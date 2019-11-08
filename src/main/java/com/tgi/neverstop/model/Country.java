package com.tgi.neverstop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "country", uniqueConstraints = { @UniqueConstraint(columnNames = { "country_name" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "country_name", nullable = false)
	private String countryName;

	@Column(name = "status")
	private int status;

	@NotBlank
	@Column(name = "continent_id", nullable = false)
	private String continentId;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "continent_id", insertable = false, updatable = false)
    private Continent continent;
	
	public Country() {
	}

	public String getContinentId() {
		return continentId;
	}

	public void setContinentId(String continentId) {
		this.continentId = continentId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String counttryName) {
		this.countryName = counttryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}
}