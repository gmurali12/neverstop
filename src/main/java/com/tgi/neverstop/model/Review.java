package com.tgi.neverstop.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "comments")
	private String comments;

	@Column(name = "like")
	private int like;

	@Column(name = "rating")
	private int rating;

	@Column(name = "posted_on")
	private Timestamp postedOn;

	@Column(name = "entity_id")
	private String entityId;

	@Column(name = "user_id")
	private String userId;

	public Review() {

	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the like
	 */
	public int getLike() {
		return like;
	}

	/**
	 * @param like the like to set
	 */
	public void setLike(int like) {
		this.like = like;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the postedOn
	 */
	public Timestamp getPostedOn() {
		return postedOn;
	}

	/**
	 * @param postedOn the postedOn to set
	 */
	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}

	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


}