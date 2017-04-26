package pe.org.yian.oauth.auth.server.util.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public enum AppExType { NOT_FOUND }
	
	private AppExType exceptionType;

	public ApplicationException(AppExType exType, LogicException ex) {
		super(ex.getMessage());
		this.exceptionType = exType;
	}

	public AppExType getExceptionType() {
		return exceptionType;
	}
}
