package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import com.hcl.claimprocessing.entity.Diagnosis;

public interface DiagnosisService {

	Optional<List<Diagnosis>> getDiagnosis();
}
