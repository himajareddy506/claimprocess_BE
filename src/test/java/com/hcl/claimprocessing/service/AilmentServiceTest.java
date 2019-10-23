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

import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.repository.AilmentRepository;

@RunWith(MockitoJUnitRunner.class)
public class AilmentServiceTest {

	@Mock
	AilmentRepository ailmentRepository;

	@InjectMocks
	AilmentServiceImpl ailmentServiceImpl;

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
	public void testGetAilment() {
		ailmentInfo = Optional.of(ailmentList);
		Mockito.when(ailmentRepository.findByDiagnosisId(Mockito.anyInt())).thenReturn(ailmentInfo);
		Optional<List<Ailments>> response = ailmentServiceImpl.getAilmentList(ailment.getDiagnosisId());
		assertNotNull(response);
	}
}
