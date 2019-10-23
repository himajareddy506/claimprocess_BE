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

import com.hcl.claimprocessing.entity.Hospital;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.service.HospitalServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HospitalControllerTest {

	@Mock
	HospitalServiceImpl hospitalServiceImpl;

	@InjectMocks
	HospitalController hospitalController;

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

		hospitalList.add(hospital);
	}

	@Test
	public void testGetHospitalList() throws HospitalNotFoundException {

		hospitalInfo = Optional.of(hospitalList);
		Mockito.when(hospitalServiceImpl.getHospitals()).thenReturn(hospitalInfo);
		ResponseEntity<List<Hospital>> hospitalResponseList = hospitalController.getHospitalList();
		assertNotNull(hospitalResponseList);
	}

	@Test(expected = HospitalNotFoundException.class)
	public void testNegativeGetHospitalList() throws HospitalNotFoundException {
		hospitalList = null;
		hospitalInfo = Optional.ofNullable(hospitalList);
		Mockito.when(hospitalServiceImpl.getHospitals()).thenReturn(hospitalInfo);
		ResponseEntity<List<Hospital>> hospitalResponseList = hospitalController.getHospitalList();
		assertNotNull(hospitalResponseList);
	}

}
