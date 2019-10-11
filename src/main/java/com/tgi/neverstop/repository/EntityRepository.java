package com.tgi.neverstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.EntityVO;

@Repository
public interface EntityRepository extends JpaRepository<EntityVO, Long> {

}
