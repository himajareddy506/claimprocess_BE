package com.hcl.claimprocessing.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.claimprocessing.entity.Diagnosis;
import com.hcl.claimprocessing.repository.DiagnosisRepository;

@RunWith(MockitoJUnitRunner.class)
public class DiagnosisServcieTest {

	@Mock
	DiagnosisRepository diagnosisRepository;
	@InjectMocks
	DiagnosisServiceImpl diagnosisServiceImpl;

	Diagnosis diagnosis;

	Optional<List<Diagnosis>> diagnosisInfo;

	List<Diagnosis> diagnosisList;

	@Before
	public void initiateData() {

		diagnosis = new Diagnosis();
		diagnosis.setDiagnosisId(1);
		diagnosis.setDiagnosisType("Respiratory");

		diagnosisList = new ArrayList<>();

	}

	@Test
	public void testGetDiagnosis() {

		diagnosisList.add(diagnosis);
		Mockito.when(diagnosisRepository.findAll()).thenReturn(diagnosisList);
		diagnosisInfo = diagnosisServiceImpl.getDiagnosis();
		assertNotNull(diagnosisInfo);
	}
}
