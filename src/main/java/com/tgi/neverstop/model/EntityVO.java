package com.tgi.neverstop.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entity")
public class EntityVO {
	@Id
	private String entity_id;

	private String entity_name;

	private String phone;

	private String email;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String country;

	private String zipcode;

	private String profile_image;

	private String thumb_image;

	private String latitude;

	private String longitude;

	private String img_path;

	private int status;

	private int create_by;

	private Date create_date;

	/**
	 * @return the entity_id
	 */
	public String getEntity_id() {
		return entity_id;
	}

	/**
	 * @param entity_id the entity_id to set
	 */
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	/**
	 * @return the entity_name
	 */
	public String getEntity_name() {
		return entity_name;
	}

	/**
	 * @param entity_name the entity_name to set
	 */
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the profile_image
	 */
	public String getProfile_image() {
		return profile_image;
	}

	/**
	 * @param profile_image the profile_image to set
	 */
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	/**
	 * @return the thumb_image
	 */
	public String getThumb_image() {
		return thumb_image;
	}

	/**
	 * @param thumb_image the thumb_image to set
	 */
	public void setThumb_image(String thumb_image) {
		this.thumb_image = thumb_image;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the img_path
	 */
	public String getImg_path() {
		return img_path;
	}

	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the create_by
	 */
	public int getCreate_by() {
		return create_by;
	}

	/**
	 * @param create_by the create_by to set
	 */
	public void setCreate_by(int create_by) {
		this.create_by = create_by;
	}

	/**
	 * @return the create_date
	 */
	public Date getCreate_date() {
		return create_date;
	}

	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}