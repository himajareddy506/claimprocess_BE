package com.hcl.claimprocessing.utils;

public class ClaimConstants {

	private ClaimConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String POLICY_NOT_FOUND_EXCEPTION = "Your policy number doesnot exist";
	public static final String POLICY_ID_MANDATORY_EXCEPTION = "Policy Id is mandatory";
	public static final String POLICY_ID_EXIST = "Policy Id exists and success";

	public static final String CLAIM_INFO_EXIST = "Claim Info Already Exist";
	public static final String POLICY_INFO_DOESNOT_EXIST = "Policy Info Not Found";
	public static final String USER_NOT_FOUND = "User Doesn't Exist";
	public static final String CLAIM_APPLIED = "Claim Applied Successfully";

}
