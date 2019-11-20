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
@Table(name = "state", uniqueConstraints = { @UniqueConstraint(columnNames = { "state_name" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "state_name", nullable = false)
	private String stateName;
 
	@Column(name = "status")
	private int status;
	
	@Column(name = "state_image")
	private String stateImg;
	
	@NotBlank
	@Column(name = "country_id", nullable = false)
	private String countryId;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
	
	public State() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStateImg() {
		return stateImg;
	}

	public void setStateImg(String stateImg) {
		this.stateImg = stateImg;
	}
	
	

}