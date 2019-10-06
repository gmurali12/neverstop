package com.tgi.neverstop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "country", uniqueConstraints = { @UniqueConstraint(columnNames = { "country_name" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "country_name", nullable = false)
	private String countryName;

	@Column(name = "status")
	private int status;

	@Column(name = "continent_id")
	private Long continentId;

	public Country() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContinentId() {
		return continentId;
	}

	public void setContinentId(Long continentId) {
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

}