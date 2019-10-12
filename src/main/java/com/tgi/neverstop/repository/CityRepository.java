package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

	List<City> findByCountryId(String countryId);

}