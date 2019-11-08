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
@Table(name = "city", uniqueConstraints = { @UniqueConstraint(columnNames = { "city_name" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "city_name", nullable = false)
	private String cityName;
	
	@Column(name = "city_img")
	private String cityImg;

	@Column(name = "status")
	private int status;

	@NotBlank
	@Column(name = "country_id", nullable = false)
	private String countryId;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
	

	public City() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCityImg() {
		return cityImg;
	}

	public void setCityImg(String cityImg) {
		this.cityImg = cityImg;
	}

}