package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import com.hcl.claimprocessing.entity.Hospital;

public interface HospitalService {

	Optional<List<Hospital>> getHospitals();
}
