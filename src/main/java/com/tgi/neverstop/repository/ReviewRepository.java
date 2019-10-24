package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

	List<Review> findByEntityId(String entityId);
	
	@Query("SELECT avg(rating) FROM Review  WHERE entity_id=:entityId")
    public Double getAvgRatngByEntity(@Param("entityId") String entityId);
}
