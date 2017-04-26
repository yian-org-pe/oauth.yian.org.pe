package pe.org.yian.oauth.auth.server.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pe.org.yian.oauth.auth.server.util.exception.ApplicationException;

@ControllerAdvice(basePackages = { "pe.org.yian.oauth.auth.server.api" })
public class ExceptionControllerAdvice {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<?> handleApplicationExcpetion(ApplicationException ex) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		switch (ex.getExceptionType()) {
		case NOT_FOUND:
			status = HttpStatus.NOT_FOUND;
			break;
		}
		
		Map<String, String> body = new HashMap<>();
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, status);
	}
}
