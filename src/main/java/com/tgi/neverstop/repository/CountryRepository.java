package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	List<Country> findByContinentId(Long continentId);

}