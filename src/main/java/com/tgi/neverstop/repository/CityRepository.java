package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

	List<City> findByStateId(String stateId);
	
	@Query("Select u from City u where u.cityName like %:cityName%")
	List<City> searchbyName(@Param("cityName") String cityName);

}