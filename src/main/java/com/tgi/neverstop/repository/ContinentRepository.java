package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, String> 
{

	@Query("Select u from Continent u where u.continentName like %:continentName%")
	List<Continent> searchbyName(@Param("continentName") String continentName);
	
}