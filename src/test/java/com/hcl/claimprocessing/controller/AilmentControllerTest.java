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

import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.service.AilmentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AilmentControllerTest {

	@Mock
	AilmentServiceImpl ailmentServiceImpl;

	@InjectMocks
	AilmentController ailmentController;

	Ailments ailment;
	Optional<List<Ailments>> ailmentInfo;
	List<Ailments> ailmentList;

	@Before
	public void initiateData() {
		ailment = new Ailments();
		ailment.setAilmentId(1);
		ailment.setAilmentName("Cold");
		ailment.setDiagnosisId(1);
		ailment.setMaxAmount(10000.0);

		ailmentList = new ArrayList<>();
		ailmentList.add(ailment);
	}

	@Test
	public void testGetAilmentList() throws HospitalNotFoundException {
		ailmentInfo = Optional.of(ailmentList);
		Mockito.when(ailmentServiceImpl.getAilmentList(Mockito.anyInt())).thenReturn(ailmentInfo);
		ResponseEntity<List<Ailments>> ailmentResponseList = ailmentController.getAilmentList(ailment.getDiagnosisId());
		assertNotNull(ailmentResponseList);
	}

	@Test(expected = HospitalNotFoundException.class)
	public void testNegativeGetAilmentList() throws HospitalNotFoundException {
		ailmentList = null;
		ailmentInfo = Optional.ofNullable(ailmentList);
		Mockito.when(ailmentServiceImpl.getAilmentList(Mockito.anyInt())).thenReturn(ailmentInfo);
		ResponseEntity<List<Ailments>> ailmentResponseList = ailmentController.getAilmentList(ailment.getDiagnosisId());
		assertNotNull(ailmentResponseList);
	}
}
