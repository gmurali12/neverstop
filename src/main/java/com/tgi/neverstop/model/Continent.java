package com.tgi.neverstop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "continent", uniqueConstraints = { @UniqueConstraint(columnNames = { "continent_name" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Continent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "continent_name", nullable = false)
	private String continentName;

	@Column(name = "status")
	private int status;

	public Continent() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentname(String continentName) {
		this.continentName = continentName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

}