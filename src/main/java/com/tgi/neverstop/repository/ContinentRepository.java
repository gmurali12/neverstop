package com.tgi.neverstop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, String> 
{
	public Optional<Continent> findById(String id);
	
	@Query("Select u from Continent u where u.continentName like %:continentName%")
	List<Continent> searchbyName(@Param("continentName") String continentName);
	
}