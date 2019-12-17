package com.tgi.neverstop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
	//List<Country> findByContinentId(String continentId);
	public Optional<Country> findById(String id);

	@Query("Select u from Country u where u.countryName like %:countryName%")
	List<Country> searchbyName(@Param("countryName") String countryName);

}