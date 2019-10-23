package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import com.hcl.claimprocessing.entity.Ailments;

public interface AilmentService {

	Optional<List<Ailments>> getAilmentList(Integer diagnosisId);
}
