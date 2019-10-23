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

import com.hcl.claimprocessing.entity.Hospital;
import com.hcl.claimprocessing.repository.HospitalRepository;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {

	@Mock
	HospitalRepository hospitalRepository;
	@InjectMocks
	HospitalServiceImpl hospitalServiceImpl;

	Hospital hospital;

	Optional<List<Hospital>> hospitalInfo;

	List<Hospital> hospitalList;

	@Before
	public void initiateData() {

		hospital = new Hospital();
		hospital.setHospitalId(1);
		hospital.setHospitalName("Apollo Hospitals");
		hospital.setNetworkStatus("In");

		hospitalList = new ArrayList<>();

	}

	@Test
	public void testGetHospital() {

		hospitalList.add(hospital);
		Mockito.when(hospitalRepository.findAll()).thenReturn(hospitalList);
		hospitalInfo = hospitalServiceImpl.getHospitals();
		assertNotNull(hospitalInfo);
	}

}
