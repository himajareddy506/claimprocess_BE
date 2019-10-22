package com.hcl.claimprocessing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.claimprocessing.entity.Ailments;

@Repository
public interface AilmentRepository extends JpaRepository<Ailments, Integer> {
		Optional<Ailments> findByAilmentName(String ailmentName);
}
