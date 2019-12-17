package com.tgi.neverstop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tgi.neverstop.model.State;

public interface StateRepository extends JpaRepository<State, String> {

	public Optional<State> findById(String id);
	//List<State> findByCountryId(String countryId);

	@Query("Select u from State u where u.stateName like %:stateName%")
	List<State> searchbyName(@Param("stateName") String stateName);

}
