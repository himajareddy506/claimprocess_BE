package com.hcl.claimprocessing.controller;

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
import org.springframework.http.ResponseEntity;

import com.hcl.claimprocessing.entity.Diagnosis;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.service.DiagnosisServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DiagnosisControllerTest {

	@Mock
	DiagnosisServiceImpl diagnosisServiceImpl;

	@InjectMocks
	DiagnosisController diagnosisController;

	Diagnosis diagnosis;

	Optional<List<Diagnosis>> diagnosisInfo;

	List<Diagnosis> diagnosisList;

	@Before
	public void initiateData() {

		diagnosis = new Diagnosis();
		diagnosis.setDiagnosisId(1);
		diagnosis.setDiagnosisType("Respiratory");

		diagnosisList = new ArrayList<>();

		diagnosisList.add(diagnosis);
	}

	@Test
	public void testGetDiagnosisList() throws HospitalNotFoundException {
		diagnosisInfo = Optional.of(diagnosisList);
		Mockito.when(diagnosisServiceImpl.getDiagnosis()).thenReturn(diagnosisInfo);
		ResponseEntity<List<Diagnosis>> diagnosisResponseList = diagnosisController.getDaignosisList();
		assertNotNull(diagnosisResponseList);
	}

	@Test(expected = HospitalNotFoundException.class)
	public void testNegativeGetDiagnosisList() throws HospitalNotFoundException {
		diagnosisList = null;
		diagnosisInfo = Optional.ofNullable(diagnosisList);
		Mockito.when(diagnosisServiceImpl.getDiagnosis()).thenReturn(diagnosisInfo);
		ResponseEntity<List<Diagnosis>> diagnosisResponseList = diagnosisController.getDaignosisList();
		assertNotNull(diagnosisResponseList);
	}
}
