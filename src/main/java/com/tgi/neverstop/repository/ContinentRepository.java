package com.tgi.neverstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {

}