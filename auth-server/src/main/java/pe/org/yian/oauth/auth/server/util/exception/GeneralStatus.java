package pe.org.yian.oauth.auth.server.util.exception;

public enum GeneralStatus {
	ENABLED("E"), DISABLED("D");
	
	private String code;
	
	private GeneralStatus (String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
