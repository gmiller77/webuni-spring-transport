package hu.webuni.transport.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice // emiatt lesz közös minden Controller-re
public class CustomExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> handleValidationError(MethodArgumentNotValidException e, WebRequest req) {
		log.warn(e.getMessage(), e);
		MyError myError = new MyError(e.getMessage(), 1240); // 1240 lesz a saját hibakódunk erre a TÖBB bemenő
																// paraméter hibát is hibaként jelző Exception-re
		myError.setFielđdErrors(e.getBindingResult().getFieldErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
	}

}
