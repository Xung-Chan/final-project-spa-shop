package final_project_spa_shop.final_project_spa_shop.exception;

public enum ErrorCode {
	INVALID_CUSTOMER(1000,"Customer is not exist"),
	INVALID_EMPLOYEE(1001,"Employee is not exist"),
	INVALID_TIME(1002,"Time is invalid"),
	INVALID_PASSWORD(1003,"Password must be least 6 character"),
	INVALID_USERNAME(1004,"Username is invalid"),
	INVALID_EXCEPTION(1005,"Message exception is invalid"),
	NULL_VALUE(1006,"Value can not null"),
	INVALID_PHONENUMBER(1007,"Phonenumber is invalid"),
	INVALID_EMAIL(1008,"Email is invalid"),
	INVALID_RATE(1009,"Valid rating from 1 to 5"),
	INVALID_APPOINTMENT(1010,"Appointment is not exist"),
	INVALID_BILL(1011,"Bill is not exist"),
	INVALID_SCHEDULE(1012,"Schedule is not exist"),
	INVALID_SERVICE(1013,"Service is not exist"),
	INVALID_PERMISSION(1014,"Permission is not exist"),
	DUPLICATED_SERVICE(1015,"Service is existed"),
	DUPLICATED_USERNAME(1016,"Username is existed"),
	INVALID_VOUCHER(1017,"Voucher is not exist"),
	DUPLICATED_VOUCHER(1018,"Voucher have already used"),
	INVALID_FEEDBACK(1019,"Feedback is not exist"),
	INVALID_POST(1020,"Post is not exist"),
	JWT_EXCEPTION(1021,"Error in using JWT"),
	LOGIN_FAILURE(1022,"Username or password is incorrect"),
	RESOURCE_NOT_FOUND(1023,"404 not found"),
	INVALID_ROLE(1024,"Role is not exist"),
	INVALID_PROFILE(1025,"Profile is not exist"),
	INVALID_PRICE(1026,"Price is not valid"),
	INVALID_IMAGE(1027,"Image is not valid"),
	WRONG_PASSWORD(1028,"Password is wrong"),
	;
	private int code;
	private String message;
	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
