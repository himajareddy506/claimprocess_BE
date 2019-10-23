package com.hcl.claimprocessing.utils;

public class ClaimConstants {

	private ClaimConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String CLAIM_INFO_EXIST = "Claim Info Already Exist";
	public static final String POLICY_INFO_DOESNOT_EXIST = "Policy Info Not Found";
	public static final String USER_NOT_FOUND = "User Doesn't Exist";
	public static final String CLAIM_APPLIED = "Claim Applied Successfully";
	public static final Integer SENIOR_APPROVER = 2;
	public static final Integer JUNIOR_APPROVER = 1;
	public static final String LOGIN_DENIED = "Login denied for the user";
	public static final String LOGIN_SUCCESS = "User logged in Successfully";
	public static final String PENDING_STATUS = "Pending";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String POLICY_NOT_FOUND_EXCEPTION = "Policy number doesnot exist";
	public static final String POLICY_ID_MANDATORY_EXCEPTION = "Policy Id is mandatory";
	public static final String POLICY_ID_EXIST = "Policy Id exists and success";
	public static final String APPROVED_STATUS = "Approved";
	public static final String CLAIM_INFO_NOT_FOUND = "Claim Inffo Not Found";
	public static final Integer PAGENATION_SIZE = 2;
	public static final String CLAIM_INFO_NOT_EXIST = "Claim Info Not Exist";
	public static final String INVALID_INPUTS = "Invalid pagenumber";
	public static final String INVALID_CREDENTIAL = "Invalid credentials";
	public static final String CLAIM_UPDATE_SUCCESS = "Claim Info Updated Successfully";
	public static final String HOSPITAL_NOT_EXIST = "Hospital Doesnot cover the Claim";
	public static final String IN_NETWORK = "In";
	public static final String OUT_OF_NETWORK = "Out";
	public static final String REVERT_BACK = "Rever Back";
	public static final String AILMENT_NOT_EXIST = "No Ailment Info Found";
	public static final String REJECTED_STATUS = "Rejected";
	public static final String HOSPITAL_INFO_NOT_EXIST = "Hospital Info not exists";
	public static final String DIAGNOSIS_INFO_NOT_EXIST = "Diagnosis Info not exists";
	public static final String AILMENT_INFO_NOT_EXIST = "Ailment Info Not exists";
	public static final String SEPERATOR=":";
	public static final String USER_LOGIN_CONTROLLER_INFO="Inside Login Controller";
	public static final String USER_LOGIN_SERVICE_INFO="Inside Login Service";
	public static final String APPLY_CLAIM_CONTROLLER="Inside Apply Claim Controller";
	public static final String APPLY_CLAIM_SERVICE="Inside Apply Claim Service";
	public static final String UPDATE_CLAIM_CONTROLLER="Inside Update Claim Controller";
	public static final String UPDATE_CLAIM_SERVICE="Inside Update Claim Service";
	public static final String GET_CLAIM_SERVICE="Inside Get Claim list Service";
	public static final String GET_CLAIM_CONTROLLER="Inside Get Claim list Controller";
	public static final String GET_AILMENT_LIST_CONTROLLER="Inside Get Ailments List Controller";
	public static final String GET_AILMENT_LIST_SERVICE="Inside Get Ailments List Service";
	public static final String GET_DIAGNOSIS_LIST_SERVICE="Inside Get Diagnosis List Service";
	public static final String GET_DIAGNOSIS_LIST_CONTROLLER="Inside Get Diagnosis List Controller";
	public static final String GET_HOSPITAL_LIST_CONTROLLER="Inside Get Hospital List Controller";
	public static final String GET_HOSPITAL_LIST_SERVICE="Inside Get Hospital List Service";
	public static final String SEARCH_POLICY_INFO_CONTROLLER="Inside the Search Policy Controller";
	public static final String SEARCH_POLICY_INFO_SERVICE="Inside the Search Policy Service";
}
