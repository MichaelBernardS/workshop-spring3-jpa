package com.uniondata.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uniondata.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Esse annotation que vai interceptar as exceções que acontecerem, para que esse objeto execute um possível tratamento;
public class ResourceExceptionHandler { // Classe que daremos o tratamento manual dos erros;
	
	@ExceptionHandler(ResourceNotFoundException.class) // Annotation para interceptar a requisição que deu a exceção para cair aqui;
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}