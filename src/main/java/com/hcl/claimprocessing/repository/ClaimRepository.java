package com.hcl.claimprocessing.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.claimprocessing.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	Optional<Claim> findByAdmitDateAndDischargeDate(LocalDate admitDate, LocalDate dischargeDate);

	Optional<Claim> findByUserId(Integer userId);

}
